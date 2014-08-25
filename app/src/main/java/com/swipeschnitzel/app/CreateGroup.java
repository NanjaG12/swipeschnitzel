package com.swipeschnitzel.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Nanja on 14.04.2014.
 */
public class CreateGroup extends Activity implements OnClickListener {
    final Context context = this;
    final Context ctx = this;
    private ImageButton ibtn_addgroup;
    private ImageButton ibtn_addmember;
    private Button btn_next;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_group);

        ibtn_addgroup = (ImageButton) findViewById(R.id.addgroup_btn);
        ibtn_addgroup.setOnClickListener( this);

        ibtn_addmember = (ImageButton) findViewById(R.id.addmember_btn);
        ibtn_addmember.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addgroup_btn:{
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.addgroup);
                dialog.setTitle(R.string.groupname);

                EditText editText = (EditText) dialog.findViewById(R.id.addgroup_scr);
                ImageButton dialogIBtn = (ImageButton)dialog.findViewById(R.id.dialogBtnAddGroup);

                dialogIBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            break;

            case R.id.addmember_btn:{
                final Dialog dialog = new Dialog(ctx);
                dialog.setContentView(R.layout.addmember);
                dialog.setTitle(R.string.addmember);

                EditText editText = (EditText) dialog.findViewById(R.id.addmember_scr);
                ImageButton dialogIBtn = (ImageButton)dialog.findViewById(R.id.dialogBtnAddMember);

                dialogIBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            break;
            case R.id.nextbutton:{
                NFCTag.scanTag();
                /*
                Intent i= new Intent(this, SwipeGoals.class);
                startActivity(i);*/
            }break;
        }
    }

}
