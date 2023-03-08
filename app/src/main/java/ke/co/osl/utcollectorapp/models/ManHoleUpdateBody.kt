package ke.co.osl.utcollectorapp.models

data class ManHoleUpdateBody(
   val Location:String,
   val Longitude: Double,
   val Latitude: Double,
   val Depth: String,
   val Material:String,
   val Status:String,
   val Route:String,
   val User: String

)