package ke.co.osl.utcollectorapp.models

data class ProjectsLinesUpdateBody(
  val LineName:String,
  val Material: String,
  val Intake: String,
  val Type: String,
  val DMA: String,
  val SchemeName: String,
  val Route:String,
  val Zone:String,
  val Size: String,
  val User:String
)