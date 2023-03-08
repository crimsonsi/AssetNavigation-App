package ke.co.osl.utcollectorapp.models

data class ValveGetBody(
   val ID: String,
   val ObjectID: String,
   val DMA: String,
   val Status:String,
   val Size: String,
   val Zone:String,
   val SchemeName: String,
   val Route:String,
   val Location:String,
   val Type: String,
   val Remarks: String,
   val User: String
)