package ke.co.osl.utcollectorapp.models

data class CustomerDetailsBody(
   val account:String,
   val meterNo:String,
   val Longitude: Double,
   val Latitude: Double,
   val name: String,
   val Zone:String,
   val Route: String,
   val DMA: String,
   val Location: String,
   val SchemeName: String
)