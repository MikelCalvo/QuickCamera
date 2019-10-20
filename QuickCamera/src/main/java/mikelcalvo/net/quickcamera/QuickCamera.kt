package mikelcalvo.net.quickcamera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class QuickCamera : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_camera)

        when(QuickCameraSetup.cameraFlash){
            "on" -> println("change this")
            "off" -> println("change this")
            "auto" -> println("change this")
            "torch" -> println("change this")
            else -> println("change this")
        }

        when(QuickCameraSetup.cameraWhiteBalance){
            "auto" -> println("change this")
            "incandescent" -> println("change this")
            "daylight" -> println("change this")
            "cloudy" -> println("change this")
            else -> println("change this")
        }

        when(QuickCameraSetup.cameraHDR){
            "on" -> println("change this")
            "off" -> println("change this")
            else -> println("change this")
        }

        when(QuickCameraSetup.cameraSize){
            "square" -> println("change this")
            "full" -> println("change this")
            else -> println("change this")
        }

        if(QuickCameraSetup.pictureQualityPercentage in 1..100){

        }else{

        }

    }
}

object QuickCameraSetup{

    var cameraFlash: String = "default"
    var cameraWhiteBalance: String = "default"
    var cameraHDR: String = "default"
    var cameraSize: String = "default"
    var pictureQualityPercentage: Int = 100

    fun launch(context: Context){
        context.startActivity(Intent(context, QuickCamera::class.java))
    }
}