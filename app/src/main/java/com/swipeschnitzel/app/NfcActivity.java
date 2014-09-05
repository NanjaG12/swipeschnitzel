package com.swipeschnitzel.app;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NfcActivity extends Activity {

    private static final String TAG = NfcActivity.class.getName();

    private List<NFCTag> scannedList;
    private List<NFCTag> tagList;

    protected NfcAdapter nfcAdapter;
    protected PendingIntent nfcPendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        tagList = APIHandler.getNFCTags();
        scannedList = new ArrayList<NFCTag>();

        // initialize NFC
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

            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] messages = null;
            if (rawMsgs != null) {

                Log.d(TAG, "Found " + rawMsgs.length + " NDEF messages"); // is almost always just one

                vibrate(); // signal found messages :-)
                messages = new NdefMessage[rawMsgs.length];

                for (int i = 0; i < rawMsgs.length; i++) {
                    messages[i] = (NdefMessage) rawMsgs[i];
                }
            }
            String result="";
            byte[] payload = messages[0].getRecords()[0].getPayload();
            for (int b = 1; b < payload.length; b++) { // skip SOH
                result += (char) payload[b];
            }

            pushNfcTag(result);

        }
        else {
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
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;
        vibe.vibrate(500);
    }

    private void pushNfcTag(String msg){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        msg = msg.substring(2);

        if(!isAlreadyScanned(msg)) {
            Calendar c = Calendar.getInstance();
            NFCTag scannedTag = APIHandler.getNfcTag(msg);
            APIHandler.pushNFCTag(scannedTag.locationName, APIHandler.getCurrentUser().getEmail(), (String) APIHandler.getCurrentUser().get("group"));
            scannedList.add(scannedTag);

            int missingTags = tagList.size() - scannedList.size();

            TextView textViewName = (TextView) findViewById(R.id.textViewLocationName);
            TextView textViewId = (TextView) findViewById(R.id.textViewLocationId);
            TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);

            if(missingTags >0) {
                setScanned(msg);
                alert.setMessage("Yeah, du hast einen Tag gefunden.\n Nur noch " + missingTags + "Tag bis zum Ziel");
                alert.show();

                if (scannedTag != null) {


                    textViewName.setText(scannedTag.locationName + " "+missingTags+"/"+tagList.size());
                    textViewId.setText(scannedTag.id);
                    textViewQuestion.setText(scannedTag.question);
                }
            }
            else{
                setScanned(msg);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Ziel erreicht!");
                textViewName.setText(scannedTag.locationName + " "+tagList.size()+"/"+tagList.size());
                textViewId.setText(scannedTag.id);
                textViewQuestion.setText("Glückwunsch, du hast alle Tags gefunden.\n Du bekommst dein Ergebnis per \nE-Mail");
            }
        }
        else{
            alert.setMessage("Du hast diesen Tag schon gescannt");
            alert.show();
        }

    }


    private Boolean isAlreadyScanned(String id){
        for(NFCTag tag : tagList)
        {
            if(tag.id.equals(id)){
                return tag.scanned;
            }
        }
        return false;
    }

    private void setScanned(String id)
    {
        int counter = 0;
        for(NFCTag tag : tagList)
        {
            if(tag.id.equals(id)){
                break;
            }
            counter ++;
        }
        tagList.get(counter).scanned = true;
    }



}