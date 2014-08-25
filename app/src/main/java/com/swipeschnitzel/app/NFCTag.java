package com.swipeschnitzel.app;

/**
 * Created by andreaspfeiffer on 03/06/14.
 */
public class NFCTag {

    public long id;
    public String locationName;
    public long nextLocationId;

    public NFCTag(long id, String locationName, long nextLocationId)
    {
        this.id = id;
        this.locationName = locationName;
        this.nextLocationId = nextLocationId;
    }



    public static void scanTag(){

    };



}
