package com.example.conduit.api

import com.example.conduit.api.services.ConduitAPI
import com.example.conduit.api.services.ConduitAuthAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit



    /*var authToken:String?=null

    private val retrofit=Retrofit.Builder()
            .baseUrl("https://conduit.productionready.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    private val retrofitAuthCall = Retrofit.Builder()
        .baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())

            .client(OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).connectTimeout(2, TimeUnit.SECONDS).addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${authToken}").build()
                chain.proceed(request)
            }.build())

        .build()

         object api{
        val retrofitService: ConduitAPI by lazy {
            retrofit.create(
                ConduitAPI::class.java
            )
        }
    }

    object Authapi{
        val retrofitService: ConduitAuthAPI by lazy {
            retrofitAuthCall.create(
                ConduitAuthAPI::class.java
            )
        }
    }

*/

//https://medium.com/android-news/token-authorization-with-retrofit-android-oauth-2-0-747995c79720
//https://stackoverflow.com/questions/41078866/retrofit2-authorization-global-interceptor-for-access-token
    object ConduitClient {
    var authToken: String? = null

    private val authInterceptor = Interceptor { chain ->
        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                    .header("Authorization", "Token $it")
                    .build()
        }
        chain.proceed(req)
    }

    val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(2, TimeUnit.SECONDS)

    val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://conduit.productionready.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())

    val api = retrofitBuilder
            .client(okHttpBuilder.build())
            .build()
            .create(ConduitAPI::class.java)

    val Authapi = retrofitBuilder
            .client(okHttpBuilder.addInterceptor(authInterceptor).build())
            .build()
            .create(ConduitAuthAPI::class.java)



    //val api=retrofit.create(ConduitAPI::class.java)
}