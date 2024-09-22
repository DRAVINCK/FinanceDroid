package com.example.financedroid.network

data class OpenAIPrompt(
    val prompt: String,
    val model: String = "gpt-3.5-turbo",
    val maxTokens: Int = 100,
    val n: Int = 1,
    val temperature: Double = 0.7

)
