package com.ms0ng.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.regex.Pattern;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;

import static android.support.v4.content.ContextCompat.startActivity;


public class MainReceiver extends BroadcastReceiver {
    Bundle bundle;
    String regID;
    String message_Title;
    String message_Message;
    String message_Extra;
    String message_ID;
    String url;
    boolean connect;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //bundle=intent.getExtras();
        //connect=bundle.getBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE);
        //regID=bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
        //message_ID=bundle.getString();
        //message_Message=bundle.getString(JPushInterface.EXTRA_ALERT);
        //Pattern pattern=Pattern.compile("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");

}
}
