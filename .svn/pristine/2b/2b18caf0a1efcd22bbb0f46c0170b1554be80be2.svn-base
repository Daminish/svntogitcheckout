package com.capco.technologies.living.storage;

import android.content.Context;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.AreaDetailMenu;
import com.capco.technologies.living.domain.model.BudgetMenu;
import com.capco.technologies.living.domain.model.location.City;
import com.capco.technologies.living.domain.model.DashboardMenu;
import com.capco.technologies.living.domain.model.FindHomeMenu;
import com.capco.technologies.living.domain.model.location.ICity;
import com.capco.technologies.living.domain.model.location.ILocality;
import com.capco.technologies.living.domain.model.location.Locality;
import com.capco.technologies.living.domain.model.Offer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sachin on 06/01/18.
 */

public class MockData {

    private static final HashMap DUMMY_CREDENTIALS = new HashMap();
    private static final List<Offer> OFFERS = new ArrayList<>();
    private static final List<DashboardMenu> DASHBOARD_MENUS = new ArrayList<>();
    private static final List<BudgetMenu> BUDGET_MENUS = new ArrayList<>();
    private static final List<AreaDetailMenu> AREA_DETAIL_MENUS_MENUS = new ArrayList<>();
    private static final List<ICity> CITY = new ArrayList<>();
    private static final List<ILocality> LOCALITIES = new ArrayList<>();

    private static final List<Locality> SAVED_ADD = new ArrayList<>();

    private static final List<FindHomeMenu> FIND_HOME_MENUS = new ArrayList<>();


    private static MockData mInstance;
    private Context context;


