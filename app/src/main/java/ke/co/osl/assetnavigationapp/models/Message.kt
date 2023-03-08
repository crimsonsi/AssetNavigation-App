package ke.co.osl.assetnavigationapp.models

data class Message (var success: String, var token: String, var error: String,var coordinates: ArrayList<ArrayList<Double>>)
