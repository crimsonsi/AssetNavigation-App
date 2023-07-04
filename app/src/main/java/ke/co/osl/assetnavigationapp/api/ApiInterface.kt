package ke.co.osl.assetnavigationapp.api

import ke.co.osl.assetnavigationapp.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

        companion object {

         var BASE_URL = "http://102.215.32.186/api/"

            fun create() : ApiInterface {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                return retrofit.create(ApiInterface::class.java)
            }
        }


        @POST("mobile/meterreaderlogin")
        fun loginMeterReader(@Body loginBody: LoginBody) : Call<Message>

    @GET("customers/details/{account}")
    fun searchCustomerDetails(@Path("account") accountno: String) : Call<List<CustomerDetailsBody>>

    @GET("customers/{accountno}")
    fun searchCustomerMeters(@Path("accountno") accountno: String) : Call<List<CustomerDetailsBody>>


    @POST("mobile/forgot")
        fun recoverPassword(@Body recoverPasswordBody: RecoverPasswordBody) : Call<Message>

        @PUT("mobile/{id}")
        fun changePassword(@Path("id") id: String, @Body changePasswordBody: ChangePasswordBody) : Call<Message>

}