    private MockData(Context context) {
        this.context = context;
        initUsers();
        initOffers();
        initDashboardMenu();
        initBudgetMenus();
        initAreaDetailMenus();
        initFindHomeMenus();
        initCity();
        try {
            initLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        savedAddress();
    }

    private void initBudgetMenus() {
        BUDGET_MENUS.add(new BudgetMenu("Estimate Budget", "", R.drawable.estimate));
        BUDGET_MENUS.add(new BudgetMenu("Your Deposit", "", R.drawable.borrow));
        BUDGET_MENUS.add(new BudgetMenu("How much I can borrow?", "", R.drawable.deposits));
        BUDGET_MENUS.add(new BudgetMenu("Saving a deposit", "", R.drawable.piggy_bank));
    }

    private void initAreaDetailMenus() {
        AREA_DETAIL_MENUS_MENUS.add(new AreaDetailMenu("Flat/Apartment", R.drawable.ic_info));
        AREA_DETAIL_MENUS_MENUS.add(new AreaDetailMenu("House/Villa", R.drawable.ic_info));
        AREA_DETAIL_MENUS_MENUS.add(new AreaDetailMenu("Village House", R.drawable.ic_info));
        AREA_DETAIL_MENUS_MENUS.add(new AreaDetailMenu("Crime Rating", R.drawable.ic_info));
        AREA_DETAIL_MENUS_MENUS.add(new AreaDetailMenu("School Rating", R.drawable.ic_info));
        AREA_DETAIL_MENUS_MENUS.add(new AreaDetailMenu("Broadband", R.drawable.ic_info));
        AREA_DETAIL_MENUS_MENUS.add(new AreaDetailMenu("Population", R.drawable.ic_info));
    }

    private void initFindHomeMenus() {
        FIND_HOME_MENUS.add(new FindHomeMenu("Area Guide", R.drawable.area_guide));
        FIND_HOME_MENUS.add(new FindHomeMenu("Finding Your Property", R.drawable.find_property));
    }

    public static List<FindHomeMenu> getFindHomeMenus() {
        return FIND_HOME_MENUS;
    }

    public static void init(Context context) {
        if (mInstance == null) {
            mInstance = new MockData(context);
        }

    }


    public static MockData getInstance() {
        if (mInstance == null)
            throw new NullPointerException("Please call MockData.init(Context) from Application class before accessing instance of class");

        return mInstance;
    }


    public List<Offer> getOFFERS() {
        return OFFERS;
    }

    public HashMap getDummyCredentials() {
        return DUMMY_CREDENTIALS;
    }

    public List<DashboardMenu> getDashboardMenus() {
        return DASHBOARD_MENUS;
    }

    public List<BudgetMenu> getBudgetMenus() {
        return BUDGET_MENUS;
    }

    public List<AreaDetailMenu> getAreaDetailMenus() {
        return AREA_DETAIL_MENUS_MENUS;
    }

    private void initDashboardMenu() {

        int menuId = 1;
        DashboardMenu menu = new DashboardMenu();
        menu.id = menuId++;
        menu.icon = R.mipmap.ic_launcher_round;
        menu.title = context.getString(R.string.home_screen_item1);
        menu.desc = context.getString(R.string.home_screen_item1_dscr);
        DASHBOARD_MENUS.add(menu);

        menu = new DashboardMenu();
        menu.id = menuId++;
        menu.icon = R.mipmap.ic_launcher_round;
        menu.title = context.getString(R.string.home_screen_item2);
        menu.desc = context.getString(R.string.home_screen_item2_dscr);
        DASHBOARD_MENUS.add(menu);

        menu = new DashboardMenu();
        menu.id = menuId++;
        menu.icon = R.mipmap.ic_launcher_round;
        menu.title = context.getString(R.string.home_screen_item3);
        menu.desc = context.getString(R.string.home_screen_item3_dscr);
        DASHBOARD_MENUS.add(menu);

        menu = new DashboardMenu();
        menu.id = menuId++;
        menu.icon = R.mipmap.ic_launcher_round;
        menu.title = context.getString(R.string.home_screen_item4);
        menu.desc = context.getString(R.string.home_screen_item4_dscr);
        DASHBOARD_MENUS.add(menu);

        menu = new DashboardMenu();
        menu.id = menuId++;
        menu.icon = R.mipmap.ic_launcher_round;
        menu.title = context.getString(R.string.home_screen_item5);
        menu.desc = context.getString(R.string.home_screen_item5_dscr);
        DASHBOARD_MENUS.add(menu);

        menu = new DashboardMenu();
        menu.id = menuId++;
        menu.icon = R.mipmap.ic_launcher_round;
        menu.title = context.getString(R.string.home_screen_item6);
        menu.desc = context.getString(R.string.home_screen_item6_dscr);
        DASHBOARD_MENUS.add(menu);

    }

    private void initOffers() {
        Offer offer = new Offer();
        offer.setImageUrl("house_01");
        offer.setTitle("Home Loan Interest");
        offer.setShortDesctiption("Offer ending on 31st Jan. 2018");
        offer.setId(1);
        OFFERS.add(offer);

        offer = new Offer();
        offer.setImageUrl("house_02");
        offer.setTitle("THAI Credit Card");
        offer.setShortDesctiption("Life time free Tahi CreditCard offer till 28 Feb 2018");
        offer.setId(2);
        OFFERS.add(offer);

        offer = new Offer();
        offer.setImageUrl("house_03");
        offer.setTitle("AXIS Credit Card");
        offer.setShortDesctiption("Reward Credit Card offer till 31 Jan 2018");
        offer.setId(2);
        OFFERS.add(offer);


        offer = new Offer();
        offer.setImageUrl("house_04");
        offer.setTitle("Mortgage Loan");
        offer.setShortDesctiption("Mortgage loan at attractive interest rate till 31 Jan 2018");
        offer.setId(2);
        OFFERS.add(offer);

    }

    private void initUsers() {
        DUMMY_CREDENTIALS.put("ankush@capco.com", "hellow");
        DUMMY_CREDENTIALS.put("shreyas@capco.com", "hworld");
    }


    private void initCity() {



    }

    private void initLocality() throws IOException {


        InputStream is = context.getAssets().open("city.json");
        Gson gson = new GsonBuilder().create();
        Reader reader = new InputStreamReader(is, "UTF-8");
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.beginArray();
        CITY.clear();
        while (jsonReader.hasNext()) {
            City city = gson.fromJson(jsonReader, City.class);
            CITY.add(city);

            Locality locality = new Locality();
            locality.setId(city.getId());
            locality.setName(city.getName());
            locality.setLatitude(city.getLatitude());
            locality.setLongitude(city.getLongitude());


            LOCALITIES.add(locality);
        }
        jsonReader.endArray();
        jsonReader.close();

        //LOCALITIES.clear();

    }

    public static List<AreaDetailMenu> getAreaDetailMenusMenus() {
        return AREA_DETAIL_MENUS_MENUS;
    }

    public  List<ICity> getCity() {
        return CITY;
    }

    public  List<ILocality> getLocalities() {
        return LOCALITIES;
    }


    private void savedAddress() {
//        SAVED_ADD.add(new Locality("Akat", ""));
//        SAVED_ADD.add(new Locality("Sakon Nakhon", ""));
    }

}
