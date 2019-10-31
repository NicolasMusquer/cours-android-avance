package com.example.kotlincourse

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class GamesService {
    companion object {
        fun getGames(success: (gameList: List<Game>) -> Unit, failure: () -> Unit) {

            val request = Request.Builder().url("https://my-json-server.typicode.com/bgdom/cours-android/games").build()
            OkHttpClient().newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    when (response.code) {
                        200 -> {
                            success(Gson().fromJson(response.body?.string(), Array<Game>::class.java).toList())
                        }
                        else -> failure()
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    failure()
                }
            })
        }
    }
}