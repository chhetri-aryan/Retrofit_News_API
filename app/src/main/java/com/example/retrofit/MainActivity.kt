package com.example.retrofit

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private val API_KEY = "b011e4c789b7a85d50e0e5e159b4a095"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

        fetchNews()

    }

    private fun fetchNews() {
        RetrofitClient.instance.getTopHeadlines(API_KEY).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val sb: StringBuilder = StringBuilder()
                if (response.isSuccessful) {
                    response.body()?.data?.forEach {
                        Log.d(
                            TAG,
                            "Title: ${it.title}" + "\nAuthor: ${it.author}" + "\nDescription: ${it.description}" + "\nSource: ${it.source}" + "\nPublished At: ${it.published_at}" + "\nURL: ${it.url}\n"
                        )

                        sb.append(
                            "Title: ${it.title}" + "\nAuthor: ${it.author}" + "\nDescription: ${it.description}" + "\nSource: ${it.source}" + "\nPublished At: ${it.published_at}" + "\nURL: ${it.url}\n\n\n"
                        )
                    }

                    findViewById<TextView>(R.id.id).text = sb.toString()
                }
            }

            override fun onFailure(call: retrofit2.Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}