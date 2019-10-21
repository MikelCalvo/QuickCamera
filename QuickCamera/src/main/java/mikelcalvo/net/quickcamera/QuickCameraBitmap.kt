package mikelcalvo.net.quickcamera

import android.graphics.Bitmap
import java.lang.ref.WeakReference

object QuickCameraBitmap {

    private var savedImage: WeakReference<Bitmap>? = null

    fun setImage(image: Bitmap?) {
        savedImage = if (image != null) WeakReference(image) else null
    }

    fun getImage(): Bitmap? {
        return savedImage?.get()
    }

    fun dispose() {
        setImage(null)
    }
}
