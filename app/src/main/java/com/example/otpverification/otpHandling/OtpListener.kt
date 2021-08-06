package com.example.otpverification.otpHandling


import android.app.Activity
import android.widget.Toast

import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.auth.api.phone.SmsRetrieverClient
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener

class otpListener {


    companion object {
        suspend fun otpChecker(activity: Activity) {
            var client: SmsRetrieverClient = SmsRetriever.getClient(activity)
            var task = client.startSmsRetriever()
            //val checker : Boolean = task.isComplete;
            task.addOnSuccessListener(activity, OnSuccessListener {

            })
            task.addOnFailureListener(activity, OnFailureListener {
                Toast.makeText(
                    activity.applicationContext,
                    "Unable to retrieve OTP",
                    Toast.LENGTH_SHORT
                )
                    .show()
            })
        }
    }

}