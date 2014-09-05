package com.swipeschnitzel.app;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
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
                user.put("group",groupName);
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

    public static void pushNFCTag(String nfc_Name, String user, String group)
    {
        try {
            ParseObject nfcTag = new ParseObject("nfcStamp");
            nfcTag.put("user", user);
            nfcTag.put("group", group);
            nfcTag.put("TagName", nfc_Name);
            nfcTag.saveInBackground();
        }
        catch(Exception exp)
        {
            System.out.print(exp.getMessage());
        }
    }

    public static List<NFCTag> getNFCTags()
    {
        List<NFCTag> nfcTags = new ArrayList<NFCTag>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("NFC");
        try
        {
            List<ParseObject> nfcTagsParse = query.find();

            for(ParseObject obj : nfcTagsParse)
            {
                nfcTags.add(new NFCTag(obj));
            }

        }
        catch (ParseException exp)
        {

        }
        return nfcTags;
    }


    public static NFCTag getNfcTag(String id){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("NFC");
        try
        {
            ParseObject nfcTag = query.get(id);
            NFCTag tag = new NFCTag(nfcTag);
            return tag;
        }
        catch (ParseException exp)
        {
            System.out.print("not able to find tag");
            return null;
        }


    }


}
