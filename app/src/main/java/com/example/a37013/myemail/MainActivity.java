package com.example.a37013.myemail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private Button sendmessage;
    private EditText et_number ;
    private EditText et_Content ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_number = findViewById(R.id.et_num);
        et_Content = findViewById(R.id.etContent);

    }

    public void sendmessage(View view) {
        String telnumber = et_number.getText().toString().trim();
        String content = et_Content.getText().toString();
        String[] allphonenumber = telnumber.split(",");
        if(TextUtils.isEmpty(telnumber))
        {
            Toast.makeText(this,"电话号码不能为空",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(content))
        {
            Toast.makeText(this, "短信内容不能为空", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent();
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> contents = smsManager.divideMessage(content);
            for(int i = 0 ; i < allphonenumber.length;i++)
            {
                for(String str:contents)
                {
                    smsManager.sendTextMessage(allphonenumber[i],null,str,null,null);
                }
            }
        }
    }
}
