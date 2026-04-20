package com.app.simostools

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {
    private val tag = "MainFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()

        DebugLog.d(tag, "onDestroy")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set background color
        view.setBackgroundColor(ColorList.BG_NORMAL.value)
        view.findViewById<ImageView>(R.id.imageMainLogo).setBackgroundColor(ColorList.BG_NORMAL.value)
        view.findViewById<View>(R.id.emptyBox).setBackgroundColor(ColorList.BT_RIM.value)

        DebugLog.d(tag, "onViewCreated")
    }

    override fun onResume() {
        super.onResume()

        if(!ConfigSettings.AUTO_LOG.toBoolean())
            sendServiceMessage(BTServiceTask.DO_STOP_TASK.toString())

        DebugLog.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()

        DebugLog.d(tag, "onPause")
    }

    private fun sendServiceMessage(type: String) {
        activity?.let {
            val serviceIntent = Intent(it, BTService::class.java)
            serviceIntent.action = type
            startForegroundService(it, serviceIntent)
        }
    }
}
