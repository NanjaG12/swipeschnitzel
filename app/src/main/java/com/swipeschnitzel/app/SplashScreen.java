package com.swipeschnitzel.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Vibrator;
import android.util.Log;

/**
 * Created by Nanja on 12.04.2014.
 */
public class SplashScreen extends Activity {

    private static final String TAG = SplashScreen.class.getName();

    protected NfcAdapter nfcAdapter;
    protected PendingIntent nfcPendingIntent;
    //Splash Screen Timer
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }



    public void enableForegroundMode() {
        Log.d(TAG, "enableForegroundMode");

        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED); // filter for all
        IntentFilter[] writeTagFilters = new IntentFilter[] {tagDetected};
        nfcAdapter.enableForegroundDispatch(this, nfcPendingIntent, writeTagFilters, null);
    }

    public void disableForegroundMode() {
        Log.d(TAG, "disableForegroundMode");

        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent");

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            //TextView textView = (TextView) findViewById(R.id.title);

            //textView.setText("Hello NFC!");

            Parcelable[] messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (messages != null) {

                Log.d(TAG, "Found " + messages.length + " NDEF messages"); // is almost always just one

                vibrate(); // signal found messages :-)


                /*
                // parse to records
                for (int i = 0; i < messages.length; i++) {
                    try {
                        List<Record> records = new Message((NdefMessage)messages[i]);

                        Log.d(TAG, "Found " + records.size() + " records in message " + i);

                        for(int k = 0; k < records.size(); k++) {
                            Log.d(TAG, " Record #" + k + " is of class " + records.get(k).getClass().getSimpleName());

                            Record record = records.get(k);

                            if(record instanceof AndroidApplicationRecord) {
                                AndroidApplicationRecord aar = (AndroidApplicationRecord)record;
                                Log.d(TAG, "Package is " + aar.getDomain() + " " + aar.getType());
                            }

                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Problem parsing message", e);
                    }

                }

                */
            }
        } else {
            // ignore
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");

        super.onResume();

        enableForegroundMode();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");

        super.onPause();

        disableForegroundMode();
    }

    private void vibrate() {
        Log.d(TAG, "vibrate");

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
        vibe.vibrate(500);
    }
}
