package com.community.hsr.testing.address.selector;

import com.community.hsr.testing.address.Address;

public class ShippingAddressSelector {

    public static Address INTERNAL_ADDRESS;

    public Address selectShippingAddress(CustomerForShipping customer){
        return INTERNAL_ADDRESS;


    }

}
