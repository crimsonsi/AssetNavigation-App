package ke.co.osl.assetnavigationapp.models

data class ManHoleGetBody(
   val ID: String,
   val ObjectID: String,
   val Location:String,
   val Depth: String,
   val Material:String,
   val Status:String,
   val Route:String,
   val User: String
)