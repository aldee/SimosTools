package com.app.simostools

data class DTCStruct(
    val code: Int,
    val pcode: String,
    val name: String,
    val symbol: String
)

object DTCs {
    private val TAG = "DTCs"
    var list: List<DTCStruct?> = listOf(
        DTCStruct(14823, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_ACQ_KNK.14823"),
        DTCStruct(
            14824,
            "P157500",
            "Right Electro-Hydraulic Engine Mount Solenoid Valve Short circuit to B+",
            "SV_ERR_SYM_AEB_SCP_0.14824"
        ),
        DTCStruct(
            14825,
            "P157600",
            "Right Electro-Hydraulic Engine Mount Solenoid Valve Short circuit to ground",
            "SV_ERR_SYM_AEB_SCG_0.14825"
        ),
        DTCStruct(
            14826,
            "P157700",
            "Right Electro-Hydraulic Engine Mount Solenoid Valve Open circuit",
            "SV_ERR_SYM_AEB_OC_0.14826"
        ),
        DTCStruct(
            14827,
            "P157100",
            "Left Electro-Hydraulic Engine Mount Solenoid Valve Short circuit to B+",
            "SV_ERR_SYM_AEB_SCP_1.14827"
        ),
        DTCStruct(
            14828,
            "P157200",
            "Left Electro-Hydraulic Engine Mount Solenoid Valve Short circuit to ground",
            "SV_ERR_SYM_AEB_SCG_1.14828"
        ),
        DTCStruct(
            14829,
            "P157300",
            "Left Electro-Hydraulic Engine Mount Solenoid Valve Open circuit",
            "SV_ERR_SYM_AEB_OC_1.14829"
        ),
        DTCStruct(
            14830,
            "P241400",
            "02 Sensor Exhaust Sample Error, Bank 1 Sensor 1",
            "SV_ERR_SYM_AIR_LSL_UP_1.14830"
        ),
        DTCStruct(14831, "P138700", "Ctrl. module faulty", "SV_ERR_SYM_AMP_SCP.14831"),
        DTCStruct(14832, "P138700", "Ctrl. module faulty", "SV_ERR_SYM_AMP_SCG.14832"),
        DTCStruct(14842, "P032700", "Knock Sensor 1 Circ. Low Input", "SV_ERR_SYM_BAS_KNK_0.14842"),
        DTCStruct(14844, "P057200", "Brake Switch A Circuit Low", "SV_ERR_SYM_BLS_BTS_PLAUS.14844"),
        DTCStruct(
            14846,
            "P256300",
            "Turbocharger Boost Control Position Sensor Circuit Range/Performance",
            "SV_ERR_SYM_BPA_AD_BOL_1.14846"
        ),
        DTCStruct(
            14847,
            "P226300",
            "Turbocharger/Supercharger Boost System Performance",
            "SV_ERR_SYM_BPA_AD_FIRST_1.14847"
        ),
        DTCStruct(
            14849,
            "P004500",
            "Turbocharger/Supercharger Boost Control Solenoid A Circuit/Open",
            "SV_ERR_SYM_BPA_DR_OC_1.14849"
        ),
        DTCStruct(
            14851,
            "P334A00",
            "Charge pressure actuator Electrical error",
            "SV_ERR_SYM_BPA_DR_SC_1.14851"
        ),
        DTCStruct(
            14863,
            "P036600",
            "Camshaft Pos.Sensor B Circ. (Bank 1) Range/Performance",
            "SV_ERR_SYM_CAM_PER_EX_1.14863"
        ),
        DTCStruct(
            14864,
            "P034100",
            "Camshaft Pos.Sensor Circ. Range/Performance",
            "SV_ERR_SYM_CAM_PER_IN_1.14864"
        ),
        DTCStruct(
            14865,
            "P036500",
            "Camshaft Position Sensor B Circuit (Bank 1)",
            "SV_ERR_SYM_CAM_NO_SIG_EX_1.14865"
        ),
        DTCStruct(
            14866,
            "P034000",
            "Camshaft Position Sensor Circuit",
            "SV_ERR_SYM_CAM_NO_SIG_IN_1.14866"
        ),
        DTCStruct(
            14867,
            "P001700",
            "Crankshaft Position-Camshaft Position Correlation Bank 1 Sensor B",
            "SV_ERR_SYM_CAM_REF_CRK_EX_1.14867"
        ),
        DTCStruct(
            14868,
            "P001600",
            "Crankshaft Position-Camshaft Position Correlation Bank 1 Sensor A",
            "SV_ERR_SYM_CAM_REF_CRK_IN_1.14868"
        ),
        DTCStruct(
            14869,
            "P001400",
            "B Camshaft Position (Bank1) Timing over-advanced or System Performance",
            "SV_ERR_SYM_CAM_STAT_VCP_EX_1.14869"
        ),
        DTCStruct(
            14870,
            "P001100",
            "A Camshaft Position (Bank1) Timing over-advanced or System Performance",
            "SV_ERR_SYM_CAM_STAT_VCP_IN_1.14870"
        ),
        DTCStruct(
            14871,
            "P036600",
            "Camshaft Pos.Sensor B Circ. (Bank 1) Range/Performance",
            "SV_ERR_SYM_CAM_SYN_CRK_EX_1.14871"
        ),
        DTCStruct(
            14872,
            "P034100",
            "Camshaft Pos.Sensor Circ. Range/Performance",
            "SV_ERR_SYM_CAM_SYN_CRK_IN_1.14872"
        ),
        DTCStruct(
            14873,
            "P036600",
            "Camshaft Pos.Sensor B Circ. (Bank 1) Range/Performance",
            "SV_ERR_SYM_CAM_SYN_EX_1.14873"
        ),
        DTCStruct(
            14874,
            "P034100",
            "Camshaft Pos.Sensor Circ. Range/Performance",
            "SV_ERR_SYM_CAM_SYN_IN_1.14874"
        ),
        DTCStruct(
            14913,
            "P023400",
            "Turbo/Super Charger Overboost Condition",
            "SV_ERR_SYM_CAP_H.14913"
        ),
        DTCStruct(14914, "P029900", "Turbo/Super Charger Underboost", "SV_ERR_SYM_CAP_L.14914"),
        DTCStruct(
            14916,
            "P042000",
            "Catalyst System,Bank1 Efficiency Below Threshold",
            "SV_ERR_SYM_CAT_DIAG_1.14916"
        ),
        DTCStruct(
            14920,
            "P172400",
            "Signal for starter lock Short circuit to ground",
            "SV_ERR_SYM_CLU_SWI_2_SCG.14920"
        ),
        DTCStruct(14922, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_CONV_PLAUS.14922"),
        DTCStruct(14923, "P160900", "Crash shut-off was triggered", "SV_ERR_SYM_CRASH.14923"),
        DTCStruct(
            14925,
            "P033500",
            "Crankshaft Position Sensor A Circuit",
            "SV_ERR_SYM_CRK_NO_SIG.14925"
        ),
        DTCStruct(
            14926,
            "P033500",
            "Crankshaft Position Sensor A Circuit",
            "SV_ERR_SYM_CRK_SHO_LEVEL.14926"
        ),
        DTCStruct(
            14927,
            "P033600",
            "Crankshaft Position Sensor A Circuit Range/Performance",
            "SV_ERR_SYM_CRK_SYN.14927"
        ),
        DTCStruct(
            14928,
            "P033900",
            "Crankshaft Position Sensor A Circuit Intermittent",
            "SV_ERR_SYM_CRK_SYN_STST.14928"
        ),
        DTCStruct(
            14929,
            "P033600",
            "Crankshaft Position Sensor A Circuit Range/Performance",
            "SV_ERR_SYM_CRK_TOOTH_NR.14929"
        ),
        DTCStruct(
            14930,
            "P033600",
            "Crankshaft Position Sensor A Circuit Range/Performance",
            "SV_ERR_SYM_CRK_TOOTH_PER.14930"
        ),
        DTCStruct(
            14931,
            "P033600",
            "Crankshaft Position Sensor A Circuit Range/Performance",
            "SV_ERR_SYM_CRK_TOOTH_NR_STST.14931"
        ),
        DTCStruct(
            14939,
            "P308200",
            "Clutch position sensor Implausible signal",
            "SV_ERR_SYM_CS_PV.14939"
        ),
        DTCStruct(
            14955,
            "P209700",
            "Post Catalyst Fuel Trim System Bank 1 Too Rich",
            "SV_ERR_SYM_LAM_ADJ_DIAG_MAX_1.14955"
        ),
        DTCStruct(
            14956,
            "P209600",
            "Post Catalyst Fuel Trim System Bank1 Too Lean",
            "SV_ERR_SYM_LAM_ADJ_DIAG_MIN_1.14956"
        ),
        DTCStruct(
            14961,
            "P013300",
            "O2 Sensor Circ.,Bank1-Sensor1 Slow Response",
            "SV_ERR_SYM_DYN_LS_UP_1.14961"
        ),
        DTCStruct(14982, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_ECU_1.14982"),
        DTCStruct(
            14983,
            "P060100",
            "Internal Contr.Module Memory Check Sum Error",
            "SV_ERR_SYM_ECU_2_CKS_SW.14983"
        ),
        DTCStruct(
            14984,
            "P060100",
            "Internal Contr.Module Memory Check Sum Error",
            "SV_ERR_SYM_ECU_2_CKS_CAL.14984"
        ),
        DTCStruct(
            14985,
            "P060100",
            "Internal Contr.Module Memory Check Sum Error",
            "SV_ERR_SYM_ECU_2_EEPROM.14985"
        ),
        DTCStruct(14989, "P164000", "Ctrl. module faulty", "SV_ERR_SYM_ECU_5.14989"),
        DTCStruct(
            14990,
            "P157000",
            "Engine Control Module (ECM) disabled",
            "SV_ERR_SYM_ECU_6.14990"
        ),
        DTCStruct(
            14993,
            "P025D00",
            "Fuel Pump Module Control Circuit High",
            "SV_ERR_SYM_EFPPWM_SCP.14993"
        ),
        DTCStruct(
            14994,
            "P025C00",
            "Fuel Pump Module Control Circuit Low",
            "SV_ERR_SYM_EFPPWM_SCG.14994"
        ),
        DTCStruct(
            14995,
            "P025A00",
            "Fuel Pump Module Control Circuit/Open",
            "SV_ERR_SYM_EFPPWM_OC.14995"
        ),
        DTCStruct(
            14996,
            "P304500",
            "Fuel pump electronics Faulty",
            "SV_ERR_SYM_EFPPWM_FB_A_INT.14996"
        ),
        DTCStruct(
            14997,
            "P308E00",
            "Fuel pump electronics Excess temp.",
            "SV_ERR_SYM_EFPPWM_FB_A_PUMP_1.14997"
        ),
        DTCStruct(
            14998,
            "P308D00",
            "Fuel pump Engine speed too low",
            "SV_ERR_SYM_EFPPWM_FB_A_PUMP_2.14998"
        ),
        DTCStruct(
            14999,
            "P304300",
            "Fuel pump mechanical malfunction",
            "SV_ERR_SYM_EFPPWM_FB_A_VB.14999"
        ),
        DTCStruct(15000, "P304400", "Fuel pump Short circuit", "SV_ERR_SYM_EFPPWM_FB_B_1.15000"),
        DTCStruct(
            15001,
            "P307300",
            "Fuel pump Electrical error in circuit",
            "SV_ERR_SYM_EFPPWM_FB_B_2.15001"
        ),
        DTCStruct(
            15002,
            "P307700",
            "Fuel Pump Electronics, voltage supply Electrical error",
            "SV_ERR_SYM_EFPPWM_FB_B_3.15002"
        ),
        DTCStruct(
            15005,
            "P008900",
            "Fuel Pressure Regulator 1 Performance",
            "SV_ERR_SYM_FUP_EFP_CTL_I.15005"
        ),
        DTCStruct(
            15007,
            "P008900",
            "Fuel Pressure Regulator 1 Performance",
            "SV_ERR_SYM_FUP_EFP_CTL_DIF.15007"
        ),
        DTCStruct(
            15008,
            "P044500",
            "Evaporativ Emiss.System Purge Control Valve Circ. Shorted",
            "SV_ERR_SYM_EL_CPS_SCP.15008"
        ),
        DTCStruct(
            15009,
            "P044500",
            "Evaporativ Emiss.System Purge Control Valve Circ. Shorted",
            "SV_ERR_SYM_EL_CPS_SCG.15009"
        ),
        DTCStruct(
            15010,
            "P044400",
            "Evaporativ Emiss.System Purge Control Valve Circ. Open",
            "SV_ERR_SYM_EL_CPS_OC.15010"
        ),
        DTCStruct(
            15013,
            "P003300",
            "Turbo Charger Bypass Valve Control Circuit",
            "SV_ERR_SYM_EL_RCL_ACR_SCP.15013"
        ),
        DTCStruct(
            15014,
            "P003400",
            "Turbo Charger Bypass Valve Control Circuit Low",
            "SV_ERR_SYM_EL_RCL_ACR_SCG.15014"
        ),
        DTCStruct(
            15015,
            "P003300",
            "Turbo Charger Bypass Valve Control Circuit",
            "SV_ERR_SYM_EL_RCL_ACR_OC.15015"
        ),
        DTCStruct(
            15025,
            "P181C00",
            "Gear recognition sensor Implausible signal",
            "SV_ERR_SYM_FRQ_NEUT_GEAR_H.15025"
        ),
        DTCStruct(
            15026,
            "P181C00",
            "Gear recognition sensor Implausible signal",
            "SV_ERR_SYM_FRQ_NEUT_GEAR_L.15026"
        ),
        DTCStruct(15027, "P125000", "Fuel level too low", "SV_ERR_SYM_FRS_ACT.15027"),
        DTCStruct(
            15028,
            "P017100",
            "Fuel Trim,Bank1 System too Lean",
            "SV_ERR_SYM_FSD_LAM_LIM_MAX_1.15028"
        ),
        DTCStruct(
            15029,
            "P017200",
            "Fuel Trim,Bank1 System too Rich",
            "SV_ERR_SYM_FSD_LAM_LIM_MIN_1.15029"
        ),
        DTCStruct(
            15030,
            "P218700",
            "System too lean at idle, Bank 1",
            "SV_ERR_SYM_FSD_MAX_1.15030"
        ),
        DTCStruct(
            15031,
            "P218800",
            "System too rich at idle, Bank 1",
            "SV_ERR_SYM_FSD_MIN_1.15031"
        ),
        DTCStruct(
            15032,
            "P21F800",
            "Fuel Control System B Too Lean at Idle Bank 1",
            "SV_ERR_SYM_FSD_OPT_MAX_1.15032"
        ),
        DTCStruct(
            15033,
            "P21F900",
            "Fuel Control System B Too Rich at Idle Bank 1",
            "SV_ERR_SYM_FSD_OPT_MIN_1.15033"
        ),
        DTCStruct(
            15034,
            "P019000",
            "Fuel Rail Pressure Sensor A Circuit",
            "SV_ERR_SYM_FUP_H.15034"
        ),
        DTCStruct(
            15035,
            "P019200",
            "Fuel Rail Pressure Sensor A Circuit Low Input",
            "SV_ERR_SYM_FUP_L.15035"
        ),
        DTCStruct(
            15038,
            "P253900",
            "Low Pressure Fuel System Sensor Circuit",
            "SV_ERR_SYM_FUP_EFP_H.15038"
        ),
        DTCStruct(
            15039,
            "P254100",
            "Low Pressure Fuel System Sensor Circuit Low Input",
            "SV_ERR_SYM_FUP_EFP_L.15039"
        ),
        DTCStruct(
            15041,
            "P119A00",
            "Fuel pressure sensor Malfunction",
            "SV_ERR_SYM_FUP_MFP_PLAUS_H.15041"
        ),
        DTCStruct(
            15042,
            "P119A00",
            "Fuel pressure sensor Malfunction",
            "SV_ERR_SYM_FUP_MFP_PLAUS_L.15042"
        ),
        DTCStruct(
            15044,
            "P019100",
            "Fuel Rail Pressure Sensor A Circuit Range/Performance",
            "SV_ERR_SYM_FUP_ORNG_TOL_2.15044"
        ),
        DTCStruct(
            15048,
            "P048000",
            "Cooling Fan 1 Control Circuit",
            "SV_ERR_SYM_ECF_EL_OC_1.15048"
        ),
        DTCStruct(
            15053,
            "U100800",
            "Diagnostic interface for data bus Read out DTC",
            "SV_ERR_SYM_GEN_LOAD_CAN.15053"
        ),
        DTCStruct(
            15057,
            "P008800",
            "Fuel Rail/System Pressure - Too High",
            "SV_ERR_SYM_H_PRS_SYS_TOL_1.15057"
        ),
        DTCStruct(
            15058,
            "P008700",
            "Fuel Rail/System Pressure - Too Low",
            "SV_ERR_SYM_H_PRS_SYS_BOL_1.15058"
        ),
        DTCStruct(
            15059,
            "P008700",
            "Fuel Rail/System Pressure - Too Low",
            "SV_ERR_SYM_H_PRS_SYS_BOL_2.15059"
        ),
        DTCStruct(
            15060,
            "P230200",
            "Ignition Coil A Secondary Circuit",
            "SV_ERR_SYM_IGC_OC_0.15060"
        ),
        DTCStruct(
            15061,
            "P230500",
            "Ignition Coil B Secondary Circuit",
            "SV_ERR_SYM_IGC_OC_3.15061"
        ),
        DTCStruct(
            15062,
            "P230800",
            "Ignition Coil C Secondary Circuit",
            "SV_ERR_SYM_IGC_OC_1.15062"
        ),
        DTCStruct(
            15063,
            "P231100",
            "Ignition Coil D Secondary Circuit",
            "SV_ERR_SYM_IGC_OC_2.15063"
        ),
        DTCStruct(
            15064,
            "P230000",
            "Ignition Coil A Primary Control Circuit Low",
            "SV_ERR_SYM_IGC_SCG_0.15064"
        ),
        DTCStruct(
            15065,
            "P230300",
            "Ignition Coil B Primary Control Circuit Low",
            "SV_ERR_SYM_IGC_SCG_3.15065"
        ),
        DTCStruct(
            15066,
            "P230600",
            "Ignition Coil C Primary Control Circuit Low",
            "SV_ERR_SYM_IGC_SCG_1.15066"
        ),
        DTCStruct(
            15067,
            "P230900",
            "Ignition Coil D Primary Control Circuit Low",
            "SV_ERR_SYM_IGC_SCG_2.15067"
        ),
        DTCStruct(
            15068,
            "P230100",
            "Ignition Coil A Primary Control Circuit High",
            "SV_ERR_SYM_IGC_SCP_0.15068"
        ),
        DTCStruct(
            15069,
            "P230400",
            "Ignition Coil B Primary Control Circuit High",
            "SV_ERR_SYM_IGC_SCP_3.15069"
        ),
        DTCStruct(
            15070,
            "P230700",
            "Ignition Coil C Primary Control Circuit High",
            "SV_ERR_SYM_IGC_SCP_1.15070"
        ),
        DTCStruct(
            15071,
            "P231000",
            "Ignition Coil D Primary Control Circuit High",
            "SV_ERR_SYM_IGC_SCP_2.15071"
        ),
        DTCStruct(
            15072,
            "P157000",
            "Engine Control Module (ECM) disabled",
            "SV_ERR_SYM_IMMO_LOCK.15072"
        ),
        DTCStruct(
            15073,
            "P157000",
            "Engine Control Module (ECM) disabled",
            "SV_ERR_SYM_IMMO_DEAC.15073"
        ),
        DTCStruct(15074, "P130A00", "Hide cylinder", "SV_ERR_SYM_INH_INJ_MIS.15074"),
        DTCStruct(
            15077,
            "P050700",
            "Idle Control System RPM Higher than Expected",
            "SV_ERR_SYM_ISC_H.15077"
        ),
        DTCStruct(
            15078,
            "P050600",
            "Idle Control System RPM Lower than Expected",
            "SV_ERR_SYM_ISC_L.15078"
        ),
        DTCStruct(
            15082,
            "P233600",
            "Cylinder #1 Above Knock Threshold",
            "SV_ERR_SYM_KNK_RTD_LIM_0.15082"
        ),
        DTCStruct(
            15083,
            "P233700",
            "Cylinder #2 Above Knock Threshold",
            "SV_ERR_SYM_KNK_RTD_LIM_3.15083"
        ),
        DTCStruct(
            15084,
            "P233800",
            "Cylinder #3 Above Knock Threshold",
            "SV_ERR_SYM_KNK_RTD_LIM_1.15084"
        ),
        DTCStruct(
            15085,
            "P233900",
            "Cylinder #4 Above Knock Threshold",
            "SV_ERR_SYM_KNK_RTD_LIM_2.15085"
        ),
        DTCStruct(
            15087,
            "P008B00",
            "Low Pressure Fuel System Pressure - Too High",
            "SV_ERR_SYM_FUP_EFP_ORNG_TOL_1.15087"
        ),
        DTCStruct(
            15088,
            "P008A00",
            "Low Pressure Fuel System Pressure - Too Low",
            "SV_ERR_SYM_FUP_EFP_ORNG_BOL.15088"
        ),
        DTCStruct(
            15090,
            "P069100",
            "Cooling Fan 1 Control Circuit Low",
            "SV_ERR_SYM_ECF_EL_SCG_1.15090"
        ),
        DTCStruct(
            15099,
            "P006800",
            "MAP/MAF - Throttle Position Correlation",
            "SV_ERR_SYM_LOAD_PLAUS.15099"
        ),
        DTCStruct(
            15100,
            "P006800",
            "MAP/MAF - Throttle Position Correlation",
            "SV_ERR_SYM_TPS_PLAUS.15100"
        ),
        DTCStruct(
            15101,
            "P006800",
            "MAP/MAF - Throttle Position Correlation",
            "SV_ERR_SYM_AMP_TPS_PLAUS.15101"
        ),
        DTCStruct(
            15120,
            "P010800",
            "Manifold Abs.Pressure or Bar.Pressure High Input",
            "SV_ERR_SYM_MAP_SCP.15120"
        ),
        DTCStruct(
            15121,
            "P010700",
            "Manifold Abs.Pressure or Bar.Pressure Low Input",
            "SV_ERR_SYM_MAP_SCG.15121"
        ),
        DTCStruct(15125, "P030100", "Cyl.1 Misfire Detected", "SV_ERR_SYM_MIS_0.15125"),
        DTCStruct(
            15126,
            "P069200",
            "Cooling Fan 1 Control Circuit High",
            "SV_ERR_SYM_ECF_EL_SCP_1.15126"
        ),
        DTCStruct(15128, "P030200", "Cyl.2 Misfire Detected", "SV_ERR_SYM_MIS_3.15128"),
        DTCStruct(
            15129,
            "P194400",
            "Coolant fan control module 1 Excess temp.",
            "SV_ERR_SYM_ECF_FB_1_1.15129"
        ),
        DTCStruct(
            15130,
            "P195000",
            "Coolant fan for coolant difficulty of movement/blocked",
            "SV_ERR_SYM_ECF_FB_1_2.15130"
        ),
        DTCStruct(15131, "P030300", "Cyl.3 Misfire Detected", "SV_ERR_SYM_MIS_1.15131"),
        DTCStruct(
            15132,
            "P194500",
            "Coolant Fan Control (FC) Control Module 1, fan activation Short circuit",
            "SV_ERR_SYM_ECF_FB_1_3.15132"
        ),
        DTCStruct(
            15133,
            "P194600",
            "Coolant fan control module 1 Faulty/open circuit in voltage supply",
            "SV_ERR_SYM_ECF_FB_1_4.15133"
        ),
        DTCStruct(15134, "P030400", "Cyl.4 Misfire Detected", "SV_ERR_SYM_MIS_2.15134"),
        DTCStruct(
            15137,
            "P030000",
            "Random/Multiple Cylinder Misfire Detected",
            "SV_ERR_SYM_MIS_MPL.15137"
        ),
        DTCStruct(15139, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_MON_3.15139"),
        DTCStruct(
            15145,
            "P154500",
            "Throttle valve control system Malfunction",
            "SV_ERR_SYM_ETC_PWM_2.15145"
        ),
        DTCStruct(
            15146,
            "P154500",
            "Throttle valve control system Malfunction",
            "SV_ERR_SYM_TPS_DIF.15146"
        ),
        DTCStruct(15148, "P021900", "Engine Overspeed Condition", "SV_ERR_SYM_N_MAX_VVL_H.15148"),
        DTCStruct(15149, "P021900", "Engine Overspeed Condition", "SV_ERR_SYM_N_MAX_VVL_L.15149"),
        DTCStruct(
            15152,
            "P150C00",
            "RPM request from transmission cannot be performed",
            "SV_ERR_SYM_N_SP_IS_TCU.15152"
        ),
        DTCStruct(
            15154,
            "P15A100",
            "Neutral position sensor Implausible signal",
            "SV_ERR_SYM_NEUT_GEAR_COAST.15154"
        ),
        DTCStruct(
            15155,
            "P15A100",
            "Neutral position sensor Implausible signal",
            "SV_ERR_SYM_NEUT_GEAR_PU.15155"
        ),
        DTCStruct(15157, "P160A00", "Ctrl. module faulty", "SV_ERR_SYM_NVMCRYPT_KEY.15157"),
        DTCStruct(15158, "P160A00", "Ctrl. module faulty", "SV_ERR_SYM_NVMCRYPT_PLAUS.15158"),
        DTCStruct(15159, "P160A00", "Ctrl. module faulty", "SV_ERR_SYM_NVMCRYPT_SW.15159"),
        DTCStruct(
            15170,
            "P019800",
            "Engine Oil Temperature Sensor Circuit high",
            "SV_ERR_SYM_OIL_SENS_EL.15170"
        ),
        DTCStruct(
            15171,
            "P019600",
            "Engine Oil Temperature Sensor Circuit range/performance",
            "SV_ERR_SYM_OIL_SENS_PLAUS.15171"
        ),
        DTCStruct(15177, "P039B00", "Cylinder 1 Pressure Too High", "SV_ERR_SYM_PI_0.15177"),
        DTCStruct(15178, "P03A500", "Cylinder 2 Pressure Too High", "SV_ERR_SYM_PI_3.15178"),
        DTCStruct(15179, "P03AF00", "Cylinder 3 Pressure Too High", "SV_ERR_SYM_PI_1.15179"),
        DTCStruct(15180, "P03B900", "Cylinder 4 Pressure Too High", "SV_ERR_SYM_PI_2.15180"),
        DTCStruct(
            15181,
            "P226100",
            "Turbo Boost Pressure Not Detected Mechanical",
            "SV_ERR_SYM_PLAUS_CLOSE_RCL.15181"
        ),
        DTCStruct(
            15182,
            "P15A100",
            "Neutral position sensor Implausible signal",
            "SV_ERR_SYM_PLAUS_NEUT_GEAR_H.15182"
        ),
        DTCStruct(
            15183,
            "P15A100",
            "Neutral position sensor Implausible signal",
            "SV_ERR_SYM_PLAUS_NEUT_GEAR_L.15183"
        ),
        DTCStruct(
            15184,
            "P15A100",
            "Neutral position sensor Implausible signal",
            "SV_ERR_SYM_PLAUS_NEUT_GEAR_MID.15184"
        ),
        DTCStruct(
            15186,
            "P085100",
            "Park/Neutral Switch Input Circuit Low",
            "SV_ERR_SYM_PN_SCG.15186"
        ),
        DTCStruct(
            15187,
            "P085000",
            "Park/Neutral Switch Input Circuit",
            "SV_ERR_SYM_PN_SCP_OC.15187"
        ),
        DTCStruct(
            15188,
            "P164B00",
            "Oil pressure switch Malfunction",
            "SV_ERR_SYM_POIL_H_WARN.15188"
        ),
        DTCStruct(
            15189,
            "P164D00",
            "Reduced oil pressure switch Malfunction",
            "SV_ERR_SYM_POIL_L_WARN.15189"
        ),
        DTCStruct(
            15190,
            "P164A00",
            "Oil pressure switch Implausible signal",
            "SV_ERR_SYM_POIL_SWI_H_PLAUS.15190"
        ),
        DTCStruct(
            15191,
            "P164C00",
            "Reduced oil pressure switch Implausible signal",
            "SV_ERR_SYM_POIL_SWI_L_PLAUS.15191"
        ),
        DTCStruct(
            15192,
            "P201000",
            "Intake Manifold Runner Bank 1 Control Circuit High",
            "SV_ERR_SYM_PORT_SCP.15192"
        ),
        DTCStruct(
            15193,
            "P200900",
            "Intake Manifold Runner Bank 1 Control Circuit Low",
            "SV_ERR_SYM_PORT_SCG.15193"
        ),
        DTCStruct(
            15194,
            "P200800",
            "Intake Manifold Runner Bank 1 Control Circuit/Open",
            "SV_ERR_SYM_PORT_OC.15194"
        ),
        DTCStruct(
            15195,
            "P201500",
            "Intake Manifold Runner Position Sensor/Switch Circuit Range/Performance",
            "SV_ERR_SYM_PORT_AD_1.15195"
        ),
        DTCStruct(
            15196,
            "P201700",
            "Intake Manifold Runner Position Sensor/Switch Circuit High",
            "SV_ERR_SYM_PORT_FB_EL_H_1.15196"
        ),
        DTCStruct(
            15197,
            "P201400",
            "Intake Manifold Runner Position Sensor/Switch Circuit",
            "SV_ERR_SYM_PORT_FB_EL_L_1.15197"
        ),
        DTCStruct(
            15198,
            "P200400",
            "Intake Manifold Runner Control Bank 1 Stuck Open",
            "SV_ERR_SYM_PORT_MEC_DOWN_1.15198"
        ),
        DTCStruct(
            15200,
            "P200600",
            "Intake Manifold Runner Control Bank 1 Stuck Closed",
            "SV_ERR_SYM_PORT_MEC_UP_1.15200"
        ),
        DTCStruct(
            15209,
            "P164E00",
            "Oil pressure regulation valve Electrical error",
            "SV_ERR_SYM_PUMP_OIL_EL_SCP.15209"
        ),
        DTCStruct(
            15210,
            "P164E00",
            "Oil pressure regulation valve Electrical error",
            "SV_ERR_SYM_PUMP_OIL_EL_SCG.15210"
        ),
        DTCStruct(
            15211,
            "P164E00",
            "Oil pressure regulation valve Electrical error",
            "SV_ERR_SYM_PUMP_OIL_EL_OC.15211"
        ),
        DTCStruct(
            15212,
            "P023800",
            "Turbocharger Boost Sensor (A) Circ. High Input",
            "SV_ERR_SYM_PUT_SCP.15212"
        ),
        DTCStruct(
            15213,
            "P023700",
            "Turbocharger Boost Sensor (A) Circ. Low Input",
            "SV_ERR_SYM_PUT_SCG.15213"
        ),
        DTCStruct(
            15217,
            "U101000",
            "Electrical Parking Brake Control Module Read out DTC",
            "SV_ERR_SYM_PV_CLU_CAN_PBR.15217"
        ),
        DTCStruct(
            15218,
            "P212300",
            "Throttle/Pedal Pos. Sens./Switch D Circuit High Input",
            "SV_ERR_SYM_PVS_H_1.15218"
        ),
        DTCStruct(
            15219,
            "P212200",
            "Throttle/Pedal Pos. Sens./Switch D Circuit Low Input",
            "SV_ERR_SYM_PVS_L_1.15219"
        ),
        DTCStruct(
            15220,
            "P212800",
            "Throttle/Pedal Pos. Sens./Switch E Circuit High Input",
            "SV_ERR_SYM_PVS_H_2.15220"
        ),
        DTCStruct(
            15221,
            "P212700",
            "Throttle/Pedal Pos. Sens./Switch E Circuit Low Input",
            "SV_ERR_SYM_PVS_L_2.15221"
        ),
        DTCStruct(
            15222,
            "P213800",
            "Throttle/Pedal Position Sensor/Switch D/E Voltage Correlation",
            "SV_ERR_SYM_PVS_DRIFT.15222"
        ),
        DTCStruct(
            15224,
            "P213800",
            "Throttle/Pedal Position Sensor/Switch D/E Voltage Correlation",
            "SV_ERR_SYM_PVS_RATIO.15224"
        ),
        DTCStruct(
            15226,
            "P00AF00",
            "Turbocharger/Supercharger Boost Control A Module Performance",
            "SV_ERR_SYM_PWM_BPA_H_1.15226"
        ),
        DTCStruct(
            15227,
            "P00AF00",
            "Turbocharger/Supercharger Boost Control A Module Performance",
            "SV_ERR_SYM_PWM_BPA_L_1.15227"
        ),
        DTCStruct(
            15230,
            "P032600",
            "Knock Sensor 1 Circuit Range/Performance",
            "SV_ERR_SYM_REL_KNK_0.15230"
        ),
        DTCStruct(
            15236,
            "U102100",
            "Body computer 1/ veh. elec sys CM / cent elec. Read out DTC",
            "SV_ERR_SYM_WIP_LIN.15236"
        ),
        DTCStruct(15237, "P056200", "System Voltage Low Voltage", "SV_ERR_SYM_RLY_MAIN_DLY.15237"),
        DTCStruct(
            15239,
            "P068600",
            "EMC/PCM Power Relay Control Circuit Low",
            "SV_ERR_SYM_RLY_MAIN_PLAUS_OFF.15239"
        ),
        DTCStruct(
            15240,
            "P068700",
            "EMC/PCM Power Relay Control Circuit High",
            "SV_ERR_SYM_RLY_MAIN_PLAUS_ON.15240"
        ),
        DTCStruct(
            15242,
            "P068700",
            "EMC/PCM Power Relay Control Circuit High",
            "SV_ERR_SYM_RLY_MAIN_EL_SCP.15242"
        ),
        DTCStruct(
            15243,
            "P065900",
            "Actuator Supply Voltage A Circuit High",
            "SV_ERR_SYM_PWR_RLY_EL_SCP_0.15243"
        ),
        DTCStruct(
            15244,
            "P065800",
            "Actuator Supply Voltage A Circuit Low",
            "SV_ERR_SYM_PWR_RLY_EL_SCG_0.15244"
        ),
        DTCStruct(
            15245,
            "P065700",
            "Actuator Supply Voltage A Circuit/Open",
            "SV_ERR_SYM_PWR_RLY_EL_OL_0.15245"
        ),
        DTCStruct(
            15259,
            "P139700",
            "Sensor Wheel for Engine Speed adaptation limit reached",
            "SV_ERR_SYM_SEG_AD_ER.15259"
        ),
        DTCStruct(
            15261,
            "P219500",
            "O2 Sensor Signal Stuck Lean Bank 1 Sensor 1",
            "SV_ERR_SYM_SHIFT_AFL_LSL_UP_1.15261"
        ),
        DTCStruct(
            15262,
            "P219600",
            "O2 Sensor Signal Stuck Rich Bank 1 Sensor 1",
            "SV_ERR_SYM_SHIFT_AFR_LSL_UP_1.15262"
        ),
        DTCStruct(
            15263,
            "P15A000",
            "Neutral position sensor Electrical error",
            "SV_ERR_SYM_SIG_NEUT_GEAR_H.15263"
        ),
        DTCStruct(
            15264,
            "P15A000",
            "Neutral position sensor Electrical error",
            "SV_ERR_SYM_SIG_NEUT_GEAR_L.15264"
        ),
        DTCStruct(
            15265,
            "P209100",
            "B Camshaft Position Actuator Control Circuit(Bank1) High",
            "SV_ERR_SYM_SLV_SCP_VCP_EX_1.15265"
        ),
        DTCStruct(
            15266,
            "P104900",
            "Bank1, camshaft adjustment valve (exhaust) Short circuit to ground",
            "SV_ERR_SYM_SLV_SCG_VCP_EX_1.15266"
        ),
        DTCStruct(
            15267,
            "P105000",
            "Bank1, camshaft adjustment valve (exhaust) Open circuit",
            "SV_ERR_SYM_SLV_OC_VCP_EX_1.15267"
        ),
        DTCStruct(
            15268,
            "P152600",
            "Bank1, camshaft adjustment Short circuit to B+",
            "SV_ERR_SYM_SLV_SCP_VCP_IN_1.15268"
        ),
        DTCStruct(
            15269,
            "P152700",
            "Bank1, camshaft adjustment Short circuit to ground",
            "SV_ERR_SYM_SLV_SCG_VCP_IN_1.15269"
        ),
        DTCStruct(
            15270,
            "P152800",
            "Bank1, camshaft adjustment Open circuit",
            "SV_ERR_SYM_SLV_OC_VCP_IN_1.15270"
        ),
        DTCStruct(
            15277,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_SPI_PBK_IV_CYC_0.15277"
        ),
        DTCStruct(
            15281,
            "P308800",
            "Starter relay Electrical error in circuit",
            "SV_ERR_SYM_ST_CTL.15281"
        ),
        DTCStruct(
            15282,
            "P305400",
            "Starter does not turn Mechanically blocked or electrical malfunction",
            "SV_ERR_SYM_ST_DFCT.15282"
        ),
        DTCStruct(
            15283,
            "P167E00",
            "Start/stop restart Maximum start time exceeded",
            "SV_ERR_SYM_ST_NOT_OK.15283"
        ),
        DTCStruct(
            15284,
            "P253500",
            "Ignition Switch Run/ Start Position Curciut High",
            "SV_ERR_SYM_ST_REQ_PLAUS_H.15284"
        ),
        DTCStruct(
            15288,
            "P304600",
            "Starter relay 1 Electrical malfunction in circuit (relay stuck/does not switch)",
            "SV_ERR_SYM_STR_1_DFCT.15288"
        ),
        DTCStruct(15289, "P061500", "Starter Relay Circuit", "SV_ERR_SYM_STR_1_OC.15289"),
        DTCStruct(15290, "P061600", "Starter Relay Circuit Low", "SV_ERR_SYM_STR_1_SCG.15290"),
        DTCStruct(15291, "P061700", "Starter Relay Circuit High", "SV_ERR_SYM_STR_1_SCP.15291"),
        DTCStruct(
            15292,
            "P305000",
            "Starter relay 2 Electrical malfunction in circuit (relay stuck/does not switch)",
            "SV_ERR_SYM_STR_2_DFCT.15292"
        ),
        DTCStruct(
            15293,
            "P304900",
            "Activation starter relay 2 Open circuit",
            "SV_ERR_SYM_STR_2_OC.15293"
        ),
        DTCStruct(
            15294,
            "P304800",
            "Activation starter relay 2 Short circuit to ground",
            "SV_ERR_SYM_STR_2_SCG.15294"
        ),
        DTCStruct(
            15295,
            "P304700",
            "Activation starter relay 2 Short circuit to B+",
            "SV_ERR_SYM_STR_2_SCP.15295"
        ),
        DTCStruct(
            15296,
            "P305200",
            "Starter activation, return message terminal 50 Short circuit to B+",
            "SV_ERR_SYM_STR_FB_H.15296"
        ),
        DTCStruct(
            15297,
            "P305300",
            "Starter activation, return message terminal 50 Short circuit to ground/open circuit",
            "SV_ERR_SYM_STR_FB_L.15297"
        ),
        DTCStruct(
            15306,
            "P139700",
            "Sensor Wheel for Engine Speed adaptation limit reached",
            "SV_ERR_SYM_T_SEG_ER.15306"
        ),
        DTCStruct(
            15313,
            "P011800",
            "Engine Coolant Temperature Sensor 1 Circuit High",
            "SV_ERR_SYM_LTS_EL_H_TCE.15313"
        ),
        DTCStruct(
            15314,
            "P011700",
            "Engine Coolant Temperature Sensor 1 Circuit Low",
            "SV_ERR_SYM_LTS_EL_L_TCE.15314"
        ),
        DTCStruct(
            15315,
            "U042500",
            "Invalid Data Received From Auxiliary Heater Control Module",
            "SV_ERR_SYM_THEAT_CAN_ICH.15315"
        ),
        DTCStruct(
            15318,
            "P011600",
            "Engine Coolant Temperature Sensor 1 Circuit Range/Performance",
            "SV_ERR_SYM_TLTS_STUCK_L_TCE.15318"
        ),
        DTCStruct(
            15319,
            "P011600",
            "Engine Coolant Temperature Sensor 1 Circuit Range/Performance",
            "SV_ERR_SYM_TLTS_STUCK_H_TCE.15319"
        ),
        DTCStruct(
            15320,
            "P181E00",
            "Gear recognition sensor Gear position implausible",
            "SV_ERR_SYM_GEAR_SIG_PLAUS_CUS.15320"
        ),
        DTCStruct(
            15321,
            "P181D00",
            "Gear recognition sensor Error",
            "SV_ERR_SYM_GEAR_SENS_PLAUS_CUS.15321"
        ),
        DTCStruct(
            15323,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_TCS_NPLAU.15323"
        ),
        DTCStruct(
            15324,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_TCS_WRG_REQ.15324"
        ),
        DTCStruct(
            15326,
            "P168200",
            "Databus drivetrain Implausible message from ABS control module",
            "SV_ERR_SYM_TCS_MSR_VS_MIN.15326"
        ),
        DTCStruct(
            15327,
            "P254500",
            "Torque Management Request Input Signal A Range/Performance",
            "SV_ERR_SYM_GB_INTV_TQ_INT.15327"
        ),
        DTCStruct(
            15328,
            "P254500",
            "Torque Management Request Input Signal A Range/Performance",
            "SV_ERR_SYM_GB_INTV_AC_VEH.15328"
        ),
        DTCStruct(
            15329,
            "P162400",
            "Requirement - malfunction lamp on active",
            "SV_ERR_SYM_TCU1.15329"
        ),
        DTCStruct(15332, "P218100", "Cooling System Performance", "SV_ERR_SYM_TH.15332"),
        DTCStruct(
            15336,
            "P181B00",
            "Gear recognition sensor Electrical error",
            "SV_ERR_SYM_TOUT_NEUT_GEAR.15336"
        ),
        DTCStruct(
            15337,
            "P012300",
            "Throttle/Pedal Pos.Sensor A Circ. High Input",
            "SV_ERR_SYM_VP_TPS_1_H.15337"
        ),
        DTCStruct(
            15338,
            "P012200",
            "Throttle/Pedal Pos.Sensor A Circ. Low Input",
            "SV_ERR_SYM_VP_TPS_1_L.15338"
        ),
        DTCStruct(
            15339,
            "P022300",
            "Throttle/Pedal Position Sensor/Switch B High Input",
            "SV_ERR_SYM_VP_TPS_2_H.15339"
        ),
        DTCStruct(
            15340,
            "P022200",
            "Throttle/Pedal Position Sensor/Switch B Low Input",
            "SV_ERR_SYM_VP_TPS_2_L.15340"
        ),
        DTCStruct(
            15341,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_AD_LIH.15341"
        ),
        DTCStruct(
            15342,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_AD_BOL_ORNG.15342"
        ),
        DTCStruct(
            15343,
            "P029900",
            "Turbo/Super Charger Underboost",
            "SV_ERR_SYM_LEAK_UP_THR.15343"
        ),
        DTCStruct(
            15344,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_AD_BOL.15344"
        ),
        DTCStruct(
            15345,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_AD_BOL_SPR.15345"
        ),
        DTCStruct(
            15346,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_AD_TOL.15346"
        ),
        DTCStruct(
            15347,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_AD_TOL_SPR.15347"
        ),
        DTCStruct(
            15348,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_AD_CDN_BAT.15348"
        ),
        DTCStruct(
            15349,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_AD_CDN.15349"
        ),
        DTCStruct(
            15350,
            "P012100",
            "Throttle/Pedal Pos.Sensor A Circ. Range/Performance",
            "SV_ERR_SYM_TPS_MAF_1.15350"
        ),
        DTCStruct(
            15351,
            "P022100",
            "Throttle/Pedal Position Sensor/Switch B Range/Performance",
            "SV_ERR_SYM_TPS_MAF_2.15351"
        ),
        DTCStruct(
            15353,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_ST_CHK_LIH.15353"
        ),
        DTCStruct(
            15355,
            "P063800",
            "Throttle Actuator Control (Bank1) Range/Performance",
            "SV_ERR_SYM_TPS_ST_CHK_SPR.15355"
        ),
        DTCStruct(
            15364,
            "P161200",
            "Engine control module (ECM) Incorrect coding",
            "SV_ERR_SYM_VAR_COD_DFT.15364"
        ),
        DTCStruct(
            15365,
            "U030000",
            "Internal Control Module Software Incompatibility",
            "SV_ERR_SYM_VAR_COD.15365"
        ),
        DTCStruct(
            15366,
            "U030200",
            "Software Incompatibility with Transmission Control Module",
            "SV_ERR_SYM_VAR_COD_TCU.15366"
        ),
        DTCStruct(15367, "P056300", "System Voltage High Voltage", "SV_ERR_SYM_VP_PWR_H_0.15367"),
        DTCStruct(15368, "P056200", "System Voltage Low Voltage", "SV_ERR_SYM_VP_PWR_L_0.15368"),
        DTCStruct(
            15369,
            "P217700",
            "System too lean off idle, Bank 1",
            "SV_ERR_SYM_FSD_FAC_MAX_1.15369"
        ),
        DTCStruct(
            15370,
            "P217800",
            "System too rich off idle, Bank 1",
            "SV_ERR_SYM_FSD_FAC_MIN_1.15370"
        ),
        DTCStruct(
            15371,
            "P21F400",
            "Fuel Control System B Too Lean Off Idle Bank 1",
            "SV_ERR_SYM_FSD_OPT_FAC_MAX_1.15371"
        ),
        DTCStruct(
            15372,
            "P064300",
            "Sensor Reference Voltage A Circuit High",
            "SV_ERR_SYM_VCC_H_0.15372"
        ),
        DTCStruct(
            15373,
            "P064200",
            "Sensor Reference Voltage A Circuit Low",
            "SV_ERR_SYM_VCC_L_0.15373"
        ),
        DTCStruct(
            15374,
            "P065300",
            "Sensor Reference Voltage B Circuit High",
            "SV_ERR_SYM_VCC_H_1.15374"
        ),
        DTCStruct(
            15375,
            "P065200",
            "Sensor Reference Voltage B Circuit Low",
            "SV_ERR_SYM_VCC_L_1.15375"
        ),
        DTCStruct(
            15376,
            "P229400",
            "Fuel Pressure Regulator 2 Control Circuit",
            "SV_ERR_SYM_VCV_OC.15376"
        ),
        DTCStruct(
            15377,
            "P229500",
            "Fuel Pressure Regulator 2 Control Circuit Low",
            "SV_ERR_SYM_VCV_SCG.15377"
        ),
        DTCStruct(
            15378,
            "P009200",
            "Fuel Pressure Regulator 1 Control Circuit High",
            "SV_ERR_SYM_VCV_SCP.15378"
        ),
        DTCStruct(
            15379,
            "P21F500",
            "Fuel Control System B Too Rich Off Idle Bank 1",
            "SV_ERR_SYM_FSD_OPT_FAC_MIN_1.15379"
        ),
        DTCStruct(
            15381,
            "P11A100",
            "Cam Shift Actuator A Cylinder 1 Circuit/Open",
            "SV_ERR_SYM_VLFT_1_OC_0.15381"
        ),
        DTCStruct(
            15382,
            "P11A500",
            "Cam Shift Actuator A Cylinder 2 Circuit/Open",
            "SV_ERR_SYM_VLFT_1_OC_3.15382"
        ),
        DTCStruct(
            15383,
            "P11A900",
            "Cam Shift Actuator A Cylinder 3 Circuit/Open",
            "SV_ERR_SYM_VLFT_1_OC_1.15383"
        ),
        DTCStruct(
            15384,
            "P11AD00",
            "Cam Shift Actuator A Cylinder 4 Circuit/Open",
            "SV_ERR_SYM_VLFT_1_OC_2.15384"
        ),
        DTCStruct(
            15385,
            "P11A100",
            "Cam Shift Actuator A Cylinder 1 Circuit/Open",
            "SV_ERR_SYM_VLFT_1_SCG_0.15385"
        ),
        DTCStruct(
            15386,
            "P11A500",
            "Cam Shift Actuator A Cylinder 2 Circuit/Open",
            "SV_ERR_SYM_VLFT_1_SCG_3.15386"
        ),
        DTCStruct(
            15387,
            "P11A900",
            "Cam Shift Actuator A Cylinder 3 Circuit/Open",
            "SV_ERR_SYM_VLFT_1_SCG_1.15387"
        ),
        DTCStruct(
            15388,
            "P11AD00",
            "Cam Shift Actuator A Cylinder 4 Circuit/Open",
            "SV_ERR_SYM_VLFT_1_SCG_2.15388"
        ),
        DTCStruct(
            15389,
            "P11BF00",
            "Cam Shift Actuator Outlet A Cylinder 1 Range/Performance",
            "SV_ERR_SYM_VLFT_1_SCP_0.15389"
        ),
        DTCStruct(
            15390,
            "P11C100",
            "Cam Shift Actuator Outlet A Cylinder 2 Range/Performance",
            "SV_ERR_SYM_VLFT_1_SCP_3.15390"
        ),
        DTCStruct(
            15391,
            "P11C300",
            "Cam Shift Actuator Outlet A Cylinder 3 Range/Performance",
            "SV_ERR_SYM_VLFT_1_SCP_1.15391"
        ),
        DTCStruct(
            15392,
            "P11C500",
            "Cam Shift Actuator Outlet A Cylinder 4 Range/Performance",
            "SV_ERR_SYM_VLFT_1_SCP_2.15392"
        ),
        DTCStruct(
            15394,
            "P11A200",
            "Cam Shift Actuator A Cylinder 1 Range/Performance",
            "SV_ERR_SYM_VLFT_1_V_0_1.15394"
        ),
        DTCStruct(
            15396,
            "P11A600",
            "Cam Shift Actuator A Cylinder 2 Range/Performance",
            "SV_ERR_SYM_VLFT_1_V_3_1.15396"
        ),
        DTCStruct(
            15398,
            "P11AA00",
            "Cam Shift Actuator A Cylinder 3 Range/Performance",
            "SV_ERR_SYM_VLFT_1_V_1_1.15398"
        ),
        DTCStruct(
            15400,
            "P11AE00",
            "Cam Shift Actuator A Cylinder 4 Range/Performance",
            "SV_ERR_SYM_VLFT_1_V_2_1.15400"
        ),
        DTCStruct(
            15401,
            "P11A300",
            "Cam Shift Actuator B Cylinder 1 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_OC_0.15401"
        ),
        DTCStruct(
            15402,
            "P11A700",
            "Cam Shift Actuator B Cylinder 2 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_OC_3.15402"
        ),
        DTCStruct(
            15403,
            "P11AB00",
            "Cam Shift Actuator B Cylinder 3 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_OC_1.15403"
        ),
        DTCStruct(
            15404,
            "P11AF00",
            "Cam Shift Actuator B Cylinder 4 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_OC_2.15404"
        ),
        DTCStruct(
            15405,
            "P11A300",
            "Cam Shift Actuator B Cylinder 1 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_SCG_0.15405"
        ),
        DTCStruct(
            15406,
            "P11A700",
            "Cam Shift Actuator B Cylinder 2 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_SCG_3.15406"
        ),
        DTCStruct(
            15407,
            "P11AB00",
            "Cam Shift Actuator B Cylinder 3 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_SCG_1.15407"
        ),
        DTCStruct(
            15408,
            "P11AF00",
            "Cam Shift Actuator B Cylinder 4 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_SCG_2.15408"
        ),
        DTCStruct(
            15409,
            "P11C000",
            "Cam Shift Actuator Outlet B Cylinder 1 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_SCP_0.15409"
        ),
        DTCStruct(
            15410,
            "P11C200",
            "Cam Shift Actuator Outlet B Cylinder 2 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_SCP_3.15410"
        ),
        DTCStruct(
            15411,
            "P11C400",
            "Cam Shift Actuator Outlet B Cylinder 3 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_SCP_1.15411"
        ),
        DTCStruct(
            15412,
            "P11C600",
            "Cam Shift Actuator Outlet B Cylinder 4 Circuit/Open",
            "SV_ERR_SYM_VLFT_STND_SCP_2.15412"
        ),
        DTCStruct(
            15414,
            "P11A400",
            "Cam Shift Actuator B Cylinder 1 Range/Performance",
            "SV_ERR_SYM_VLFT_STND_V_0_1.15414"
        ),
        DTCStruct(
            15416,
            "P11A800",
            "Cam Shift Actuator B Cylinder 2 Range/Performance",
            "SV_ERR_SYM_VLFT_STND_V_3_1.15416"
        ),
        DTCStruct(
            15418,
            "P11AC00",
            "Cam Shift Actuator B Cylinder 3 Range/Performance",
            "SV_ERR_SYM_VLFT_STND_V_1_1.15418"
        ),
        DTCStruct(
            15420,
            "P11B000",
            "Cam Shift Actuator B Cylinder 4 Range/Performance",
            "SV_ERR_SYM_VLFT_STND_V_2_1.15420"
        ),
        DTCStruct(
            15423,
            "P256500",
            "Turbocharger Boost Control Position Sensor Circuit High",
            "SV_ERR_SYM_VP_BPA_H_1.15423"
        ),
        DTCStruct(
            15424,
            "P256400",
            "Turbocharger Boost Control Position Sensor Circuit Low",
            "SV_ERR_SYM_VP_BPA_L_1.15424"
        ),
        DTCStruct(
            15431,
            "P25A900",
            "Piston Cooling Oil Control Circuit/Open",
            "SV_ERR_SYM_OIL_2_PIST_OC.15431"
        ),
        DTCStruct(
            15432,
            "P25AA00",
            "Piston Cooling Oil Control Circuit Low",
            "SV_ERR_SYM_OIL_2_PIST_SCG.15432"
        ),
        DTCStruct(
            15433,
            "P25AB00",
            "Piston Cooling Oil Control Circuit High",
            "SV_ERR_SYM_OIL_2_PIST_SCP.15433"
        ),
        DTCStruct(
            15434,
            "P21CF00",
            "Cylinder 1 Injector B Circuit/Open",
            "SV_ERR_SYM_IV_MPI_OC_0.15434"
        ),
        DTCStruct(
            15435,
            "P21D000",
            "Cylinder 2 Injector B Circuit/Open",
            "SV_ERR_SYM_IV_MPI_OC_3.15435"
        ),
        DTCStruct(
            15436,
            "P21D100",
            "Cylinder 3 Injector B Circuit/Open",
            "SV_ERR_SYM_IV_MPI_OC_1.15436"
        ),
        DTCStruct(
            15437,
            "P21D200",
            "Cylinder 4 Injector B Circuit/Open",
            "SV_ERR_SYM_IV_MPI_OC_2.15437"
        ),
        DTCStruct(
            15438,
            "P21DB00",
            "Cylinder 1 Injector B Circuit Low",
            "SV_ERR_SYM_IV_MPI_SCG_0.15438"
        ),
        DTCStruct(
            15439,
            "P21DE00",
            "Cylinder 2 Injector B Circuit Low",
            "SV_ERR_SYM_IV_MPI_SCG_3.15439"
        ),
        DTCStruct(
            15440,
            "P21E000",
            "Cylinder 3 Injector B Circuit Low",
            "SV_ERR_SYM_IV_MPI_SCG_1.15440"
        ),
        DTCStruct(
            15441,
            "P21E200",
            "Cylinder 4 Injector B Circuit Low",
            "SV_ERR_SYM_IV_MPI_SCG_2.15441"
        ),
        DTCStruct(
            15442,
            "P21DC00",
            "Cylinder 1 Injector B Circuit High",
            "SV_ERR_SYM_IV_MPI_SCP_0.15442"
        ),
        DTCStruct(
            15443,
            "P21DF00",
            "Cylinder 2 Injector B Circuit High",
            "SV_ERR_SYM_IV_MPI_SCP_3.15443"
        ),
        DTCStruct(
            15444,
            "P21E100",
            "Cylinder 3 Injector B Circuit High",
            "SV_ERR_SYM_IV_MPI_SCP_1.15444"
        ),
        DTCStruct(
            15445,
            "P21E300",
            "Cylinder 4 Injector B Circuit High",
            "SV_ERR_SYM_IV_MPI_SCP_2.15445"
        ),
        DTCStruct(
            15446,
            "P188900",
            "Coolant shut-off valve Short circuit to ground/open circuit",
            "SV_ERR_SYM_COC_EPC_OC_INTER_HEAT.15446"
        ),
        DTCStruct(
            15447,
            "P19A200",
            "Transmission coolant valve Open circuit",
            "SV_ERR_SYM_COC_EPC_OC_GB_HEAT.15447"
        ),
        DTCStruct(
            15448,
            "P275300",
            "Transmission Fluid Cooler Control Circuit/Open",
            "SV_ERR_SYM_COC_EPC_OC_GB_COOL.15448"
        ),
        DTCStruct(
            15450,
            "P188900",
            "Coolant shut-off valve Short circuit to ground/open circuit",
            "SV_ERR_SYM_COC_EPC_SCG_INTER_HEAT.15450"
        ),
        DTCStruct(
            15451,
            "P19A100",
            "Transmission coolant valve Short circuit to ground",
            "SV_ERR_SYM_COC_EPC_SCG_GB_HEAT.15451"
        ),
        DTCStruct(
            15452,
            "P275400",
            "Transmission Fluid Cooler Control Circuit Low",
            "SV_ERR_SYM_COC_EPC_SCG_GB_COOL.15452"
        ),
        DTCStruct(
            15454,
            "P188800",
            "Coolant shut-off valve Short circuit to B+",
            "SV_ERR_SYM_COC_EPC_SCP_INTER_HEAT.15454"
        ),
        DTCStruct(
            15455,
            "P19A000",
            "Transmission coolant valve Short circuit to B+",
            "SV_ERR_SYM_COC_EPC_SCP_GB_HEAT.15455"
        ),
        DTCStruct(
            15456,
            "P275500",
            "Transmission Fluid Cooler Control Circuit High",
            "SV_ERR_SYM_COC_EPC_SCP_GB_COOL.15456"
        ),
        DTCStruct(
            15462,
            "P268100",
            "Engine Coolant Bypass Valve Control Circuit/Open",
            "SV_ERR_SYM_RVC_DR_OC.15462"
        ),
        DTCStruct(
            15463,
            "P268200",
            "Engine Coolant Bypass Valve Control Circuit Low",
            "SV_ERR_SYM_RVC_DR_SCG1.15463"
        ),
        DTCStruct(
            15464,
            "P268200",
            "Engine Coolant Bypass Valve Control Circuit Low",
            "SV_ERR_SYM_RVC_DR_SCG2.15464"
        ),
        DTCStruct(
            15465,
            "P268300",
            "Engine Coolant Bypass Valve Control Circuit High",
            "SV_ERR_SYM_RVC_DR_SCP1.15465"
        ),
        DTCStruct(
            15466,
            "P268300",
            "Engine Coolant Bypass Valve Control Circuit High",
            "SV_ERR_SYM_RVC_DR_SCP2.15466"
        ),
        DTCStruct(
            15470,
            "P00B700",
            "Engine Coolant Flow Low/Performance",
            "SV_ERR_SYM_RVC_JAM_RNG_0.15470"
        ),
        DTCStruct(
            15471,
            "P255700",
            "Engine Coolant Level Sensor/Switch Circuit Range/Performance",
            "SV_ERR_SYM_RVC_DIG_CHKSUM.15471"
        ),
        DTCStruct(
            15472,
            "P255600",
            "Engine Coolant Level Sensor/Switch Circuit",
            "SV_ERR_SYM_RVC_DIG_COM.15472"
        ),
        DTCStruct(
            15473,
            "P255600",
            "Engine Coolant Level Sensor/Switch Circuit",
            "SV_ERR_SYM_RVC_DIG_SENS.15473"
        ),
        DTCStruct(15492, "P056300", "System Voltage High Voltage", "SV_ERR_SYM_VP_PWR_H_1.15492"),
        DTCStruct(15493, "P056200", "System Voltage Low Voltage", "SV_ERR_SYM_VP_PWR_L_1.15493"),
        DTCStruct(
            16447,
            "P000B00",
            "B Camshaft Position Slow Response (Bank 1)",
            "SV_ERR_SYM_CAM_DYN_VCP_EX_1.16447"
        ),
        DTCStruct(
            16448,
            "P000A00",
            "A Camshaft Position Slow Response (Bank 1)",
            "SV_ERR_SYM_CAM_DYN_VCP_IN_1.16448"
        ),
        DTCStruct(
            16452,
            "U041600",
            "Invalid Data Received From Vehicle Dynamics Control Module",
            "SV_ERR_SYM_PBSU_CAN_SENS.16452"
        ),
        DTCStruct(
            16453,
            "U041600",
            "Invalid Data Received From Vehicle Dynamics Control Module",
            "SV_ERR_SYM_PBSU_CAN_INIT.16453"
        ),
        DTCStruct(
            16464,
            "P00B700",
            "Engine Coolant Flow Low/Performance",
            "SV_ERR_SYM_RVC_DIG_BOL_AD.16464"
        ),
        DTCStruct(
            16465,
            "P00B700",
            "Engine Coolant Flow Low/Performance",
            "SV_ERR_SYM_RVC_DIG_TOL_AD.16465"
        ),
        DTCStruct(
            16466,
            "P00B700",
            "Engine Coolant Flow Low/Performance",
            "SV_ERR_SYM_RVC_AD_TOUT.16466"
        ),
        DTCStruct(
            16467,
            "P210100",
            "Throttle Actuator Control Motor Circuit Range/performance",
            "SV_ERR_SYM_ETC_DR_OHP.16467"
        ),
        DTCStruct(
            16468,
            "P210300",
            "Throttle Actuator A Control Motor Circuit High",
            "SV_ERR_SYM_ETC_DR_SC.16468"
        ),
        DTCStruct(
            16469,
            "P210000",
            "Throttle Actuator Control Motor Circuit/Open",
            "SV_ERR_SYM_ETC_DR_OC.16469"
        ),
        DTCStruct(
            16470,
            "P012100",
            "Throttle/Pedal Pos.Sensor A Circ. Range/Performance",
            "SV_ERR_SYM_TPS_RATIO.16470"
        ),
        DTCStruct(
            16479,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_VS_TOL.16479"
        ),
        DTCStruct(
            16483,
            "P050200",
            "Vehicle Speed Sensor A Circuit Low Input",
            "SV_ERR_SYM_VS_CAN_EL_SCG.16483"
        ),
        DTCStruct(
            16484,
            "P050200",
            "Vehicle Speed Sensor A Circuit Low Input",
            "SV_ERR_SYM_VS_CAN_EL_SCP.16484"
        ),
        DTCStruct(
            16485,
            "P050200",
            "Vehicle Speed Sensor A Circuit Low Input",
            "SV_ERR_SYM_VS_CAN_EL_OC.16485"
        ),
        DTCStruct(
            16486,
            "P050100",
            "Vehicle Speed Sensor A Range/Performance",
            "SV_ERR_SYM_VS_CAN_PLAUS_OOR_H.16486"
        ),
        DTCStruct(
            16487,
            "P050100",
            "Vehicle Speed Sensor A Range/Performance",
            "SV_ERR_SYM_VS_CAN_PLAUS_OOR_L.16487"
        ),
        DTCStruct(
            16488,
            "P050100",
            "Vehicle Speed Sensor A Range/Performance",
            "SV_ERR_SYM_VS_CAN_PLAUS_RC_H.16488"
        ),
        DTCStruct(
            16489,
            "P050100",
            "Vehicle Speed Sensor A Range/Performance",
            "SV_ERR_SYM_VS_CAN_PLAUS_RC_L.16489"
        ),
        DTCStruct(
            16490,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_VS_CAN_INI_VALUE.16490"
        ),
        DTCStruct(
            16491,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_VS_CAN_V_SMALL.16491"
        ),
        DTCStruct(
            16492,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_VS_CAN_SENS_DFCT.16492"
        ),
        DTCStruct(
            16503,
            "P33B700",
            "Cylinder 1 Combustion misfire during direct injection detected",
            "SV_ERR_SYM_MIS_DI_0.16503"
        ),
        DTCStruct(
            16504,
            "P33B800",
            "Cylinder 2 Combustion misfire during direct injection detected",
            "SV_ERR_SYM_MIS_DI_3.16504"
        ),
        DTCStruct(
            16505,
            "P33B900",
            "Cylinder 3 Combustion misfire during direct injection detected",
            "SV_ERR_SYM_MIS_DI_1.16505"
        ),
        DTCStruct(
            16506,
            "P33BA00",
            "Cylinder 4 Combustion misfire during direct injection detected",
            "SV_ERR_SYM_MIS_DI_2.16506"
        ),
        DTCStruct(
            16507,
            "P33BF00",
            "Cylinder 1 Combustion misfire during combined operation detected",
            "SV_ERR_SYM_MIS_IGC_0.16507"
        ),
        DTCStruct(
            16508,
            "P33C000",
            "Cylinder 2 Combustion misfire during combined operation detected",
            "SV_ERR_SYM_MIS_IGC_3.16508"
        ),
        DTCStruct(
            16509,
            "P33C100",
            "Cylinder 3 Combustion misfire during combined operation detected",
            "SV_ERR_SYM_MIS_IGC_1.16509"
        ),
        DTCStruct(
            16510,
            "P33C200",
            "Cylinder 4 Combustion misfire during combined operation detected",
            "SV_ERR_SYM_MIS_IGC_2.16510"
        ),
        DTCStruct(
            16511,
            "P33AF00",
            "Cylinder 1 Combustion misfire during intake manifold injection detected",
            "SV_ERR_SYM_MIS_MPI_0.16511"
        ),
        DTCStruct(
            16512,
            "P33B000",
            "Cylinder 2 Combustion misfire during intake manifold injection detected",
            "SV_ERR_SYM_MIS_MPI_3.16512"
        ),
        DTCStruct(
            16513,
            "P33B100",
            "Cylinder 3 Combustion misfire during intake manifold injection detected",
            "SV_ERR_SYM_MIS_MPI_1.16513"
        ),
        DTCStruct(
            16514,
            "P33B200",
            "Cylinder 4 Combustion misfire during intake manifold injection detected",
            "SV_ERR_SYM_MIS_MPI_2.16514"
        ),
        DTCStruct(
            16782,
            "U044700",
            "Invalid Data Received From Gateway A",
            "SV_ERR_SYM_CAN_DATE_2.16782"
        ),
        DTCStruct(
            16784,
            "U044700",
            "Invalid Data Received From Gateway A",
            "SV_ERR_SYM_CAN_DATE_4.16784"
        ),
        DTCStruct(
            16785,
            "U044700",
            "Invalid Data Received From Gateway A",
            "SV_ERR_SYM_CAN_DATE_5.16785"
        ),
        DTCStruct(
            16786,
            "U044700",
            "Invalid Data Received From Gateway A",
            "SV_ERR_SYM_CAN_DATE_6.16786"
        ),
        DTCStruct(
            16796,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_N_GB_ISC_ETCU.16796"
        ),
        DTCStruct(
            16797,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_N_GB_NCTL_ETCU.16797"
        ),
        DTCStruct(
            16801,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_T_SYN_GB_NCTL_ETCU.16801"
        ),
        DTCStruct(
            16803,
            "U042300",
            "Invalid Data Received From Instrument Panel Cluster Control Module",
            "SV_ERR_SYM_TAA_FIL_INTC.16803"
        ),
        DTCStruct(
            16807,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_TQ_PT_TRANS_ETCU.16807"
        ),
        DTCStruct(
            16810,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_TQ_GB_INTV_FAST_ETCU.16810"
        ),
        DTCStruct(
            16812,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_TQ_GB_INTV_SLOW_ETCU.16812"
        ),
        DTCStruct(
            16819,
            "P172300",
            "Signal for starter lock Open circuit",
            "SV_ERR_SYM_CLU_SWI_2_SCP_1.16819"
        ),
        DTCStruct(
            16821,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_CYC_1_0.16821"
        ),
        DTCStruct(
            16822,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_CYC_1_1.16822"
        ),
        DTCStruct(
            16823,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_CYC_2_0.16823"
        ),
        DTCStruct(
            16824,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_CYC_2_1.16824"
        ),
        DTCStruct(
            16825,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_INI_1_0.16825"
        ),
        DTCStruct(
            16826,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_INI_1_1.16826"
        ),
        DTCStruct(
            16827,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_INI_2_0.16827"
        ),
        DTCStruct(
            16828,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_INI_2_1.16828"
        ),
        DTCStruct(
            16829,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_INI_3_0.16829"
        ),
        DTCStruct(
            16830,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_INI_3_1.16830"
        ),
        DTCStruct(
            16831,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_INI_4_0.16831"
        ),
        DTCStruct(
            16832,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_DPS_IV_INI_4_1.16832"
        ),
        DTCStruct(
            16837,
            "P218500",
            "Engine Coolant Temperature Sensor 2 Circuit High",
            "SV_ERR_SYM_LTS_EL_H_TCR.16837"
        ),
        DTCStruct(
            16838,
            "P218400",
            "Engine Coolant Temperature Sensor 2 Circuit Low",
            "SV_ERR_SYM_LTS_EL_L_TCR.16838"
        ),
        DTCStruct(
            16845,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_SPI_PBK_IV_CYC_1.16845"
        ),
        DTCStruct(
            16846,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_SPI_PBK_IV_INI_0.16846"
        ),
        DTCStruct(
            16847,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_SPI_PBK_IV_INI_1.16847"
        ),
        DTCStruct(16853, "P014900", "Fuel Timing Error", "SV_ERR_SYM_VBOOST_BOL_1.16853"),
        DTCStruct(16854, "P014900", "Fuel Timing Error", "SV_ERR_SYM_VBOOST_BOL_2.16854"),
        DTCStruct(16855, "P014900", "Fuel Timing Error", "SV_ERR_SYM_VBOOST_TOL.16855"),
        DTCStruct(
            16986,
            "P00B700",
            "Engine Coolant Flow Low/Performance",
            "SV_ERR_SYM_RVC_JAM_RNG_1.16986"
        ),
        DTCStruct(
            16987,
            "P00B700",
            "Engine Coolant Flow Low/Performance",
            "SV_ERR_SYM_RVC_JAM_RNG_2.16987"
        ),
        DTCStruct(
            16988,
            "P00B700",
            "Engine Coolant Flow Low/Performance",
            "SV_ERR_SYM_RVC_JAM_RNG_3.16988"
        ),
        DTCStruct(16992, "U110300", "Production mode active", "SV_ERR_SYM_MOD_PROD.16992"),
        DTCStruct(16993, "P169A00", "Loading mode active", "SV_ERR_SYM_MOD_TRPT.16993"),
        DTCStruct(
            17002,
            "P168D00",
            "Oil pressure switch 3 Malfunction",
            "SV_ERR_SYM_POIL_PIST_L.17002"
        ),
        DTCStruct(
            17003,
            "P168C00",
            "Oil pressure switch 3 Implausible signal during engine operation",
            "SV_ERR_SYM_POIL_PIST_H.17003"
        ),
        DTCStruct(
            17004,
            "P168B00",
            "Oil pressure switch 3 Implausible signal during engine stand still",
            "SV_ERR_SYM_POIL_PIST_PLAUS.17004"
        ),
        DTCStruct(
            17169,
            "P164700",
            "Checking coding/versions of control modules in drivetrain",
            "SV_ERR_SYM_VLCAvl_stCodFail_VW.17169"
        ),
        DTCStruct(
            17170,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_VLCAvl_stBrkPlaus_VW.17170"
        ),
        DTCStruct(
            17171,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_VLCAvl_aAccDes_VW.17171"
        ),
        DTCStruct(
            17172,
            "P150B00",
            "Acceleration monitoring Control limit exceeded",
            "SV_ERR_SYM_VLCAvl_aDeBrkLo_VW.17172"
        ),
        DTCStruct(
            17173,
            "P150B00",
            "Acceleration monitoring Control limit exceeded",
            "SV_ERR_SYM_VLCAvl_aDeEngOpm_VW.17173"
        ),
        DTCStruct(
            17174,
            "P150B00",
            "Acceleration monitoring Control limit exceeded",
            "SV_ERR_SYM_VLCAvl_aVeh_VW.17174"
        ),
        DTCStruct(
            17175,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_VLCAvl_stAccPlaus_VW.17175"
        ),
        DTCStruct(
            17176,
            "U112300",
            "Databus error value received",
            "SV_ERR_SYM_VLCAvl_errSigAcc_VW.17176"
        ),
        DTCStruct(
            17177,
            "P150B00",
            "Acceleration monitoring Control limit exceeded",
            "SV_ERR_SYM_VLCAvl_aDeBrkHi_VW.17177"
        ),
        DTCStruct(
            17178,
            "U112300",
            "Databus error value received",
            "SV_ERR_SYM_VLCAvl_stBrkFailIrv_VW.17178"
        ),
        DTCStruct(
            17179,
            "U112300",
            "Databus error value received",
            "SV_ERR_SYM_VLCAvl_stTrailDfct_VW.17179"
        ),
        DTCStruct(
            17180,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_VLCAvl_stTrailDiag_VW.17180"
        ),
        DTCStruct(
            17181,
            "U112300",
            "Databus error value received",
            "SV_ERR_SYM_VLCAvl_stBrkLghtDfct_VW.17181"
        ),
        DTCStruct(
            17182,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_VLCAvl_stBrkLghtDiag_VW.17182"
        ),
        DTCStruct(
            17183,
            "P133600",
            "Engine torque monitoring Control limit exceeded",
            "SV_ERR_SYM_MonTqLim_tqLimMon_VW.17183"
        ),
        DTCStruct(
            17184,
            "P15A400",
            "Vehicle shutdown active",
            "SV_ERR_SYM_LMVLim_bTrckAuth_VW.17184"
        ),
        DTCStruct(
            17185,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_LMLev_stLevPlaus_VW.17185"
        ),
        DTCStruct(
            17186,
            "U112300",
            "Databus error value received",
            "SV_ERR_SYM_LMLev_bLevSig_VW.17186"
        ),
        DTCStruct(
            17187,
            "P056800",
            "Cruise Control Set Signal",
            "SV_ERR_SYM_LMLev_bMainSwt_VW.17187"
        ),
        DTCStruct(
            17188,
            "U112300",
            "Databus error value received",
            "SV_ERR_SYM_LMVDes_bDisp_VW.17188"
        ),
        DTCStruct(
            17189,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_LMVDes_vDispPlaus_VW.17189"
        ),
        DTCStruct(
            17355,
            "P020100",
            "Cylinder 1- Injector Circuit",
            "SV_ERR_SYM_IV_HPDI_OC_0.17355"
        ),
        DTCStruct(
            17356,
            "P020200",
            "Cylinder 2- Injector Circuit",
            "SV_ERR_SYM_IV_HPDI_OC_3.17356"
        ),
        DTCStruct(
            17357,
            "P020300",
            "Cylinder 3- Injector Circuit",
            "SV_ERR_SYM_IV_HPDI_OC_1.17357"
        ),
        DTCStruct(
            17358,
            "P020400",
            "Cylinder 4- Injector Circuit",
            "SV_ERR_SYM_IV_HPDI_OC_2.17358"
        ),
        DTCStruct(
            17359,
            "P026100",
            "Cylinder 1- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_0.17359"
        ),
        DTCStruct(
            17360,
            "P026400",
            "Cylinder 2- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_3.17360"
        ),
        DTCStruct(
            17361,
            "P026700",
            "Cylinder 3- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_1.17361"
        ),
        DTCStruct(
            17362,
            "P027000",
            "Cylinder 4- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_2.17362"
        ),
        DTCStruct(
            17363,
            "P026200",
            "Cylinder 1- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_0.17363"
        ),
        DTCStruct(
            17364,
            "P026500",
            "Cylinder 2- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_3.17364"
        ),
        DTCStruct(
            17365,
            "P026800",
            "Cylinder 3- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_1.17365"
        ),
        DTCStruct(
            17366,
            "P027100",
            "Cylinder 4- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_2.17366"
        ),
        DTCStruct(
            17367,
            "P10B100",
            "Monitoring of injector cylinder 1 Malfunction detected",
            "SV_ERR_SYM_IV_HPDI_SCL_0.17367"
        ),
        DTCStruct(
            17368,
            "P10B200",
            "Monitoring of injector cylinder 2 Malfunction detected",
            "SV_ERR_SYM_IV_HPDI_SCL_3.17368"
        ),
        DTCStruct(
            17369,
            "P10B300",
            "Monitoring of injector cylinder 3 Malfunction detected",
            "SV_ERR_SYM_IV_HPDI_SCL_1.17369"
        ),
        DTCStruct(
            17370,
            "P10B400",
            "Monitoring of injector cylinder 4 Malfunction detected",
            "SV_ERR_SYM_IV_HPDI_SCL_2.17370"
        ),
        DTCStruct(
            17371,
            "P026200",
            "Cylinder 1- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_L_0.17371"
        ),
        DTCStruct(
            17372,
            "P026500",
            "Cylinder 2- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_L_3.17372"
        ),
        DTCStruct(
            17373,
            "P026800",
            "Cylinder 3- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_L_1.17373"
        ),
        DTCStruct(
            17374,
            "P027100",
            "Cylinder 4- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_L_2.17374"
        ),
        DTCStruct(
            17375,
            "P026200",
            "Cylinder 1- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_H_0.17375"
        ),
        DTCStruct(
            17376,
            "P026500",
            "Cylinder 2- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_H_3.17376"
        ),
        DTCStruct(
            17377,
            "P026800",
            "Cylinder 3- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_H_1.17377"
        ),
        DTCStruct(
            17378,
            "P027100",
            "Cylinder 4- Injector Circuit High",
            "SV_ERR_SYM_IV_HPDI_SCP_H_2.17378"
        ),
        DTCStruct(
            17379,
            "P026100",
            "Cylinder 1- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_L_0.17379"
        ),
        DTCStruct(
            17380,
            "P026400",
            "Cylinder 2- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_L_3.17380"
        ),
        DTCStruct(
            17381,
            "P026700",
            "Cylinder 3- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_L_1.17381"
        ),
        DTCStruct(
            17382,
            "P027000",
            "Cylinder 4- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_L_2.17382"
        ),
        DTCStruct(
            17383,
            "P026100",
            "Cylinder 1- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_H_0.17383"
        ),
        DTCStruct(
            17384,
            "P026400",
            "Cylinder 2- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_H_3.17384"
        ),
        DTCStruct(
            17385,
            "P026700",
            "Cylinder 3- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_H_1.17385"
        ),
        DTCStruct(
            17386,
            "P027000",
            "Cylinder 4- Injector Circuit Low",
            "SV_ERR_SYM_IV_HPDI_SCG_H_2.17386"
        ),
        DTCStruct(
            17387,
            "P10B100",
            "Monitoring of injector cylinder 1 Malfunction detected",
            "SV_ERR_SYM_IV_HPDI_IDT_0.17387"
        ),
        DTCStruct(
            17388,
            "P10B200",
            "Monitoring of injector cylinder 2 Malfunction detected",
            "SV_ERR_SYM_IV_HPDI_IDT_3.17388"
        ),
        DTCStruct(
            17389,
            "P10B300",
            "Monitoring of injector cylinder 3 Malfunction detected",
            "SV_ERR_SYM_IV_HPDI_IDT_1.17389"
        ),
        DTCStruct(
            17390,
            "P10B400",
            "Monitoring of injector cylinder 4 Malfunction detected",
            "SV_ERR_SYM_IV_HPDI_IDT_2.17390"
        ),
        DTCStruct(17521, "P150D00", "Chain elongation", "SV_ERR_SYM_ChaDiag_bChaElg1_VW.17521"),
        DTCStruct(
            17789,
            "P00B700",
            "Engine Coolant Flow Low/Performance",
            "SV_ERR_SYM_RVC_DIF.17789"
        ),
        DTCStruct(
            17918,
            "P198700",
            "Function restriction due to brake temperature",
            "SV_ERR_SYM_VLCAvl_bBrkOverHeat_VW.17918"
        ),
        DTCStruct(
            17920,
            "P056800",
            "Cruise Control Set Signal",
            "SV_ERR_SYM_LMLev_bMainBtn_VW.17920"
        ),
        DTCStruct(
            17922,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_LMVDes_bDispHiLn_VW.17922"
        ),
        DTCStruct(
            17924,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_LMVDes_bDispLim_VW.17924"
        ),
        DTCStruct(
            18191,
            "P167D00",
            "Button for start/stop operation Electrical error",
            "SV_ERR_SYM_StrtStopSwt_bStuck_VW.18191"
        ),
        DTCStruct(
            18876,
            "P056800",
            "Cruise Control Set Signal",
            "SV_ERR_SYM_LMLev_bLevCod_VW.18876"
        ),
        DTCStruct(
            18929,
            "P013A00",
            "O2 Sensor Bank 1 Sensor 2 Slow Response - Rich to Lean",
            "SV_ERR_SYM_GRD_R2L_LS_DOWN_1.18929"
        ),
        DTCStruct(
            18933,
            "P227000",
            "O2 Sensor Signal Stuck Lean; Bank 1 Sensor 2",
            "SV_ERR_SYM_PEAK_MAX_LS_DOWN_1.18933"
        ),
        DTCStruct(
            18934,
            "P227100",
            "O2 Sensor Signal Stuck Rich; Bank 1 Sensor 2",
            "SV_ERR_SYM_PEAK_MIN_LS_DOWN_1.18934"
        ),
        DTCStruct(
            19308,
            "U042100",
            "Invalid Data Received From Suspension Control Module",
            "SV_ERR_SYM_COM_CHF_PLAUS.19308"
        ),
        DTCStruct(
            19309,
            "U013200",
            "Lost Communication With Suspension Control Module A",
            "SV_ERR_SYM_COM_CHF_TOUT.19309"
        ),
        DTCStruct(
            19311,
            "U019900",
            "Lost Communication With Door Control Module A",
            "SV_ERR_SYM_COM_DSC_LE_TOUT.19311"
        ),
        DTCStruct(19395, "C10A700", "Rolling Mode recognized", "SV_ERR_SYM_DYNO_MOD.19395"),
        DTCStruct(
            19845,
            "U045200",
            "Invalid Data Received From Restraints Control Module",
            "SV_ERR_SYM_COM_ACU_PLAUS.19845"
        ),
        DTCStruct(
            19846,
            "U015100",
            "Lost Communication With Restraints Control Module",
            "SV_ERR_SYM_COM_ACU_TOUT.19846"
        ),
        DTCStruct(
            19853,
            "U042900",
            "Invalid Data Received From Steering Column Control Module",
            "SV_ERR_SYM_COM_CRU_PLAUS.19853"
        ),
        DTCStruct(
            19854,
            "U021200",
            "Lost Communication With Steering Column Control Module",
            "SV_ERR_SYM_COM_CRU_TOUT.19854"
        ),
        DTCStruct(
            19859,
            "U049A00",
            "Invalid Data Received From Door Control Module A",
            "SV_ERR_SYM_COM_DSC_LE_PLAUS.19859"
        ),
        DTCStruct(
            19861,
            "U043100",
            "Invalid Data Received From Body Control Module A",
            "SV_ERR_SYM_COM_EILU_PLAUS.19861"
        ),
        DTCStruct(
            19862,
            "U014100",
            "Lost Communication With Body Control Module A",
            "SV_ERR_SYM_COM_EILU_TOUT.19862"
        ),
        DTCStruct(
            19867,
            "U042300",
            "Invalid Data Received From Instrument Panel Cluster Control Module",
            "SV_ERR_SYM_COM_ICL_PLAUS.19867"
        ),
        DTCStruct(
            19868,
            "U015500",
            "Lost Communication With Instrument Panel Cluster (IPC) Control Module",
            "SV_ERR_SYM_COM_ICL_TOUT.19868"
        ),
        DTCStruct(
            19869,
            "U044700",
            "Invalid Data Received From Gateway A",
            "SV_ERR_SYM_COM_IF_CFT_PLAUS.19869"
        ),
        DTCStruct(
            19870,
            "U014600",
            "Lost Communication With Gateway A",
            "SV_ERR_SYM_COM_IF_CFT_TOUT.19870"
        ),
        DTCStruct(
            19874,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_COM_TCS_PLAUS.19874"
        ),
        DTCStruct(
            19875,
            "U012100",
            "Lost Communication With Anti-Lock Brake System (ABS) Control Module",
            "SV_ERR_SYM_COM_TCS_TOUT.19875"
        ),
        DTCStruct(
            19878,
            "U042200",
            "Invalid Data Received From Body Control Module",
            "SV_ERR_SYM_COM_VECU_PLAUS.19878"
        ),
        DTCStruct(
            19879,
            "U014000",
            "Lost Communication With Body Control Module",
            "SV_ERR_SYM_COM_VECU_TOUT.19879"
        ),
        DTCStruct(
            20250,
            "U032200",
            "Software Incompatibility With Body Control Module",
            "SV_ERR_SYM_TAA_SRC_CONF_INTC.20250"
        ),
        DTCStruct(
            20251,
            "U042200",
            "Invalid Data Received From Body Control Module",
            "SV_ERR_SYM_TAA_SRC_RST_INTC.20251"
        ),
        DTCStruct(
            20269,
            "P070400",
            "Clutch Switch Input Circuit",
            "SV_ERR_SYM_CLU_SWI_PLAUS_0.20269"
        ),
        DTCStruct(
            20270,
            "P070400",
            "Clutch Switch Input Circuit",
            "SV_ERR_SYM_CLU_SWI_PLAUS_1.20270"
        ),
        DTCStruct(
            20276,
            "P151B00",
            "Voltage terminal 15 Implausible",
            "SV_ERR_SYM_IGK_PLAUS_0.20276"
        ),
        DTCStruct(20316, "P30C000", "Fuel quality poor", "SV_ERR_SYM_LOW_RON.20316"),
        DTCStruct(20577, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_AMP_ORNG_MAX.20577"),
        DTCStruct(20578, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_AMP_ORNG_MIN.20578"),
        DTCStruct(
            20590,
            "P115800",
            "Manifold abs. press. sens. Implausible signal",
            "SV_ERR_SYM_AR_PL_MAX_LAM_MIN.20590"
        ),
        DTCStruct(
            20593,
            "P115800",
            "Manifold abs. press. sens. Implausible signal",
            "SV_ERR_SYM_AR_PL_MIN_LAM_MAX.20593"
        ),
        DTCStruct(
            20614,
            "P069900",
            "Sensor Reference Voltage C Circuit High",
            "SV_ERR_SYM_VCC_H_2.20614"
        ),
        DTCStruct(
            20615,
            "P069800",
            "Sensor Reference Voltage C Circuit Low",
            "SV_ERR_SYM_VCC_L_2.20615"
        ),
        DTCStruct(20628, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_LAMB_IC_COM_1.20628"),
        DTCStruct(20630, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_LAMB_IC_CONF_1.20630"),
        DTCStruct(
            20632,
            "P014000",
            "O2 Sensor Circ.,Bank1-Sensor2 No Activity Detected",
            "SV_ERR_SYM_LS_OC_1.20632"
        ),
        DTCStruct(
            20634,
            "P013700",
            "O2 Sensor Circ.,Bank1-Sensor2 Low Voltage",
            "SV_ERR_SYM_LS_SCG_1.20634"
        ),
        DTCStruct(
            20636,
            "P013800",
            "O2 Sensor Circ.,Bank1-Sensor2 High Voltage",
            "SV_ERR_SYM_LS_SCP_1.20636"
        ),
        DTCStruct(
            20638,
            "P003600",
            "O2 Sensor Heater Contr. Circ.(Bank1(1)Sensor 2)",
            "SV_ERR_SYM_LSH_LS_OC_1.20638"
        ),
        DTCStruct(
            20640,
            "P014100",
            "O2 Sensor Heater Circ.,Bank1-Sensor2 Malfunction",
            "SV_ERR_SYM_LSH_LS_PWR_L_1.20640"
        ),
        DTCStruct(
            20642,
            "P003700",
            "O2 Sensor Heater Contr. Circ.(Bank1(1)Sensor 2) Low",
            "SV_ERR_SYM_LSH_LS_SCG_1.20642"
        ),
        DTCStruct(
            20644,
            "P003800",
            "O2 Sensor Heater Contr. Circ.(Bank1(1)Sensor 2) High",
            "SV_ERR_SYM_LSH_LS_SCP_1.20644"
        ),
        DTCStruct(
            20646,
            "P003000",
            "O2 Sensor Heater Contr. Circ.(Bank1(1)Sensor 1)",
            "SV_ERR_SYM_LSH_LSL_OC_1.20646"
        ),
        DTCStruct(
            20648,
            "P013500",
            "O2 Sensor Heater Circ.,Bank1-Sensor1 Malfunction",
            "SV_ERR_SYM_LSH_LSL_PWR_L_1.20648"
        ),
        DTCStruct(
            20650,
            "P003100",
            "O2 Sensor Heater Contr. Circ.(Bank1(1)Sensor 1) Low",
            "SV_ERR_SYM_LSH_LSL_SCG_1.20650"
        ),
        DTCStruct(
            20652,
            "P003200",
            "O2 Sensor Heater Contr. Circ.(Bank1(1)Sensor 1) High",
            "SV_ERR_SYM_LSH_LSL_SCP_1.20652"
        ),
        DTCStruct(
            20654,
            "P225100",
            "O2 Sensor Negative Current Control Circuit Bank 1 Sensor 1 open",
            "SV_ERR_SYM_LSL_OC_VG_1.20654"
        ),
        DTCStruct(
            20656,
            "P223700",
            "O2 Sensor Positive Current Control Circuit Bank 1 Sensor 1 Open",
            "SV_ERR_SYM_LSL_OC_VIP_1.20656"
        ),
        DTCStruct(
            20658,
            "P224300",
            "O2 Sensor Reference Voltage Circuit Bank 1 Sensor 1 Open",
            "SV_ERR_SYM_LSL_OC_VN_1.20658"
        ),
        DTCStruct(
            20662,
            "P013100",
            "O2 Sensor Circ.,Bank1-Sensor1 Low Voltage",
            "SV_ERR_SYM_LSL_SCG_1.20662"
        ),
        DTCStruct(
            20664,
            "P013200",
            "O2 Sensor Circ.,Bank1-Sensor1 High Voltage",
            "SV_ERR_SYM_LSL_SCP_1.20664"
        ),
        DTCStruct(
            21266,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_PRS_BRAKE_CAN.21266"
        ),
        DTCStruct(
            21297,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_AC_LGT_TCS8_CAN.21297"
        ),
        DTCStruct(
            21302,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_AC_TRV_TCS2.21302"
        ),
        DTCStruct(
            21312,
            "U042800",
            "Invalid Data Received From Steering Angle Sensor Module",
            "SV_ERR_SYM_ANG_PSTE.21312"
        ),
        DTCStruct(
            21319,
            "U044700",
            "Invalid Data Received From Gateway A",
            "SV_ERR_SYM_CTR_KM_CAN.21319"
        ),
        DTCStruct(
            21320,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_CTR_WHEEL_FN_LE_CAN.21320"
        ),
        DTCStruct(
            21321,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_CTR_WHEEL_FN_RI_CAN.21321"
        ),
        DTCStruct(
            21322,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_CTR_WHEEL_RE_LE_CAN.21322"
        ),
        DTCStruct(
            21323,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_CTR_WHEEL_RE_RI_CAN.21323"
        ),
        DTCStruct(
            21329,
            "U045600",
            "Invalid Data Received From Coolant Temperature Control Module",
            "SV_ERR_SYM_ECF_REQ_AC_CAN.21329"
        ),
        DTCStruct(
            21388,
            "P301900",
            "Exhaust door valve 1 Open circuit",
            "SV_ERR_SYM_EF_EL_OC_0.21388"
        ),
        DTCStruct(
            21389,
            "P301800",
            "Exhaust door valve 1 Short circuit to ground",
            "SV_ERR_SYM_EF_EL_SCG_0.21389"
        ),
        DTCStruct(
            21390,
            "P301700",
            "Exhaust door valve 1 Short circuit to B+",
            "SV_ERR_SYM_EF_EL_SCP_0.21390"
        ),
        DTCStruct(
            21391,
            "P14AB00",
            "Exhaust door control unit mechanical malfunction",
            "SV_ERR_SYM_PWM_EF_FB_1_0.21391"
        ),
        DTCStruct(
            21392,
            "P14AB00",
            "Exhaust door control unit mechanical malfunction",
            "SV_ERR_SYM_PWM_EF_FB_2_0.21392"
        ),
        DTCStruct(
            21393,
            "P14AB00",
            "Exhaust door control unit mechanical malfunction",
            "SV_ERR_SYM_PWM_EF_FB_3_0.21393"
        ),
        DTCStruct(
            21399,
            "P302300",
            "Exhaust door valve 2 Open circuit",
            "SV_ERR_SYM_EF_EL_OC_1.21399"
        ),
        DTCStruct(
            21400,
            "P302200",
            "Exhaust door valve 2 Short circuit to ground",
            "SV_ERR_SYM_EF_EL_SCG_1.21400"
        ),
        DTCStruct(
            21401,
            "P302100",
            "Exhaust door valve 2 Short circuit to B+",
            "SV_ERR_SYM_EF_EL_SCP_1.21401"
        ),
        DTCStruct(
            21402,
            "P14AC00",
            "Exhaust door control unit 2 mechanical malfunction",
            "SV_ERR_SYM_PWM_EF_FB_1_1.21402"
        ),
        DTCStruct(
            21403,
            "P14AC00",
            "Exhaust door control unit 2 mechanical malfunction",
            "SV_ERR_SYM_PWM_EF_FB_2_1.21403"
        ),
        DTCStruct(
            21404,
            "P14AC00",
            "Exhaust door control unit 2 mechanical malfunction",
            "SV_ERR_SYM_PWM_EF_FB_3_1.21404"
        ),
        DTCStruct(
            21406,
            "P044100",
            "EVAP Emission Contr.Sys.Incorrect Purge Flow",
            "SV_ERR_SYM_MEC_OPEN_CPS_1.21406"
        ),
        DTCStruct(
            21417,
            "P261000",
            "ECM/PCM Internal Engine Off Timer Performance",
            "SV_ERR_SYM_T_ES_L_POW_CHK.21417"
        ),
        DTCStruct(
            21419,
            "P261000",
            "ECM/PCM Internal Engine Off Timer Performance",
            "SV_ERR_SYM_T_ES_VLD_CHK.21419"
        ),
        DTCStruct(
            21471,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_LMLev_bLevLim_VW.21471"
        ),
        DTCStruct(
            21564,
            "P151E00",
            "Reduced speed threshold for oil pressure switching",
            "SV_ERR_SYM_ChaDiag_bChaElg1Prot_VW.21564"
        ),
        DTCStruct(
            21624,
            "U000100",
            "High Speed CAN Communication Bus",
            "SV_ERR_SYM_COM_0_BOFF.21624"
        ),
        DTCStruct(
            21625,
            "U041400",
            "Invalid Data Received From Four-Wheel Drive Clutch Control Module",
            "SV_ERR_SYM_COM_4WD_PLAUS.21625"
        ),
        DTCStruct(
            21626,
            "U011400",
            "Lost Communication With Four-Wheel Drive Clutch Control Module",
            "SV_ERR_SYM_COM_4WD_TOUT.21626"
        ),
        DTCStruct(
            21629,
            "U042400",
            "Invalid Data Received From HVAC Control Module",
            "SV_ERR_SYM_COM_CCU_PLAUS.21629"
        ),
        DTCStruct(
            21630,
            "U016400",
            "Lost Communication With HVAC Control Module",
            "SV_ERR_SYM_COM_CCU_TOUT.21630"
        ),
        DTCStruct(
            21635,
            "U041700",
            "Invalid Data Received From Park Brake Control Module",
            "SV_ERR_SYM_COM_EPB_PLAUS.21635"
        ),
        DTCStruct(
            21636,
            "U012800",
            "Lost Communication With Brake System Control Module",
            "SV_ERR_SYM_COM_EPB_TOUT.21636"
        ),
        DTCStruct(
            21637,
            "U042500",
            "Invalid Data Received From Auxiliary Heater Control Module",
            "SV_ERR_SYM_COM_ICH_PLAUS.21637"
        ),
        DTCStruct(
            21638,
            "U016600",
            "Lost Communication With Auxiliary Heater Control Module",
            "SV_ERR_SYM_COM_ICH_TOUT.21638"
        ),
        DTCStruct(
            21639,
            "U100B00",
            "Access and start authorization Implausible signal",
            "SV_ERR_SYM_COM_KLG_PLAUS.21639"
        ),
        DTCStruct(
            21640,
            "U100A00",
            "Access and start authorization No communication",
            "SV_ERR_SYM_COM_KLG_TOUT.21640"
        ),
        DTCStruct(
            21644,
            "U109500",
            "Electronic steering column lock control module No communication",
            "SV_ERR_SYM_COM_PSTE_LOCK_TOUT.21644"
        ),
        DTCStruct(
            21645,
            "U041900",
            "Invalid Data Received From Steering Effort Control Module",
            "SV_ERR_SYM_COM_SCU_PLAUS.21645"
        ),
        DTCStruct(
            21646,
            "U013000",
            "Lost Communication With Steering Effort Control Module",
            "SV_ERR_SYM_COM_SCU_TOUT.21646"
        ),
        DTCStruct(
            21651,
            "U112200",
            "Databus implausible message",
            "SV_ERR_SYM_COM_STS_PLAUS.21651"
        ),
        DTCStruct(21652, "U112100", "Databus missing message", "SV_ERR_SYM_COM_STS_TOUT.21652"),
        DTCStruct(
            21653,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_COM_TCU_PLAUS.21653"
        ),
        DTCStruct(21654, "U010100", "Lost Communication with TCM", "SV_ERR_SYM_COM_TCU_TOUT.21654"),
        DTCStruct(
            21655,
            "U100400",
            "Towing recognition control module Implausible signal",
            "SV_ERR_SYM_COM_TRLCU_PLAUS.21655"
        ),
        DTCStruct(
            21656,
            "U100300",
            "Towing recognition control module No Communication",
            "SV_ERR_SYM_COM_TRLCU_TOUT.21656"
        ),
        DTCStruct(
            21657,
            "U044700",
            "Invalid Data Received From Gateway A",
            "SV_ERR_SYM_GEN_LOAD_POW_CAN.21657"
        ),
        DTCStruct(
            21658,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_TOIL_GEAR_TM.21658"
        ),
        DTCStruct(
            21659,
            "U042400",
            "Invalid Data Received From HVAC Control Module",
            "SV_ERR_SYM_TQ_ACC_CAN.21659"
        ),
        DTCStruct(
            21660,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_TQ_LM_GB_PT_MAX_ETCU.21660"
        ),
        DTCStruct(
            21661,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_TQ_LOSS_GB_CAN.21661"
        ),
        DTCStruct(
            22007,
            "U043300",
            "Invalid Data Received From Cruise Control Front Distance Range Sensor",
            "SV_ERR_SYM_COM_DCC_PLAUS.22007"
        ),
        DTCStruct(
            22008,
            "U023500",
            "Lost Communication With Cruise Control Front Distance Range Sensor",
            "SV_ERR_SYM_COM_DCC_TOUT.22008"
        ),
        DTCStruct(
            22043,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_MCC_ATIC_111.22043"
        ),
        DTCStruct(
            22045,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_MCC_ATIC_140_DEV_0.22045"
        ),
        DTCStruct(
            22046,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_MCC_ATIC_140_DEV_1.22046"
        ),
        DTCStruct(
            22049,
            "U042400",
            "Invalid Data Received From HVAC Control Module",
            "SV_ERR_SYM_ACP_CAN.22049"
        ),
        DTCStruct(
            22050,
            "U041900",
            "Invalid Data Received From Steering Effort Control Module",
            "SV_ERR_SYM_CAN_ANG_SCU.22050"
        ),
        DTCStruct(
            22052,
            "U000200",
            "High Speed CAN Communication Bus Performance",
            "SV_ERR_SYM_COM_0_TOT.22052"
        ),
        DTCStruct(
            22054,
            "U10BB00",
            "Power steering control module Implausible signal",
            "SV_ERR_SYM_COM_EPS_PLAUS.22054"
        ),
        DTCStruct(
            22055,
            "U013100",
            "Lost Communication With Power Steering Control Module",
            "SV_ERR_SYM_COM_EPS_TOUT.22055"
        ),
        DTCStruct(
            22056,
            "U045A00",
            "Invalid Data Received From Parking Assist Control Module A",
            "SV_ERR_SYM_COM_PARK_ASI_PLAUS.22056"
        ),
        DTCStruct(
            22057,
            "U015900",
            "Lost Communication With Parking Assist Control Module A",
            "SV_ERR_SYM_COM_PARK_ASI_TOUT.22057"
        ),
        DTCStruct(
            22058,
            "U042800",
            "Invalid Data Received From Steering Angle Sensor Module",
            "SV_ERR_SYM_COM_PSTE_PLAUS.22058"
        ),
        DTCStruct(
            22059,
            "U012600",
            "Lost Communication With Steering Angle Sensor Module",
            "SV_ERR_SYM_COM_PSTE_TOUT.22059"
        ),
        DTCStruct(
            22062,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_DRG_TCU.22062"
        ),
        DTCStruct(
            22063,
            "U042300",
            "Invalid Data Received From Instrument Panel Cluster Control Module",
            "SV_ERR_SYM_FTL_CAN.22063"
        ),
        DTCStruct(
            22064,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_GEAR_RATIO_TCU1.22064"
        ),
        DTCStruct(
            22065,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_N_CONV.22065"
        ),
        DTCStruct(
            22073,
            "U042400",
            "Invalid Data Received From HVAC Control Module",
            "SV_ERR_SYM_TQ_AC_FAN_CAN.22073"
        ),
        DTCStruct(
            22076,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_TQI_REL_MSR_REQ_CAN.22076"
        ),
        DTCStruct(
            22092,
            "U042300",
            "Invalid Data Received From Instrument Panel Cluster Control Module",
            "SV_ERR_SYM_KBI_TANKFUELL_PROZ.22092"
        ),
        DTCStruct(
            22096,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_TQI_GB_INTV_FAST_ETCU.22096"
        ),
        DTCStruct(
            22097,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_TQI_REL_ASR_REQ_SLOW.22097"
        ),
        DTCStruct(
            22099,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_MCC_ATIC_139_LS.22099"
        ),
        DTCStruct(
            22100,
            "P060300",
            "Internal Contr.Module (KAM) Error",
            "SV_ERR_SYM_MCC_ATIC_139_PS.22100"
        ),
        DTCStruct(
            22136,
            "P011600",
            "Engine Coolant Temperature Sensor 1 Circuit Range/Performance",
            "SV_ERR_SYM_TCE_NEG_OFS_CHK.22136"
        ),
        DTCStruct(
            22260,
            "P011100",
            "Intake Air Temperature Sensor 1 Bank 1 Circuit Range/Performance",
            "SV_ERR_SYM_TEMP_CST_PLAUS_TIG_IM.22260"
        ),
        DTCStruct(
            22261,
            "P007100",
            "Ambient Air Temperature Sensor Circuit Range/Performance",
            "SV_ERR_SYM_TEMP_CST_PLAUS_TAA.22261"
        ),
        DTCStruct(
            22262,
            "P218300",
            "Engine Coolant Temperature Sensor 2 Circuit Range/Performance",
            "SV_ERR_SYM_TEMP_CST_PLAUS_TCR.22262"
        ),
        DTCStruct(
            23079,
            "U105400",
            "Information electronics control module 1 No Communication",
            "SV_ERR_SYM_COM_MFE_INFO_TOUT.23079"
        ),
        DTCStruct(
            23096,
            "P011300",
            "Intake Air Temperature Sensor 1 Bank 1 Circuit High",
            "SV_ERR_SYM_LTS_AIR_OC_IM.23096"
        ),
        DTCStruct(
            23097,
            "P011200",
            "Intake Air Temperature Sensor 1 Bank 1 Circuit Low",
            "SV_ERR_SYM_LTS_AIR_SCG_IM.23097"
        ),
        DTCStruct(
            23128,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_VEL_WHL_FN_LE_COM.23128"
        ),
        DTCStruct(
            23129,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_VEL_WHL_FN_RI_COM.23129"
        ),
        DTCStruct(
            23130,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_VEL_WHL_RE_LE_COM.23130"
        ),
        DTCStruct(
            23131,
            "U041500",
            "Invalid Data Received From Anti-Lock Brake System Control Module",
            "SV_ERR_SYM_VEL_WHL_RE_RI_COM.23131"
        ),
        DTCStruct(
            23142,
            "U042300",
            "Invalid Data Received From Instrument Panel Cluster Control Module",
            "SV_ERR_SYM_KBI_STANDZEIT_02.23142"
        ),
        DTCStruct(
            23143,
            "U042300",
            "Invalid Data Received From Instrument Panel Cluster Control Module",
            "SV_ERR_SYM_VS_ICL_DISP_COM.23143"
        ),
        DTCStruct(
            23175,
            "P007200",
            "Ambient Air Temperature Sensor Circuit Low",
            "SV_ERR_SYM_TAA_INTC_EL_L.23175"
        ),
        DTCStruct(
            23176,
            "P007000",
            "Ambient Air Temperature Sensor Circuit",
            "SV_ERR_SYM_TAA_INTC_EL_H.23176"
        ),
        DTCStruct(
            23648,
            "P16C100",
            "Heater support pump Open circuit",
            "SV_ERR_SYM_PWM_CWP_EL_OC_1.23648"
        ),
        DTCStruct(
            23649,
            "P16BF00",
            "Heater support pump Short circuit to ground",
            "SV_ERR_SYM_PWM_CWP_EL_SCG_1.23649"
        ),
        DTCStruct(
            23650,
            "P16C000",
            "Heater support pump Short circuit to B+",
            "SV_ERR_SYM_PWM_CWP_EL_SCP_1.23650"
        ),
        DTCStruct(
            23651,
            "P16C600",
            "Heater support pump Dry running",
            "SV_ERR_SYM_PWM_CWP_FB_1_0.23651"
        ),
        DTCStruct(
            23652,
            "P16C700",
            "Heater support pump locked",
            "SV_ERR_SYM_PWM_CWP_FB_1_1.23652"
        ),
        DTCStruct(
            23653,
            "P16C800",
            "Heater support pump Overheated",
            "SV_ERR_SYM_PWM_CWP_FB_1_2.23653"
        ),
        DTCStruct(
            24576,
            "U041400",
            "Invalid Data Received From Four-Wheel Drive Clutch Control Module",
            "SV_ERR_SYM_TQ_LIM_4WD_PROT_MAX_COM.24576"
        ),
        DTCStruct(25214, "P218100", "Cooling System Performance", "SV_ERR_SYM_TH_AUX.25214"),
        DTCStruct(
            25279,
            "U040200",
            "Invalid Data Received From Transmission Control Module",
            "SV_ERR_SYM_TOIL_TRANS.25279"
        ),
        DTCStruct(
            25574,
            "U043300",
            "Invalid Data Received From Cruise Control Front Distance Range Sensor",
            "SV_ERR_SYM_TQI_REQ_DCC.25574"
        ),
        DTCStruct(
            26279,
            "P010600",
            "Manifold Abs.Pressure or Bar.Pressure Range/Performance",
            "SV_ERR_SYM_PRS_IM_PLAUS.26279"
        ),
        DTCStruct(
            26363,
            "P023600",
            "Turbocharger Boost Sensor (A) Circ. Range/Performance",
            "SV_ERR_SYM_PRS_UP_THR_PLAUS.26363"
        ),
        DTCStruct(
            26370,
            "P006800",
            "MAP/MAF - Throttle Position Correlation",
            "SV_ERR_SYM_PRS_IM_CTL_MIN.26370"
        ),
        DTCStruct(26372, "P227900", "Intake Air System Leak", "SV_ERR_SYM_AR_PL_MAX_LAM_RNG.26372"),
        DTCStruct(
            26374,
            "P307A00",
            "Intake system Air flow too low",
            "SV_ERR_SYM_AR_PL_MIN_LAM_RNG.26374"
        ),
        DTCStruct(26376, "P227900", "Intake Air System Leak", "SV_ERR_SYM_PUT_MAX_LAM_RNG.26376"),
        DTCStruct(27740, "P060600", "ECM/PCM Processor", "SV_ERR_SYM_AMP_PLAUS.27740"),
        DTCStruct(
            28511,
            "U043300",
            "Invalid Data Received From Cruise Control Front Distance Range Sensor",
            "SV_ERR_SYM_AC_DCC_FOL_CTL.28511"
        ),
        DTCStruct(
            28541,
            "U042400",
            "Invalid Data Received From HVAC Control Module",
            "SV_ERR_SYM_TQ_ACC_CAN_PQX.28541"
        ),
        DTCStruct(
            29095,
            "P211200",
            "Throttle Actuator Control System - Stuck closed",
            "SV_ERR_SYM_TPS_JAM_CLOSE.29095"
        ),
        DTCStruct(29376, "P138800", "Ctrl. module faulty", "SV_ERR_SYM_TRM_TSK_06_MON_1.29376"),
        DTCStruct(29377, "P138800", "Ctrl. module faulty", "SV_ERR_SYM_TRM_MOTOR_20_MON_1.29377"),
        DTCStruct(
            29381,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TRANS_MON_1.29381"
        ),
        DTCStruct(
            29382,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TQI_MON_1.29382"
        ),
        DTCStruct(
            29383,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TQ_MIN_CLU_MON_1.29383"
        ),
        DTCStruct(
            29384,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TQ_MAX_CLU_MON_1.29384"
        ),
        DTCStruct(
            29385,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TPS_MON_1.29385"
        ),
        DTCStruct(29386, "P168000", "Reset-resistant limp-home", "SV_ERR_SYM_RST_CHK_MON_1.29386"),
        DTCStruct(
            29387,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_MSR_MON_1.29387"
        ),
        DTCStruct(
            29388,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_MFF_MON_1.29388"
        ),
        DTCStruct(
            29389,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_GS_INC_MON_1.29389"
        ),
        DTCStruct(
            29391,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_BLS_BTS_MON_1.29391"
        ),
        DTCStruct(
            30705,
            "P00AF00",
            "Turbocharger/Supercharger Boost Control A Module Performance",
            "SV_ERR_SYM_BPA_STUCK_CLOSE_1.30705"
        ),
        DTCStruct(
            30706,
            "P00AF00",
            "Turbocharger/Supercharger Boost Control A Module Performance",
            "SV_ERR_SYM_BPA_STUCK_OPEN_1.30706"
        ),
        DTCStruct(
            30787,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TQI_P_D_IS_MON_1.30787"
        ),
        DTCStruct(
            30788,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TQI_N_MAX_MON_1.30788"
        ),
        DTCStruct(
            30789,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TQI_I_IS_MON_1.30789"
        ),
        DTCStruct(
            30790,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_TQ_LOSS_MON_1.30790"
        ),
        DTCStruct(
            30791,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_PVS_MON_1.30791"
        ),
        DTCStruct(
            30792,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_N_32_MON_1.30792"
        ),
        DTCStruct(
            30794,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_STST_MON_1.30794"
        ),
        DTCStruct(
            31537,
            "P060C00",
            "Internal Control Module Main Processor Performance",
            "SV_ERR_SYM_CRU_MON_1.31537"
        ),
        DTCStruct(31538, "P138800", "Ctrl. module faulty", "SV_ERR_SYM_CONV_MON_1.31538")
    )
}