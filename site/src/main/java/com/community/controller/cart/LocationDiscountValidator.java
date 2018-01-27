package com.community.controller.cart;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class LocationDiscountValidator {

    AddressRetriever retriever = new AddressRetriever(new HttpImpl());

    public boolean isAuthorizedForDiscount(double latitude, double longitude) throws IOException, ParseException {

        Address address = retriever.retrieve(latitude, longitude);
        return LocationDiscountAddressChecker.isDiscountAddress(address);

    }


}
