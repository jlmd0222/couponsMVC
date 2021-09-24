package jlmd.dev.android.offerscuponesmvc.model

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiAdapter {
    val apiKey = "1fd8b8a6c75fc1c278def69cd7c8365b"
    val urlApi = "http://feed.linkmydeals.com/"
    fun getClientService(): ApiService {
        val authInterceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder()
                .addQueryParameter("API_KEY", apiKey)
                .addQueryParameter("format", "json")
                .build()
            val newRequest = chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(newRequest)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(urlApi)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return  retrofit.create(ApiService::class.java)
    }
}