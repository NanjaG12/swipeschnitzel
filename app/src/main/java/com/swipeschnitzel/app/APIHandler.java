package com.swipeschnitzel.app;

import com.parse.*;

import java.util.Date;
import java.util.List;

/**
 * Created by andreaspfeiffer on 02/06/14.
 */
public final class APIHandler {

    public static ParseUser getCurrentUser()
    {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            return currentUser;
        } else {
            return null;
        }
    }

    public static void logoutCurrentUser()
    {
        ParseUser.logOut();
    }

    public static ParseUser generateUser(String mail, String groupName)
    {
        if(!mail.isEmpty()) {
            ParseUser user = new ParseUser();
            user.setUsername(mail);
            user.setPassword("12345");
            user.setEmail(mail);
            if(!groupName.isEmpty()){
                //set user groupName
            }

            signUp(user);
            return user;
        }
        return null;
    }

    public static void signUp(ParseUser user){
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    System.out.print("Sign up succeed");
                } else {
                    System.out.print("Sign up didn't succeed");
                }
            }
        });
    }

    public static void pushNFCTag(String nfc_id, Date timestamp, String user)
    {
        ParseObject nfcTag = new ParseObject("nfcStamp");
        nfcTag.put("user", user);
        nfcTag.put("id", nfc_id);
        nfcTag.put("timestamp", timestamp);
        nfcTag.saveInBackground();
    }

    public static List<NFCTag> getNFCTags()
    {
        List<NFCTag> nfcTags = null;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("NFC");
        try
        {
            List<ParseObject> nfcTagsParse = query.find();

            for(ParseObject obj : nfcTagsParse)
            {

            }

        }
        catch (ParseException exp)
        {

        }
        return nfcTags;
    }


    public static NFCTag getNfcTag(String id){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        try
        {
            ParseObject nfcTag = query.get(id);
            return null;
        }
        catch (ParseException exp)
        {
            System.out.print("not able to find tag");
            return null;
        }


    }


    public static void createGroup(String groupName)
    {

    }

    public static void addUserToGroup(String groupName, String userName)
    {

    }


}
