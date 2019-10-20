/*
 * Last Modified by Mikel Calvo on 10/20/19 3:10 AM
 */

/*
 * Last Modified by Mikel Calvo on 10/20/19 3:09 AM
 */

/*
 * Last Modified by Mikel Calvo on 10/20/19 3:06 AM
 */

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
