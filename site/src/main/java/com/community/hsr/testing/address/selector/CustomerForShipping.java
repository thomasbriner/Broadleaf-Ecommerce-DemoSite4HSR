package com.community.hsr.testing.address.selector;

import com.community.hsr.testing.address.AddressHistoryChain;

import java.util.Optional;

public class CustomerForShipping {

    private AddressHistoryChain defaultAdress;
    private Optional<AddressHistoryChain> shippingAdress;


    private boolean internalCustomer;

    private Optional<Company> associatedCompany;

}
