package com.capco.technologies.living.domain.model.location;

import java.util.List;

/**
 * Created by test on 08/01/18.
 */

public interface ILocation {

    int getId();

    void setId(int id);

    String getName();

    void setName(String name);


    double getLatitude();

    void setLatitude(double latitude);

    double getLongitude();

    void setLongitude(double longitude);


//    String cityOrLocality;
//
//    public String getCityOrLocality() {
//        return cityOrLocality;
//    }
//
//    public void setCityOrLocality(String cityOrLocality) {
//        this.cityOrLocality = cityOrLocality;
//    }
}
