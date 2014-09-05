package com.swipeschnitzel.app;

import com.parse.ParseObject;

/**
 * Created by andreaspfeiffer on 03/06/14.
 */
public class NFCTag {

    public String locationName;
    public String id;
    public String nextLocationId;
    public String question;
    public Boolean scanned;


    public NFCTag (ParseObject nfcTagParsObject){
        this.locationName = (String)nfcTagParsObject.get("locationName");
        this.id = nfcTagParsObject.getObjectId();
        this.nextLocationId = (String)nfcTagParsObject.get("nextLocationId");
        this.question = (String)nfcTagParsObject.get("question");
        this.scanned = false;
    }


    public String getFormatedString(){
        return "location name: "+locationName+"\nnext location: "+nextLocationId+"\nquestion: "+question;
    }




}


