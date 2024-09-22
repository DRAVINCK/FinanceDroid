package com.example.financedroid.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    /* TODO: NAO UTILIZAR A API KEY AQUI EM UM PROJETO REAL, JOGA PARA O BACK END*/
    private const val API_KEY = ""
    private const val BASE_URL = "https://api.openai.com/"

    fun provideAiService(): AiService { // cria a instancia do retrofit
        return Retrofit.Builder()
            .baseUrl(BASE_URL)// define a url base
            .client(provideOkHttpClient(provideApiKeyInterceptor()))// define o cliente de http
            .addConverterFactory(GsonConverterFactory.create()) // converte JSON para Objeto
            .build() // constroi o retrofit
            .create(AiService::class.java) // vai criar o objeto de acordo com a interface definida
    }

    private fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(API_KEY)
    }

    private fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}