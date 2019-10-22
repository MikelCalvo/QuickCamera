package mikelcalvo.net.quickcamera.sample

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import mikelcalvo.net.quickcamera.QuickCameraBitmap
import mikelcalvo.net.quickcamera.QuickCameraSetup

class MainActivity : AppCompatActivity() {

    private var cameraOnResume: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        creditsText.movementMethod = LinkMovementMethod.getInstance()

        QuickCameraSetup().cameraFlash = "auto"
        QuickCameraSetup().cameraWhiteBalance = "auto"
        QuickCameraSetup().cameraHDR = "on"
        QuickCameraSetup().cameraSize = "square"
        QuickCameraSetup().pictureQualityPercentage = 70

        launchCamBtn.setOnClickListener {
            cameraOnResume = true
            QuickCameraSetup().launch(this)
        }


    }

    override fun onResume() {
        if(!cameraOnResume){
            super.onResume()
        }else{
            if(QuickCameraBitmap.getImage() != null){
                picturePreviewHolder.visibility = View.VISIBLE
                launchCamBtn.text = getString(R.string.changePicture)
                resultImage.setImageBitmap(QuickCameraBitmap.getImage())
            }else{
                Toast.makeText(this, "Image not found, try again", Toast.LENGTH_SHORT).show()
            }
            super.onResume()
        }
    }
}
