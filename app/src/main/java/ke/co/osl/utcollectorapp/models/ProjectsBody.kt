package ke.co.osl.utcollectorapp.models

data class ProjectsBody(
   val Name:String,
   val Zone:String,
   val Route:String,
   val Phone:String,
   val Latitude: Double,
   val Longitude: Double,
   val User: String
)