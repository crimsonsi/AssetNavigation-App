package ke.co.osl.utcollectorapp.models

data class WaterPipesBody(
  val Material:String,
  val Coordinates:ArrayList<ArrayList<Double>>,
  val Type:String,
  val Route:String,
  val Zone:String,
  val Size:String,
  val Status:String,
  val Remarks:String,
  val UserID:String,
)