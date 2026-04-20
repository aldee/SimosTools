package com.app.simostools

import com.app.simostools.core.utils.DebugLog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class BTDevice(val name: String, val address: String)

class BTDeviceAdapter(private val devices: List<BTDevice>, private val onClick: (BTDevice) -> Unit) :
    RecyclerView.Adapter<BTDeviceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(android.R.id.text1)
        val addressText: TextView = view.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = devices[position]
        holder.nameText.text = device.name
        holder.nameText.setTextColor(ColorList.TEXT.value)
        holder.addressText.text = device.address
        holder.addressText.setTextColor(ColorList.TEXT.value)
        holder.itemView.setOnClickListener { onClick(device) }
    }

    override fun getItemCount() = devices.size
}

class ConnectionFragment : Fragment() {
    private val tag = "ConnectionFragment"
    private val mDevices = mutableListOf<BTDevice>()
    private lateinit var mAdapter: BTDeviceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set background color
        view.setBackgroundColor(ColorList.BG_NORMAL.value)
        view.findViewById<ImageView>(R.id.imageMainLogo).setBackgroundColor(ColorList.BG_NORMAL.value)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerDevices)
        recycler.layoutManager = LinearLayoutManager(context)
        mAdapter = BTDeviceAdapter(mDevices) { device ->
            connectToDevice(device)
        }
        recycler.adapter = mAdapter

        val buttonScan = view.findViewById<Button>(R.id.buttonScan)
        buttonScan.setTextColor(ColorList.BT_TEXT.value)
        buttonScan.setBackgroundColor(ColorList.BT_BG.value)
        buttonScan.setOnClickListener {
            DebugLog.d(tag, "Scan button clicked")
            mDevices.clear()
            mAdapter.notifyDataSetChanged()
            sendServiceMessage(BTServiceTask.START_SCAN.toString())
        }

        val buttonDisconnect = view.findViewById<Button>(R.id.buttonDisconnect)
        buttonDisconnect.setTextColor(ColorList.BT_TEXT.value)
        buttonDisconnect.setBackgroundColor(ColorList.BT_BG.value)
        buttonDisconnect.setOnClickListener {
            sendServiceMessage(BTServiceTask.DO_DISCONNECT.toString())
        }

        DebugLog.d(tag, "onViewCreated")
    }

    override fun onResume() {
        super.onResume()

        val filter = IntentFilter()
        filter.addAction(GUIMessage.SCAN_RESULT.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            activity?.registerReceiver(mBroadcastReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activity?.registerReceiver(mBroadcastReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            activity?.registerReceiver(mBroadcastReceiver, filter)
        }

        if(!ConfigSettings.AUTO_LOG.toBoolean())
            sendServiceMessage(BTServiceTask.DO_STOP_TASK.toString())

        DebugLog.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(mBroadcastReceiver)
        DebugLog.d(tag, "onPause")
    }

    private val mBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            when (intent.action) {
                GUIMessage.SCAN_RESULT.toString() -> {
                    val name = intent.getStringExtra("deviceName") ?: "Unknown"
                    val address = intent.getStringExtra("deviceAddress") ?: ""
                    if (mDevices.none { it.address == address }) {
                        mDevices.add(BTDevice(name, address))
                        mAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun connectToDevice(device: BTDevice) {
        val intent = Intent(activity, BTService::class.java)
        intent.action = BTServiceTask.CONNECT_TO_DEVICE.toString()
        intent.putExtra("deviceAddress", device.address)
        activity?.startForegroundService(intent)
    }

    private fun sendServiceMessage(type: String) {
        activity?.let {
            val serviceIntent = Intent(it, BTService::class.java)
            serviceIntent.action = type
            startForegroundService(it, serviceIntent)
        }
    }
}
