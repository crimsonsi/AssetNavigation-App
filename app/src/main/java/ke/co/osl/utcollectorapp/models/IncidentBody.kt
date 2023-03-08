package ke.co.osl.utcollectorapp.models

import android.graphics.Bitmap
import android.media.Image

data class IncidentBody(
   val Type:String,
   val Longitude: Double,
   val Latitude:Double,
   val Description:String,
   val Image: String
)