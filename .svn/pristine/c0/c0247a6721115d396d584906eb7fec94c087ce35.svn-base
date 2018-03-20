package com.capco.technologies.living.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.adapters.FindHomeAdapter;
import com.capco.technologies.living.core.BaseActivity;
import com.capco.technologies.living.datamodel.FindHomeLandingPageData;

import java.util.ArrayList;
import java.util.List;

public class FindHomeLandingPage extends AppCompatActivity {


    private RecyclerView find_home_landing_recycler;
    private List<FindHomeLandingPageData> findHomeLandingitems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_home_landing_page);

        find_home_landing_recycler=(RecyclerView)findViewById(R.id.find_home_landing_recycler);

        //temporary basis
        FindHomeLandingPageData findHomeLandingPageData=new FindHomeLandingPageData();
        FindHomeLandingPageData findHomeLandingPageData1=new FindHomeLandingPageData();

        findHomeLandingPageData.setItemName("Area Guide");
        findHomeLandingPageData.setItemImg("ACB");

        findHomeLandingPageData1.setItemName("Finding Your Property");
        findHomeLandingPageData1.setItemImg("ACB");

        findHomeLandingitems.add(findHomeLandingPageData);
        findHomeLandingitems.add(findHomeLandingPageData1);

        //Adapter
        LinearLayoutManager llm = new LinearLayoutManager(this);
        find_home_landing_recycler.setLayoutManager(llm);
        find_home_landing_recycler.setAdapter(new FindHomeAdapter(this,findHomeLandingitems));

    }
}
