package ke.co.osl.utcollectorapp.models

data class WaterLinesCoordsBody(
  val LineName:String,
  val Coordinates:ArrayList<ArrayList<Double>>,
  val Material:String,
  val Intake: String,
  val Type:String,
  val Function:String,
  val DMA:String,
  val SchemeName:String,
  val Route:String,
  val Zone:String,
  val Size:String,
  val Status:String,
  val Remarks:String
)