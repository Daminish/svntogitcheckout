package com.capco.technologies.living.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.capco.technologies.living.R;
import com.capco.technologies.living.presentation.budgeting.step1.EstimateBudgetStep1Fragment;

public class EstimateBudget extends AppCompatActivity {

    FragmentTransaction ft = null;

    enum View {DEFAULT, VIEW1, VIEW2, VIEW3}

    ;
    View view = View.DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_budget);

        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setSubtitle(R.string.subtitle_activity_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Begin the transaction
        ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(R.id.fragment_placeholder, new EstimateBudgetStep1Fragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_estimate_budget, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_next) {
//            View currentView = view;
//
//            ft = getSupportFragmentManager().beginTransaction();
//            if (currentView == View.DEFAULT) {
//                ft.replace(R.id.fragment_placeholder, new EstimateBudgetStep2Fragment());
//                ft.commit();
//                view = View.VIEW2;
//            } else if (currentView == View.VIEW2) {
//                ft.replace(R.id.fragment_placeholder, new EstimateBudgetStep3Fragment());
//                ft.commit();
//                view = View.VIEW3;
//            }
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
//        View currentView = view;
//        ft = getSupportFragmentManager().beginTransaction();
//        if (currentView == View.VIEW3) {
//            ft.replace(R.id.fragment_placeholder, new EstimateBudgetStep2Fragment());
//            ft.commit();
//            view = View.VIEW2;
//        } else if (currentView == View.VIEW2) {
//            ft.replace(R.id.fragment_placeholder, new EstimateBudgetStep1Fragment());
//            ft.commit();
//            view = View.VIEW1;
//        } else if (currentView == View.VIEW1) {
//            finish();
//            view = View.DEFAULT;
//        }
//        else{
//            finish();
//        }

        return super.onSupportNavigateUp();
    }


}
