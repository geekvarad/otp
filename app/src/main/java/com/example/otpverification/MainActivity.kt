package com.example.otpverification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import com.example.otpverification.otpHandling.otpListener
import com.example.otpverification.otpHandling.otpReceiver
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var job :Job
        runBlocking {
            job=background()

        }
        if(job.isCompleted)
        {
            setotp()
        }

    }
    private suspend fun background(): Job{
        val job : Job
        coroutineScope{
           job= launch(Dispatchers.IO) { com.example.otpverification.otpHandling.otpListener.Companion.otpChecker(this@MainActivity) }
        }
        return job
    }
    private fun setotp(){
        val text=findViewById(R.id.otp_main) as EditText

        text.text = db.message.substring(db.message.indexOf(':')+1,db.message.indexOf(':')+7).trim() as Editable
    }
}