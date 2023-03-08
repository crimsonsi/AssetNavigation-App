package ke.co.osl.utcollectorapp.models

data class MasterMetersBody(
   val Name:String,
   val Size:String,
   val Route:String,
   val Zone:String,
   val DMA: String,
   val Cover: String,
   val Location: String,
   val Remarks: String,
   val User: String,
   val Longitude: Double,
   val Latitude: Double,
   )