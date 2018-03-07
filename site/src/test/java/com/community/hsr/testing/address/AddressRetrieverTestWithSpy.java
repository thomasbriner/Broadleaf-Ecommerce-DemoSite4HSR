package com.community.hsr.testing.address;

import com.community.hsr.testing.address.util.Http;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class AddressRetrieverTestWithSpy {

    private HttpImpl httpSpy;
    private AddressRetriever retriever;

    @Before
    public void createRetriever() {
        httpSpy = spy(new HttpImpl());
        retriever = new AddressRetriever(httpSpy);
    }

    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {
        when(httpSpy.executeRequest(any(), any()))
                .thenReturn("{\"address\":{"
                        + "\"house_number\":\"324\","
                        // ...
                        + "\"road\":\"North Tejon Street\","
                        + "\"city\":\"Colorado Springs\","
                        + "\"state\":\"Colorado\","
                        + "\"postcode\":\"80903\","
                        + "\"country_code\":\"us\"}"
                        + "}");

    }

}
