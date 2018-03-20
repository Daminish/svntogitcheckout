package com.capco.technologies.living.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.capco.technologies.living.R;

/**
 * Created by test on 19/12/17.
 */

public class BaseActivity extends AppCompatActivity {

    private String whichActivity;
    private boolean useToolbar=false;
    private TextView heading_text_view;
    private String whichStmt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {

        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout childActivities = (FrameLayout) fullView.findViewById(R.id.child_activity_container);
        getLayoutInflater().inflate(layoutResID, childActivities, true);

        super.setContentView(fullView);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(useToolbar){

            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            if(whichActivity!=null && !whichActivity.isEmpty())
                setTitle(Html.fromHtml("<font color='#ffffff'>"+whichActivity+"</font>"));

            if(whichStmt!=null && !whichStmt.isEmpty()){
                ((TextView)findViewById(R.id.heading_text_view)).setText(whichStmt);
            }else{
                ((TextView)findViewById(R.id.heading_text_view)).setVisibility(View.GONE);
            }

        }else{

            toolbar.setVisibility(View.GONE);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.living_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:

                return true;

            case R.id.item2:
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void enableToolbar(String whichActivity,String whichStmt) {
        this.whichActivity = whichActivity;
        this.whichStmt=whichStmt;
        useToolbar=true;
    }

}
