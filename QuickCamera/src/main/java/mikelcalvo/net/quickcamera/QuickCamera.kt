package mikelcalvo.net.quickcamera

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.controls.Hdr
import com.otaliastudios.cameraview.controls.WhiteBalance
import kotlinx.android.synthetic.main.activity_quick_camera.*
import java.io.ByteArrayOutputStream


class QuickCamera : AppCompatActivity() {

    var mFlashState = 1
    var mSquaredPicture = true
    var mConstraintSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_camera)

        /**
         * Return Arrow to Action Bar & custom title
         */
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = QuickCameraSetup.cameraToolbarTitle


        /**
         * Let's set the configuration
         */
        when(QuickCameraSetup.cameraFlash){
            "on" -> {
                mFlashState = 0
                quickCameraFlashController.setImageDrawable(resources.getDrawable(R.drawable.ic_flash_on, theme))
                quickCameraView.flash = Flash.ON
            }
            "off" -> {
                mFlashState = 1
                quickCameraFlashController.setImageDrawable(resources.getDrawable(R.drawable.ic_flash_off, theme))
                quickCameraView.flash = Flash.OFF
            }
            "auto" -> {
                mFlashState = 2
                quickCameraFlashController.setImageDrawable(resources.getDrawable(R.drawable.ic_flash_auto, theme))
                quickCameraView.flash = Flash.AUTO
            }
            "torch" -> {
                mFlashState = 3
                quickCameraFlashController.setImageDrawable(resources.getDrawable(R.drawable.ic_flash_torch, theme))
                quickCameraView.flash = Flash.TORCH
            }
        }

        when(QuickCameraSetup.cameraWhiteBalance){
            "auto" -> quickCameraView.whiteBalance = WhiteBalance.AUTO
            "incandescent" -> quickCameraView.whiteBalance = WhiteBalance.INCANDESCENT
            "daylight" -> quickCameraView.whiteBalance = WhiteBalance.DAYLIGHT
            "cloudy" -> quickCameraView.whiteBalance = WhiteBalance.CLOUDY
        }

        when(QuickCameraSetup.cameraHDR){
            "on" -> quickCameraView.hdr = Hdr.ON
            "off" -> quickCameraView.hdr = Hdr.OFF
        }

        when(QuickCameraSetup.cameraSize){
            "full" -> {
                mConstraintSet.connect(quickCameraView.id, ConstraintSet.TOP,
                    quickCameraParent.id, ConstraintSet.TOP)
                mConstraintSet.connect(quickCameraView.id, ConstraintSet.START,
                    quickCameraParent.id, ConstraintSet.START)
                mConstraintSet.connect(quickCameraView.id, ConstraintSet.END,
                    quickCameraParent.id, ConstraintSet.END)
                mConstraintSet.connect(quickCameraView.id, ConstraintSet.BOTTOM,
                    quickCameraParent.id, ConstraintSet.BOTTOM)

                mConstraintSet.applyTo(quickCameraParent)
                mSquaredPicture = false
            }
        }

        /**
         * We start the camera
         */
        quickCameraView.setLifecycleOwner(this)
        quickCameraView.open()

        /**
         * Picture Taken Listener
         */
        quickCameraView.addCameraListener(object: CameraListener(){
            override fun onPictureTaken(result: PictureResult) {
                result.toBitmap {
                    //TODO: ADD COMPRESSION

                    var mBitmap = it!!
                    if(QuickCameraSetup.pictureQualityPercentage in 1..99){
                        val byteArrayOutputStream = ByteArrayOutputStream()
                        it.compress(CompressFormat.JPEG, QuickCameraSetup.pictureQualityPercentage, byteArrayOutputStream)
                        mBitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().size)
                    }

                    if(mSquaredPicture){
                        QuickCameraBitmap.setImage(createSquaredBitmap(mBitmap))
                    }else{
                        QuickCameraBitmap.setImage(it)
                    }

                    finish()
                }
                super.onPictureTaken(result)
            }
        })


        /**
         * Camera facing direction change button Listener
         */
        quickCameraChangeController.setOnClickListener {
            quickCameraChangeController.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate))
            quickCameraView.toggleFacing()
        }

        /**
         * Camera flash change button Listener
         */
        quickCameraFlashController.setOnClickListener {toggleFlash()}

        /**
         * Camera action button Listener
         */
        quickCameraActionController.setOnClickListener {
            quickCameraView.takePicture()
        }

    }

    /**
     * If they press the toolbar back button the camera is closed
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun toggleFlash(){
        when(mFlashState){
            /**
             * 0 -> ON
             * 1 -> OFF
             * 2 -> AUTO
             */

            0 -> {
                mFlashState = 1
                quickCameraFlashController.setImageDrawable(resources.getDrawable(R.drawable.ic_flash_off, theme))
                quickCameraView.flash = Flash.OFF
            }
            1 -> {
                mFlashState = 2
                quickCameraFlashController.setImageDrawable(resources.getDrawable(R.drawable.ic_flash_auto, theme))
                quickCameraView.flash = Flash.AUTO
            }
            2 -> {
                mFlashState = 3
                quickCameraFlashController.setImageDrawable(resources.getDrawable(R.drawable.ic_flash_torch, theme))
                quickCameraView.flash = Flash.TORCH
            }
            3 -> {
                mFlashState = 0
                quickCameraFlashController.setImageDrawable(resources.getDrawable(R.drawable.ic_flash_on, theme))
                quickCameraView.flash = Flash.ON
            }
        }
    }

    private fun createSquaredBitmap(srcBmp: Bitmap): Bitmap {
        val dim = Math.min(srcBmp.width, srcBmp.height)
        val dstBmp = Bitmap.createBitmap(dim, dim, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(dstBmp)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(srcBmp, ((dim - srcBmp.width) / 2).toFloat(), ((dim - srcBmp.height) / 2).toFloat(), null)

        return dstBmp
    }
}