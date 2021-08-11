package com.example.otpverification.otpHandling

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import com.example.otpverification.MainActivity
import com.example.otpverification.R
import com.example.otpverification.db
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status


class otpReceiver : BroadcastReceiver() {
    var message: String =""


    override fun onReceive (context: Context?, intent: Intent?) {
       /* TODO("Not yet implemented")*/
        if(intent?.action == SmsRetriever.SMS_RETRIEVED_ACTION)
        {
            val extras: Bundle? = intent?.extras
        val status: Status? = extras!![SmsRetriever.EXTRA_STATUS] as Status?
        when(status?.statusCode){
            CommonStatusCodes.SUCCESS ->  {
                message =extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                                          Toast.makeText(context,message,Toast.LENGTH_LONG).show();}
            else -> Toast.makeText(context,"Request timeout",Toast.LENGTH_SHORT)
        }
        }
        db.message=this.message
        MainActivity().setotp()
    }


}