package mikelcalvo.net.quickcamera

import android.graphics.Bitmap
import java.lang.ref.WeakReference

object QuickCameraBitmap {

    @JvmStatic private var savedImage: WeakReference<Bitmap>? = null

    @JvmStatic fun setImage(image: Bitmap?) {
        savedImage = if (image != null) WeakReference(image) else null
    }

    @JvmStatic fun getImage(): Bitmap? {
        return savedImage?.get()
    }

    @JvmStatic fun dispose() {
        setImage(null)
    }
}
