package com.swipeschnitzel.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * Created by Nanja on 11.05.2014.
 */
public class PlaceholderFragment extends Fragment{

    private static final String KEY_CONTENT = "PlaceholderFragment:Content";

    public static PlaceholderFragment newInstance(String content) {
        PlaceholderFragment phf = new PlaceholderFragment();

        StringBuilder builder = new StringBuilder();
        builder.append(content).append(" ");
        builder.deleteCharAt(builder.length() - 1);
        phf.mContent = builder.toString();

        return phf;
    }

    private String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER_HORIZONTAL);
        text.setText(mContent);
        text.setTextSize(24);
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                rl.setMargins(50,50,0,0);
        text.setPadding(10,10,10,10);

        //ImageView image = new ImageView(getActivity());

//        View line = new View(getActivity());
//        RelativeLayout.LayoutParams rl_line=new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//            rl_line.setMargins(0,0,100,0);
//        rl_line.height= 10;
//        line.setBackgroundColor(getResources().getColor(R.color.redline));

        RelativeLayout layout = new RelativeLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.addView(text);
        //layout.addView(image);
        //layout.addView(line);

        return layout;
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if(v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l,t,r,b);
            v.requestLayout();
        }
    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
}
