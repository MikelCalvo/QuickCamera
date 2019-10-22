package mikelcalvo.net.quickcamera

import android.content.Context
import android.content.Intent

class QuickCameraSetup {
    /**
     * Object to store the user selected configuration params
     */

    var cameraFlash: String = "off"
    var cameraWhiteBalance: String = "on"
    var cameraHDR: String = "on"
    var cameraSize: String = "square"
    var cameraToolbarTitle: String = "camera"
    var pictureQualityPercentage: Int = 100

    fun launch(context: Context){
        context.startActivity(Intent(context, QuickCamera::class.java))
    }
}