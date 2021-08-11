package com.example.otpverification

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import com.example.otpverification.otpHandling.otpListener
import com.example.otpverification.otpHandling.otpReceiver
import com.google.android.gms.auth.api.phone.SmsCodeRetriever
import com.google.android.gms.auth.api.phone.SmsRetriever
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val obj=otpReceiver()

   /* companion object{*/
        public fun setotp() {
            val text = findViewById<EditText>(R.id.otp_main)

            text.text = Editable.Factory.getInstance().newEditable(db.message.substring(db.message.indexOf(':') + 1, db.message.indexOf(':') + 7).trim())
        }
  /*  }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* val obj: helper=helper(applicationContext);
        print(obj.appSignatures.toString())*/

        CoroutineScope(context= Dispatchers.IO + SupervisorJob()).launch {
            val job:Job=background()
            job.join()
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(obj, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(obj)
    }
    private suspend fun background(): Job{

       /* val client =SmsRetriever.getClient(this)
        client.startSmsRetriever()*/
        val job : Job=coroutineScope{
           launch(Dispatchers.IO) { otpListener.otpChecker(this@MainActivity) }
        }
        return job
    }

}