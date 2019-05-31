package com.ms0ng.jpush;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        Switch sw=findViewById(R.id.switch1);
        Switch debug=findViewById(R.id.switch2);
        EditText editText=findViewById(R.id.keyEditText);
        Button changeKeyButton=findViewById(R.id.changeAppKeyButton);
        Button resetButton=findViewById(R.id.resetKeyButton);
        Button tutorialButton = findViewById(R.id.tutorialButton);

        FloatingActionButton fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);


        JPushInterface.init(getApplicationContext());

        fab.setOnClickListener(this);
        sw.setOnClickListener(this);
        debug.setOnClickListener(this);
        changeKeyButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        tutorialButton.setOnClickListener(this);

        sw.setChecked(!JPushInterface.isPushStopped(getApplicationContext()));
        sw.setText(JPushInterface.isPushStopped(getApplicationContext())?"推送服务已关闭":"推送服务已打开");
        sharedPreferences=getSharedPreferences("AppKey",MODE_PRIVATE);
        editText.setText(sharedPreferences.getString("key","(默认)"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Switch sw;
        EditText editText;
        switch (v.getId()){
            case R.id.fab:
                EditText titleT=findViewById(R.id.msgTitleText);
                EditText msgT=findViewById(R.id.msgEditText);
                String title=titleT.getText().toString();
                String msg=msgT.getText().toString();
                JPushLocalNotification ln = new JPushLocalNotification();
                ln.setBuilderId(0);
                ln.setContent(msg.equalsIgnoreCase("")?"这是一条本地推送":msg);
                ln.setTitle(title.equalsIgnoreCase("")?"无标题":title);
                ln.setNotificationId(11111111) ;
                ln.setBroadcastTime(System.currentTimeMillis() + 500);
                JPushInterface.addLocalNotification(getApplicationContext(), ln);
                Snackbar.make(v,"本地推送已发出", Snackbar.LENGTH_SHORT)
                    .setAction("我知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(MainActivity.this,"你点了",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
                break;
            case R.id.switch1:
                sw=findViewById(v.getId());
                if(sw.isChecked()){
                    sw.setText("推送服务已打开");
                    JPushInterface.resumePush(getApplicationContext());
                }else {
                    sw.setText("推送服务已关闭");
                    JPushInterface.stopPush(getApplicationContext());
                }
                break;
            case R.id.switch2:
                sw=findViewById(v.getId());
                if(sw.isChecked())JPushInterface.setDebugMode(true);
                else JPushInterface.setDebugMode(false);
                break;
            case R.id.tutorialButton:
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(""));
                break;
            case R.id.resetKeyButton:
                editText=(EditText)findViewById(R.id.keyEditText);
                editText.setText("040614d47c2bc2a59edba9e9");
                sharedPreferencesEditor.putString("key","040614d47c2bc2a59edba9e9");
                break;
            case R.id.changeAppKeyButton:
                editText=(EditText)findViewById(R.id.keyEditText);
                sharedPreferencesEditor.putString("key",editText.getText().toString());
                break;
        }
    }
}
