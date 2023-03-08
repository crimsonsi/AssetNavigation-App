package ke.co.osl.assetnavigationapp.models

data class CustomerDetailsBody(
   val ID: String,
   val Name: String,
   val Longitude: Double,
   val Latitude: Double,
   val Zone: String,
   val Route: String,
   val DMA: String,
   val Location: String,
   val SchemeName: String,
   val Brand: String,
   val Material: String,
   val Class: String,
   val AccountNo: String,
   val MeterNo: String,
   val MeterSize: String,
   val MeterStatus: String,
   val ConnectionStatus: String,
   val AccountStatus: String,
   val User: String,
   val updatedAt: String,
   val Remarks: String,
   val Type: String
)