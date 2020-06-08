package com.smartbtsimple.sampleapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.huawei.hms.common.api.CommonStatusCodes;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.sms.common.ReadSmsConstant;

public class MySMSBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ReadSmsConstant.READ_SMS_BROADCAST_ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Status status = bundle.getParcelable(ReadSmsConstant.EXTRA_STATUS);
                if (status.getStatusCode() == CommonStatusCodes.TIMEOUT) {
                    Toast.makeText(context, "Timeout", Toast.LENGTH_SHORT).show();
                    // Service has timed out and no SMS message that meets the requirement is read. Service ended.
//                doSomethingWhenTimeOut();
                } else if (status.getStatusCode() == CommonStatusCodes.SUCCESS) {
                    if (bundle.containsKey(ReadSmsConstant.EXTRA_SMS_MESSAGE)) {



                        // An SMS message that meets the requirement is read. Service ended.
//                    doSomethingWhenGetMessage(bundle.getString(ReadSmsConstant.EXTRA_SMS_MESSAGE));

                        String message = (String) bundle.get(ReadSmsConstant.EXTRA_SMS_MESSAGE);
                        String otp = message.replace("<#> Your OTP is: ", "");
                        com.smartbtsimple.logger.Log.d("otp ",  otp.substring(0,6));

                    }
                }
            }
        }else {
            Log.d("messagintentdifferent  " ,  intent.getAction()+"");
        }
    }



}