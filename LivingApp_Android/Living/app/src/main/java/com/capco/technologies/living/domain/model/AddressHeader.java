package com.capco.technologies.living.domain.model;

/**
 * Created by test on 09/01/18.
 */

public class AddressHeader implements IAddressHeader {

    public String name;

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IAddressHeader && ((IAddressHeader) obj).getName().equals(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
