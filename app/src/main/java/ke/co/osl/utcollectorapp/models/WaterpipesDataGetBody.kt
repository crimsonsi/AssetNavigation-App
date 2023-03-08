package ke.co.osl.utcollectorapp.models

data class WaterpipesDataGetBody(
  val ID:String,
  val ObjectID:String,
  val LineName:String,
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