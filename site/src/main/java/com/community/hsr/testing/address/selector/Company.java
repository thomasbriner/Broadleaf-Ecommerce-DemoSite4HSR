package com.community.hsr.testing.address.selector;

import com.community.hsr.testing.address.AddressHistoryChain;

public class Company {
    private final AddressHistoryChain addressHistoryChain;

    public Company(AddressHistoryChain addressHistoryChain) {
        this.addressHistoryChain = addressHistoryChain;
    }

    public AddressHistoryChain getShippingAddressHistoryChain() {
        return addressHistoryChain;
    }
}
