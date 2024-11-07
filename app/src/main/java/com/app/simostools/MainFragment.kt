package com.app.simostools

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {
    private val TAG = "MainFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()

        DebugLog.d(TAG, "onDestroy")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loggingButton = view.findViewById<SwitchButton>(R.id.buttonMainLogging)
        loggingButton.apply {
            paintBG.color = ColorList.BT_BG.value
            paintRim.color = ColorList.BT_RIM.value
            setTextColor(ColorList.BT_TEXT.value)
            setOnClickListener {
                sendServiceMessage(BTServiceTask.DO_START_LOG.toString())
                findNavController().navigate(R.id.action_MainFragment_to_LoggingFragment)
            }
        }

        val flashingButton = view.findViewById<SwitchButton>(R.id.buttonMainFlashing)
        flashingButton.apply {
            paintBG.color = ColorList.BT_BG.value
            paintRim.color = ColorList.BT_RIM.value
            setTextColor(ColorList.BT_TEXT.value)
            setOnClickListener {
                findNavController().navigate(R.id.action_MainFragment_to_FlashingFragment)
            }
        }

        val logViewerButton = view.findViewById<SwitchButton>(R.id.buttonLogViewer)
        logViewerButton.apply {
            paintBG.color = ColorList.BT_BG.value
            paintRim.color = ColorList.BT_RIM.value
            setTextColor(ColorList.BT_TEXT.value)
            setOnClickListener {
                gLogViewerLoadLast = false
                findNavController().navigate(R.id.action_MainFragment_to_LogViewerFragment)
            }
        }

        val utilitiesButton = view.findViewById<SwitchButton>(R.id.buttonMainUtilities)
        utilitiesButton.apply {
            paintBG.color = ColorList.BT_BG.value
            paintRim.color = ColorList.BT_RIM.value
            setTextColor(ColorList.BT_TEXT.value)
            setOnClickListener {
                findNavController().navigate(R.id.action_MainFragment_to_UtilitiesFragment)
            }
        }

        val settingsButton = view.findViewById<SwitchButton>(R.id.buttonMainSettings)
        settingsButton.apply {
            paintBG.color = ColorList.BT_BG.value
            paintRim.color = ColorList.BT_RIM.value
            setTextColor(ColorList.BT_TEXT.value)
            setOnClickListener {
                TempPIDS.reset(context)
                ColorSettings.resetColors()
                findNavController().navigate(R.id.action_MainFragment_to_SettingsFragment)
            }
        }

        val exitButton = view.findViewById<SwitchButton>(R.id.buttonMainExit)
        exitButton.apply {
            paintBG.color = ColorList.BT_BG.value
            paintRim.color = ColorList.BT_RIM.value
            setTextColor(ColorList.BT_TEXT.value)
            setOnClickListener {
                //Write pid default files
                UDSLoggingMode.values().forEach { mode ->
                    //write current PID list
                    PIDCSVFile.write(
                        getString(R.string.filename_pid_csv, mode.cfgName),
                        requireActivity(),
                        PIDs.getList(mode),
                        true
                    )
                }

                //write current PID list
                PIDCSVFile.write(
                    getString(R.string.filename_pid_csv, "DSG"),
                    requireActivity(),
                    PIDs.getDSGList(),
                    true
                )

                //clear globals
                gLogViewerData = null
                gUtilitiesMsgList = emptyArray()
                gFlashMsgList = emptyArray()

                //stop timer
                (activity as MainActivity).stopGUITimer()

                //Stop our BT Service
                sendServiceMessage(BTServiceTask.STOP_SERVICE.toString())
                requireActivity().finish()
            }
        }

        //Set background color
        view.setBackgroundColor(ColorList.BG_NORMAL.value)
        view.findViewById<ImageView>(R.id.imageMainLogo)
            .setBackgroundColor(ColorList.BG_NORMAL.value)

        DebugLog.d(TAG, "onViewCreated")
    }

    override fun onResume() {
        super.onResume()

        if (!ConfigSettings.AUTO_LOG.toBoolean())
            sendServiceMessage(BTServiceTask.DO_STOP_TASK.toString())

        DebugLog.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()

        DebugLog.d(TAG, "onPause")
    }

    private fun sendServiceMessage(type: String) {
        activity?.let {
            val serviceIntent = Intent(it, BTService::class.java)
            serviceIntent.action = type
            startForegroundService(it, serviceIntent)
        }
    }
}