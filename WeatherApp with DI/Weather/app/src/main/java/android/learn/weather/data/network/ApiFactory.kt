package android.learn.weather.data.network

import android.learn.weather.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "http://api.weatherapi.com/v1/"
    private const val KEY = "key"

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        val newUrl = request.url()
            .newBuilder()
            .addQueryParameter(KEY, BuildConfig.API_KEY)
            .build()
        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }.build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}