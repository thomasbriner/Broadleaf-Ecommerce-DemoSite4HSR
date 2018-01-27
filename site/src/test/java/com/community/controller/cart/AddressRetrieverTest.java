/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
 ***/
package com.community.controller.cart;

import java.io.*;

import com.community.hsr.testing.address.Address;
import com.community.hsr.testing.address.AddressRetriever;
import com.community.hsr.testing.address.util.Http;
import org.json.simple.parser.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddressRetrieverTest {
    private Http httpMock;
    private AddressRetriever retriever;

    @Before
    public void createRetriever() {
        httpMock = mock(Http.class);
        retriever = new AddressRetriever(httpMock);
    }

    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {
        when(httpMock.get(contains("lat=38.000000&lon=-104.000000")))
                .thenReturn("{\"address\":{"
                        + "\"house_number\":\"324\","
                        // ...
                        + "\"road\":\"North Tejon Street\","
                        + "\"city\":\"Colorado Springs\","
                        + "\"state\":\"Colorado\","
                        + "\"postcode\":\"80903\","
                        + "\"country_code\":\"us\"}"
                        + "}");

        Address address = retriever.retrieve(38.0, -104.0);

        assertThat(address.getHouseNumber(), equalTo("324"));
        assertThat(address.getRoad(), equalTo("North Tejon Street"));
        assertThat(address.getCity(), equalTo("Colorado Springs"));
        assertThat(address.getState(), equalTo("Colorado"));
        assertThat(address.getZip(), equalTo("80903"));
    }
}
