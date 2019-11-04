package mikelcalvo.net.quickcamera

import android.content.Context
import android.content.Intent

object QuickCameraSetup {
    /**
     * Object to store the user selected configuration params
     */

    @JvmStatic var cameraFlash: String = "off"
    @JvmStatic var cameraWhiteBalance: String = "auto"
    @JvmStatic var cameraHDR: String = "on"
    @JvmStatic var cameraSize: String = "square"
    @JvmStatic var cameraToolbarTitle: String = "Camera"
    @JvmStatic var pictureQualityPercentage: Int = 100

    @JvmStatic fun launch(context: Context){
        context.startActivity(Intent(context, QuickCamera::class.java))
    }
}