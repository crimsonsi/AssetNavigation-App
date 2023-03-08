package ke.co.osl.utcollectorapp.api

import ke.co.osl.utcollectorapp.models.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

        companion object {

         var BASE_URL = "http://102.222.147.190/api/"

            fun create() : ApiInterface {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                return retrofit.create(ApiInterface::class.java)
            }
        }

        @POST("reports/create")
        fun reportIncident(@Body body: MultipartBody) : Call<Message>

        @POST("mobile/login")
        fun loginUser(@Body loginBody: LoginBody) : Call<Message>

        @POST("mobile/meterreaderlogin")
        fun loginMeterReader(@Body loginBody: LoginBody) : Call<Message>

        @POST("customers/create")
        fun postCustomerMeters(@Body formBody: FormBody) : Call<Message>

        @PUT("customers/{id}")
        fun putCustomerMeters(@Path("id") id: String, @Body formBody1: FormBody1) : Call<Message>

        @GET("customers/details/{account}")
        fun searchCustomerDetails(@Path("account") accountno: String) : Call<List<dataBody>>

        @GET("customers/{accountno}")
        fun searchCustomerMeters(@Path("accountno") accountno: String) : Call<List<CustomerDetailsBody>>

        @PUT("customers/{id}")
        fun postForm2(@Path("id") id: String, @Body formBody2: formBody2) : Call<Message>

        @PUT("customers/{id}")
        fun postForm3( @Path("id") id: String,@Body formBody3: formBody3) : Call<Message>

        @GET("customers/totalmapped")
        fun showTotalMapped() : Call<Message>

        @POST("mobile/forgot")
        fun recoverPassword(@Body recoverPasswordBody: RecoverPasswordBody) : Call<Message>

        @PUT("mobile/{id}")
        fun changePassword(@Path("id") id: String, @Body changePasswordBody: ChangePasswordBody) : Call<Message>

        @GET("customers/paginated/{offset}")
        fun showMappedList(@Path("offset") offset: Int) : Call<mappedBody>

        @GET("sewerlines/paginated/{offset}")
        fun showSewerLines(@Path("offset") offset: Int) : Call<mappedSewerLinesBody>

        @GET("waterpipes/paginated/{offset}")
        fun showWaterPipes(@Path("offset") offset: Int) : Call<mappedWaterPipesBody>

        @POST("tanks/create")
        fun postTanks(@Body tankBody: TankBody) : Call<Message>

        @PUT("tanks/{id}")
        fun putWaterTanks(@Path("id") id:String, @Body tankBody: TankGetBody) : Call<Message>

        @GET("tanks/details/{id}")
        fun searchWaterTanks(@Path("id") id: String) : Call<List<TankGetBody>>

        @POST("valves/create")
        fun postValves(@Body valveBody: ValveBody) : Call<Message>

        @PUT("valves/{id}")
        fun putValves(@Path("id") id:String, @Body valveBody: ValveGetBody) : Call<Message>

        @GET("valves/details/{id}")
        fun searchValves(@Path("id") id: String) : Call<List<ValveGetBody>>

        @POST("manholes/create")
        fun postManHoles(@Body manHoles: ManHoleUpdateBody) : Call<Message>

        @PUT("manholes/{id}")
        fun putManHoles(@Path("id") id:String, @Body manHoles: ManHoleGetBody) : Call<Message>

        @GET("manholes/details/{id}")
        fun searchManHoles(@Path("id") id: String) : Call<List<ManHoleGetBody>>

        @POST("projects/create")
        fun postProject(@Body projectsBody: ProjectsBody) : Call<Message>

        @POST("mastermeters/create")
        fun postMasterMeters(@Body masterMetersBody: MasterMetersBody) : Call<Message>

        @GET("mastermeters/details/{id}")
        fun searchMasterMeters(@Path("id") id: String) : Call<List<MasterMetersGetBody>>

        @PUT("mastermeters/{id}")
        fun putMasterMeters(@Path("id") id:String, @Body masterMetersGetBody: MasterMetersGetBody) : Call<Message>

        @POST("prv/create")
        fun postPRV(@Body prvBody: PRVBody) : Call<Message>

        @PUT("prv/{id}")
        fun putPRV(@Path("id") id:String, @Body prvGetBody: PRVGetBody) : Call<Message>

        @GET("prv/details/{id}")
        fun searchPRV(@Path("id") id: String) : Call<List<PRVGetBody>>

        @POST("projectslines/create")
        fun postProjectlineCoords(@Body projectsLinesBody: ProjectsLinesBody) : Call<Message>

        @PUT("projectslines/{id}")
        fun putProjectLines(@Path("id") id:String, @Body projectsLinesUpdateBody: ProjectsLinesUpdateBody) : Call<Message>

        @PUT("projectslines/{id}")
        fun putProjectsCoordinatesLines(@Path("id") id:String, @Body linedatacoordsbody:LineDataCoordsBody) : Call<Message>

        @PUT("sewerlines/{id}")
        fun putCoordinatesLines(@Path("id") id:String, @Body linedatacoordsbody:LineDataCoordsBody) : Call<Message>

        @POST("sewerlines/create")
        fun postSewerlineCoords(@Body sewerlinesbody: SewerLinesBody) : Call<Message>

        @PUT("sewerlines/{id}")
        fun putSewerLines(@Path("id") id:String, @Body sewerlinedataupdatebody: SewerlineDataUpdateBody) : Call<Message>

        @GET("sewerlines/details/{id}")
        fun searchSewerlines(@Path("id") id: String) : Call<List<SewerlineUpdateGetBody>>

        @PUT("waterpipes/{id}")
        fun putCoordinatesWaterPipes(@Path("id") id:String, @Body linedatacoordsbody:LineDataCoordsBody) : Call<Message>

        @POST("waterpipes/create")
        fun postWaterPipesCoords(@Body waterLinesBody: WaterLinesCoordsBody) : Call<Message>

        @PUT("waterpipes/{id}")
        fun putWaterPipes(@Path("id") id:String, @Body waterpipesDataUpdateBody: WaterpipesDataUpdateBody) : Call<Message>

        @GET("waterpipes/details/{id}")
        fun searchWaterPipes(@Path("id") id: String) : Call<List<WaterpipesDataGetBody>>

}