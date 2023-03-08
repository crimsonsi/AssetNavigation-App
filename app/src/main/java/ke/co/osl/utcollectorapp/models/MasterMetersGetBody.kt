package ke.co.osl.utcollectorapp.models

data class MasterMetersGetBody(
   val ID: String,
   val ObjectID: String,
   val Name:String,
   val Size:String,
   val Route:String,
   val Zone:String,
   val DMA: String,
   val Cover: String,
   val Location: String,
   val Remarks: String,
   val User: String,
   )