package com.capco.technologies.living.presentation.findhome;

/**
 * Created by Shreyas Bhondve on 1/8/2018.
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.capco.technologies.living.domain.model.AreaDetailMenu;
import com.capco.technologies.living.domain.model.location.ICity;
import com.capco.technologies.living.domain.model.location.ILocality;
import com.capco.technologies.living.domain.model.location.ILocation;
import com.capco.technologies.living.presentation.base.BaseActivity;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;
import com.capco.technologies.living.storage.MockData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.capco.technologies.living.R;
import java.util.ArrayList;
import java.util.List;

public class AreaGuideActivity extends BaseActivity implements OnMapReadyCallback, AreaGuideContract.View, OnRecyclerViewItemClickListener<AreaDetailMenu>, LocationListener {

    private GoogleMap mMap;

    private AreaGuideContract.Presenter mPresenter;

    BottomSheetDialog dialog;
    ImageView iv_trigger;
    CoordinatorLayout coordinatorLayout;

    LocationAdapter<ICity> adapterCity;
    LocationAdapter<ILocality> adapterLocality;

    LinearLayout areadetailView;
    AreaDetailsAdapter areaDetailsAdapter;
    ActionBar actionBar;

    private RecyclerView areaDetailRecyclerView;


    public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;
    public static final int REQUEST_ID_FOR_GPS = 200;
    public static final int REQUEST_ID_FOR_LOCATION_PERMISSION =300;
    public static String MESSAGE_1="GPS is disabled in your device. Would you like to enable it?";
    private String MESSAGE_2="GPS Permission is not provide. Would you like to enable it?";
    private String  PackageName = "com.capco.technologies.living";


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_area_guide);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        areaDetailRecyclerView = (RecyclerView)findViewById(R.id.list);
        areaDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initializeData();
        new AreaGuidePresenter(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinator);
        init_persistent_bottomsheet();
    }

    private boolean checkGPSSettings() {
        if (isGPSEnabled()) {
            checkPermissionAvialable();
            return true;
        } else {
            showDisabledAlert(MESSAGE_1);
            return false;
        }
    }

    @Override
    public void showAreaFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void OnClickCurrentGPS(View view) {
        checkGPSSettings();
    }


    private void initializeData() {

        areaDetailsAdapter = new AreaDetailsAdapter(MockData.getInstance().getAreaDetailMenus(), this);
        areaDetailRecyclerView.setAdapter(areaDetailsAdapter);

        adapterCity = new LocationAdapter<>(new ArrayList<>());
        adapterCity.setOnItemClickListener(onCityItemClickListener);
        adapterLocality = new LocationAdapter<>(new ArrayList<>());
        adapterLocality.setOnItemClickListener(onLocalityItemClickListener);


        //TODO
        // change adapter to the recyclerView based on focus on edittext of search locality
        //        recyclerView.setAdapter(areaDetailsAdapter);

        getCityFieldView().addTextChangedListener(textWatcherCity);
        getCityFieldView().setOnFocusChangeListener((view, b) -> {
            //TODO manupulate view as per requirements
            getCityLocalityRecycler().setAdapter(adapterCity);

        });
        getLocalityFieldView().addTextChangedListener(textWatcherLocality);
        getLocalityFieldView().setOnFocusChangeListener((view, b) -> {
            //TODO manupulate view as per requirements
            //getCityLocalityRecycler().setAdapter(adapterLocality);
        });
    }


    private TextWatcher textWatcherCity = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mPresenter.startCitySearch(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private TextWatcher textWatcherLocality = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mPresenter.startLocalitySearch(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public void showCityField(boolean isShow) {
        getCityFieldView().setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLocalityField(boolean isShow) {
        getLocalityFieldView().setVisibility(isShow ? View.VISIBLE : View.GONE);
    }


    @Override
    public void showDefaultLocations(boolean isShow) {
        findViewById(R.id.container_default_location).setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showFilteredLocations(boolean isShow) {
        findViewById(R.id.cityLocality_recycler).setVisibility(isShow ? View.VISIBLE : View.GONE);
    }



    @Override
    public EditText getCityFieldView() {
        return findViewById(R.id.et_city);
    }

    @Override
    public EditText getLocalityFieldView() {
        return findViewById(R.id.et_locality);
    }

    @Override
    public RecyclerView getCityLocalityRecycler() {
        return findViewById(R.id.cityLocality_recycler);
    }

    @Override
    public void updateLocationDetails() {
        //TODO Need to get location details show in bottom sheet
    }

    private OnRecyclerViewItemClickListener<ICity> onCityItemClickListener = new OnRecyclerViewItemClickListener<ICity>() {
        @Override
        public void onItemClick(View v, ICity clickedItem,int position) {

            getCityFieldView().setText(clickedItem.getName());
            getLocalityFieldView().requestFocus();
            getCityLocalityRecycler().setAdapter(adapterLocality);
        }
    };

    private OnRecyclerViewItemClickListener<ILocality> onLocalityItemClickListener = new OnRecyclerViewItemClickListener<ILocality>() {
        @Override
        public void onItemClick(View v, ILocality clickedItem,int position) {

            getLocalityFieldView().setText(clickedItem.getName());
            Toast.makeText(AreaGuideActivity.this, "Details of this locality will be fetch", Toast.LENGTH_SHORT).show();
        }
    };

    public void init_persistent_bottomsheet() {
        View persistentbottomSheet = coordinatorLayout.findViewById(R.id.bottomsheet);
        iv_trigger = (ImageView) persistentbottomSheet.findViewById(R.id.slideImg);
        areadetailView = (LinearLayout) persistentbottomSheet.findViewById(R.id.areaDetailsView);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(persistentbottomSheet);


        areadetailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    iv_trigger.setImageResource(R.drawable.slide_down);
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    iv_trigger.setImageResource(R.drawable.slide_up);
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        if (behavior != null)
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    //showing the different states
                    switch (newState) {
                        case BottomSheetBehavior.STATE_HIDDEN:
                            break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            break;
                        case BottomSheetBehavior.STATE_DRAGGING:
                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            break;
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    // React to dragging events

                }
            });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (!checkPermissionAvialable()) {
            findLocation();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void setPresenter(AreaGuideContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mPresenter.start();
    }

    @Override
    public void setScreenTitle(String title) {

    }

    @Override
    public void setPageBanner(String bannerString) {
        setBannerText(bannerString);
    }

    @Override
    public String getBannerText() {
        return "";
    }

    @Override
    public void showAreaDetails() {

    }

    @Override
    public void hideAreaDetails() {

    }

    @Override



    public void showCityCurrentLocationIcon(boolean isShow) {
        findViewById(R.id.img_city_location_gps).setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showCityListData(List<ICity> filteredCity) {
        // MockData.getCity();
         adapterCity.setLocation(filteredCity);
    }


    @Override
    public void showLocalityListData(List<ILocality> filteredLocality) {
        // MockData.getLocality();
        // locality adapter
        adapterLocality.setLocation(filteredLocality);
    }

    @Override
    public void onItemClick(View v, AreaDetailMenu clickedItem,int position) {

        ViewDialog alert = null;

        switch (position) {
            case 0:
                alert = new ViewDialog(this, "Flat/Apartment", "6,00,000 - 10,00,000", "Average property price range per borough.", "Source: Land registry (As of 2018)");
                break;
            case 1:
                alert = new ViewDialog(this, "House/Villa", "6,00,000 - 10,00,000", "Average property price range per borough.", "Source: Land registry (As of 2018)");

                break;
            case 2:
                alert = new ViewDialog(this, "Village House", "6,00,000 - 10,00,000", "Average property price range per borough.", "Source: Land registry (As of 2018)");

                break;
            case 3:
                alert = new ViewDialog(this, "Crime Rating", "6,00,000 - 10,00,000", "Average property price range per borough.", "Source: Land registry (As of 2018)");

                break;
            case 4:
                alert = new ViewDialog(this, "School rating", "88%", "Average school rating per borough based on % of pupils achievement 5+, A+, -C grades at GCSE level.", "Source: Department of Education (as of 2018)");

                break;
            case 5:
                alert = new ViewDialog(this, "Broadband", "6,00,000 - 10,00,000", "Average property price range per borough.", "Source: Land registry (As of 2018)");

                break;
            case 6:
                alert = new ViewDialog(this, "Population", "6,00,000 - 10,00,000", "Average property price range per borough.", "Source: Land registry (As of 2018)");

                break;

        }
        alert.showInfo();
    }

    class ViewDialog {
        String title;
        String measure;
        String description;
        String source;
        Context mContext;

        public ViewDialog(Context mContext, String title, String measure, String description, String source) {
            this.title = title;
            this.measure = measure;
            this.description = description;
            this.source = source;
            this.mContext = mContext;
        }

        public void showInfo() {
            final Dialog dialog = new Dialog(mContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.area_info_dialog);

            TextView text1 = (TextView) dialog.findViewById(R.id.title);
            TextView text2 = (TextView) dialog.findViewById(R.id.measure);
            TextView text3 = (TextView) dialog.findViewById(R.id.description);
            TextView text4 = (TextView) dialog.findViewById(R.id.source);
            text1.setText(title);
            text2.setText(measure);
            text3.setText(description);
            text4.setText(source);

            TextView dialogButton = (TextView) dialog.findViewById(R.id.okay);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_ID_ACCESS_COURSE_FINE_LOCATION: {

                // Note: If request is cancelled, the result arrays are empty.
                // Permissions granted (read/write).
                if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED&&
                        grantResults[1]==PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();

                    // Show current location on Map.
                    this.findLocation();
                }
                // Cancelled or denied.
                else {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                        checkPermissionAvialable();
                    }else{
                        showDisabledAlert(MESSAGE_2);
                    }
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ID_FOR_GPS) {
            findLocation();
        }else if(requestCode==REQUEST_ID_FOR_LOCATION_PERMISSION){
           checkGPSSettings();
        }
    }

    //to check is GPS is enabled
    public boolean isGPSEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private boolean checkPermissionAvialable()
    {

            int accessCoarsePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            int accessFinePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            if (accessCoarsePermission != PackageManager.PERMISSION_GRANTED || accessFinePermission != PackageManager.PERMISSION_GRANTED) {
                // The Permissions to ask user.
                String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION, };

                // Show a dialog asking the user to allow the above permissions.
                ActivityCompat.requestPermissions(this, permissions, REQUEST_ID_ACCESS_COURSE_FINE_LOCATION);

                return true;
        }
        return false;
    }


    // Call this method only when you have the permissions to view a user's location.
    private void findLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        String locationProvider = this.getEnabledLocationProvider();

        if (locationProvider == null) {
            return;
        }

        final long MIN_TIME_BW_UPDATES = 1000;
        final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;

        android.location.Location myLocation = null;
        try {
            // This code need permissions (Asked above ***)
            locationManager.requestLocationUpdates(locationProvider, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
            // Getting Location.
            myLocation = locationManager.getLastKnownLocation(locationProvider);
        }
        // With Android API >= 23, need to catch SecurityException.
        catch (SecurityException e) {
            Toast.makeText(this, "Show Location Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }

        if (myLocation != null) {

            LatLng latLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .zoom(15)
                    .bearing(90)
                    .tilt(40)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


            // Add Marker to Map
            MarkerOptions option = new MarkerOptions();
            option.title("My Location");
            option.snippet("....");
            option.position(latLng);
            Marker currentMarker = mMap.addMarker(option);
            currentMarker.showInfoWindow();
        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_LONG).show();
        }

    }


    //for showing GPS Disabled  Alert
    private void showDisabledAlert(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                   try
                   {
                     if(message.equals(MESSAGE_1)){
                     Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                     startActivityForResult(intent, REQUEST_ID_FOR_GPS);
                     }else{
                      Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                       intent.setData(Uri.parse("package:" + PackageName));
                        startActivityForResult(intent,REQUEST_ID_FOR_LOCATION_PERMISSION);
                    }
                  }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                 }
                });
        alertDialogBuilder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    // Find Location provider is openning.
    private String getEnabledLocationProvider() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Criteria to find location provider.
        Criteria criteria = new Criteria();

        // Returns the name of the provider that best meets the given criteria.
        // ==> "gps", "network",...
        String bestProvider = locationManager.getBestProvider(criteria, true);

        if(bestProvider==null){
            return null;
        }
        boolean enabled = locationManager.isProviderEnabled(bestProvider);

        if (!enabled) {
            Toast.makeText(this, "No location provider enabled!", Toast.LENGTH_LONG).show();
            return null;
        }
        return bestProvider;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
