package ke.co.osl.utcollectorapp.models

data class SewerlineDataUpdateBody(
  val Material:String,
  val Type:String,
  val Route:String,
  val Zone:String,
  val SchemeName: String,
  val Size:String,
  val Status:String,
  val Remarks:String,
  val User: String
)