package com.lemon.lib_base.config

interface AppConstants {

    object SpKey{
        const val LOGIN_NAME: String = "login_name"
        const val USER_ID: String = "user_id"
        const val USER_JSON_DATA: String = "user_json_data"
        const val SYS_UI_MODE: String = "sys_ui_mode"
        const val USER_UI_MODE: String = "user_ui_mode"
        const val READ_HISTORY_STATE: String = "read_history_state"
    }


    object Router{
        object Login {
            const val F_LOGIN = "/login/LoginFragment"
            const val F_REGISTER = "/login/RegisterFragment"
        }

        object Main {
            const val A_TEST = "/main/TestActivity"
            const val A_MAIN = "/main/MainActivity"
            const val F_HOME = "/main/HomeFragment"
            const val F_QR_SCAN = "/main/QRScanFragment"
        }
    }
}