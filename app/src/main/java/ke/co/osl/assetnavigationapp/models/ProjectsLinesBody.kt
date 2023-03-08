package ke.co.osl.assetnavigationapp.models

data class ProjectsLinesBody(
  val LineName:String,
  val Material: String,
  val Intake: String,
  val Coordinates:ArrayList<ArrayList<Double>>,
  val Type: String,
  val DMA: String,
  val SchemeName: String,
  val Route:String,
  val Zone:String,
  val Size: String,
  val UserID:String
)