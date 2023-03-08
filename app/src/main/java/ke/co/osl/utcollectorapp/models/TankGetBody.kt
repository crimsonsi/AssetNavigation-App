package ke.co.osl.utcollectorapp.models

data class TankGetBody(
   val ID: String,
   val ObjectID: String,
   val Name:String,
   val Zone: String,
   val Elevation: String,
   val Area:String,
   val Location: String,
   val InletPipe:String,
   val OutletPipe:String,
   val Material:String,
   val Capacity:String,
   val Status:String,
   val Remarks: String,
   val User: String
)