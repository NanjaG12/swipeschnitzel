package com.swipeschnitzel.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.parse.*;


public class MainActivity extends Activity implements OnClickListener{

    private EditText emailadress=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //init parse
        Parse.initialize(this, "YyR2OejscWJcCFaadJH0igburHfokx3WRBetikym", "7zEOssXjI3OUIKDcJKDiqdD5LkEzbIqmTZDBZHqp");

        APIHandler.getNFCTags();

        if(APIHandler.getCurrentUser() !=null){
            //User already signed up
            Intent i= new Intent(this, CreateGroup.class);
            startActivity(i);
        }
        else{
            //new user or logged out user--> show login view
            setContentView(R.layout.activity_main);
            View anmelden = findViewById(R.id.btnAnmelden);
            anmelden.setOnClickListener(this);

            //change Text if User exists.
        }
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAnmelden:
                EditText mail = (EditText)findViewById(R.id.email1);
                EditText groupName = (EditText)findViewById(R.id.groupName);
                String mailText = mail.getText().toString();
                String groupNameText = groupName.getText().toString();
                if(!mailText.isEmpty() && !groupNameText.isEmpty()){
                    APIHandler.generateUser(mailText, groupNameText);
                    Intent i= new Intent(this, CreateGroup.class);
                    startActivity(i);
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setMessage("bitte alle Textfelder ausfüllen");
                    alert.show();

                }
                break;
            //further icons
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
