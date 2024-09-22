package com.example.financedroid.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response { // intercepta a requisição
        val originalRequest = chain.request()       //request original interceptado

        val newRequest = originalRequest.newBuilder()                   // utiliza designer patters builder para identificar e criar um novo
            .addHeader("Authorization", "Bearer $apiKey")  // cria o novo request adicionando o cabeçalho de autorização
            .build() // constroi o novo request
        return chain.proceed(newRequest) // passa o novo request para o chain
    }

}