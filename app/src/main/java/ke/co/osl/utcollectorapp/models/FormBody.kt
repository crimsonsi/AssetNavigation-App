package ke.co.osl.utcollectorapp.models

data class FormBody(
   val Name: String,
   val Longitude: Double,
   val Latitude: Double,
   val AccountNo:String,
   val MeterNo: String,
   val MeterSize:String,
   val MeterStatus: String,
   val User: String,
   val Zone:String,
   val Route: String,
   val DMA: String,
   val Location: String,
   val SchemeName: String,
   val Brand: String,
   val Material: String,
   val Class:String,
   val ConnectionStatus:String,
   val AccountStatus:String,
   val Remarks: String,
)