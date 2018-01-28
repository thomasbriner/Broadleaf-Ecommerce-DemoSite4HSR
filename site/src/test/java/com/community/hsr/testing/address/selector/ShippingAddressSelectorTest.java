package com.community.hsr.testing.address.selector;

import com.community.hsr.testing.address.Address;
import com.community.hsr.testing.address.AddressHistoryChain;
import org.junit.Test;

import java.util.Optional;

import static com.community.hsr.testing.address.selector.ShippingAddressSelector.INTERNAL_ADDRESS;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ShippingAddressSelectorTest {


    public static final String DEFAULT_CITY = "DefaultCity";
    public static final String SHIPPING_CITY = "ShippingCity";
    public static final String COMPANY_CITY = "CompanyCity";
    public static final String UNAPPROVED_DEFAULT_CITY = "UnapprovedDefaultCity";
    public static final String APPROVED_DEFAULT_CITY = "ApprovedDefaultCity";
    public static final String NEW_APPROVED_DEFAULT_CITY = "NewApprovedDefaultCity";

    @Test
    public void internalAddressShouldBeChosenForInternalCustomer() {
        test()
                .setInternalCustomer()
                .run()
                .assertInternalAddressIsSelected();
    }
    @Test
    public void companyAddressIsChosenIfCustomerIsAssociatedWithCompany() {
        test()
                .setAssociatedCompany()
                .run()
                .assertAddressIsFound()
                .assertCompanyAddressIsSelected();
    }

    @Test
    public void defaultAddressIsChosenIfNoShippingAddressExists() {
        test()
                .run()
                .assertAddressIsFound()
                .assertDefaultAddressIsSelected();
    }

    @Test
    public void shippingAddressIsChosenIfExists() {
        test()
                .setShippingAddress()
                .run()
                .assertAddressIsFound()
                .assertShippingAddressIsSelected();
    }

    @Test
    public void onlyApprovedAddressIsTakenIfRestricted() throws Exception {
        test()
                .addApprovedDefaultAddress()
                .addUnapprovedDefaultAddress()
                .useOnlyApprovedAddresses()
                .run()
                .assertApprovedDefaultAddressIsTaken();
    }

    @Test
    public void unapprovedAddressIsTakenIfNotRestricted() throws Exception {
        test()
                .addUnapprovedDefaultAddress()
                .run()
                .assertUnapprovedDefaultAddressIsTaken();
    }

    @Test
    public void noAddressIsSelectedIfRestricted() throws Exception {
        testWithoutPrefilledAddresses()
                .addUnapprovedDefaultAddress()
                .useOnlyApprovedAddresses()
                .run()
                .assertNoAddressIsTaken();
    }

    @Test
    public void newestAddressIsTaken() throws Exception {
        test()
                .addApprovedDefaultAddress()
                .addNewApprovedDefaultAddress()
                .useOnlyApprovedAddresses()
                .run()
                .assertNewApprovedDefaultAddressIsTaken();
    }



    private TestBuilder test() {
        return new TestBuilder(true);
    }

    private TestBuilder testWithoutPrefilledAddresses() {
        return new TestBuilder(false);
    }


    private static class TestBuilder {

        private final ShippingAddressSelector shippingAddressSelector;
        private final CustomerForShipping customer;
        private boolean hasToBeApproved;

        public TestBuilder(boolean usePrefilledAddresses) {
            shippingAddressSelector = new ShippingAddressSelector();
            AddressHistoryChain defaultAddressChain;
            if (usePrefilledAddresses) {
                defaultAddressChain = new AddressHistoryChain(new AddressBuilder().city(DEFAULT_CITY).build());
            } else {
                defaultAddressChain = new AddressHistoryChain();
            }
            customer = new CustomerForShipping(defaultAddressChain);
            hasToBeApproved = false;
        }

        public TestBuilder setInternalCustomer() {
            customer.setInternalCustomer(true);
            return this;
        }

        public TestBuilder setShippingAddress() {
            customer.setShippingAddress(new AddressHistoryChain(new AddressBuilder().city(SHIPPING_CITY).build()));
            return this;
        }

        public TestResult run() {
            Optional<Address> selectedAddress = shippingAddressSelector.selectShippingAddress(customer, hasToBeApproved);

            return new TestResult(selectedAddress);
        }


        public TestBuilder setAssociatedCompany() {
            Company company = new Company(new AddressHistoryChain(new AddressBuilder().city(COMPANY_CITY).build()));
            customer.setAssociatedCompany(company);
            return this;
        }

        public TestBuilder addUnapprovedDefaultAddress() {
            customer.getDefaultAddress().addAddress(new AddressBuilder()
                    .city(UNAPPROVED_DEFAULT_CITY)
                    .approved(false)
                    .build());
            return this;
        }

        public TestBuilder addApprovedDefaultAddress() {
            customer.getDefaultAddress().addAddress(new AddressBuilder()
                    .city(APPROVED_DEFAULT_CITY)
                    .approved(true)
                    .build());
            return this;
        }

        public TestBuilder addNewApprovedDefaultAddress() {
            customer.getDefaultAddress().addAddress(new AddressBuilder()
                    .city(NEW_APPROVED_DEFAULT_CITY)
                    .approved(true)
                    .build());
            return this;
        }
        public TestBuilder useOnlyApprovedAddresses() {
            hasToBeApproved = true;
            return this;
        }
    }

    private static class TestResult {

        private final Optional<Address> selectedAddress;

        public TestResult(Optional<Address> selectedAddress) {
            this.selectedAddress = selectedAddress;
        }

        public TestResult assertAddressIsFound() {
            assertThat(selectedAddress.isPresent(), is(true));
            return this;
        }

        public TestResult assertInternalAddressIsSelected() {
            assertAddressIsFound();
            assertThat(selectedAddress.get(), is(INTERNAL_ADDRESS));
            return this;
        }

        public TestResult assertDefaultAddressIsSelected() {
            assertAddressIsFound();
            assertThat(selectedAddress.get().getCity(), is(DEFAULT_CITY));
            return this;
        }

        public TestResult assertShippingAddressIsSelected() {
            assertAddressIsFound();
            assertThat(selectedAddress.get().getCity(), is(SHIPPING_CITY));
            return this;
        }

        public TestResult assertCompanyAddressIsSelected() {
            assertAddressIsFound();
            assertThat(selectedAddress.get().getCity(), is(COMPANY_CITY));
            return this;
        }

        public TestResult assertApprovedDefaultAddressIsTaken() {
            assertThat(selectedAddress.get().getCity(), is(APPROVED_DEFAULT_CITY));
            return this;
        }

        public TestResult assertUnapprovedDefaultAddressIsTaken() {
            assertThat(selectedAddress.get().getCity(), is(UNAPPROVED_DEFAULT_CITY));
            return this;
        }

        public TestResult assertNoAddressIsTaken() {
            assertThat(selectedAddress.isPresent(), is(false));
            return this;
        }

        public TestResult assertNewApprovedDefaultAddressIsTaken() {
            assertThat(selectedAddress.get().getCity(), is(NEW_APPROVED_DEFAULT_CITY));
            return this;
        }
    }

    private static class AddressBuilder {
        private String road = "SomeAvenue";
        private String city = "AnyCity";
        private String state = "NowhereState";
        private String zip = "4242";
        private String houseNumber = "42";
        private boolean approved = true;


        public AddressBuilder road(String road) {
            this.road = road;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder state(String state) {
            this.state = state;
            return this;
        }

        public AddressBuilder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public AddressBuilder houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder approved(boolean approved) {
            this.approved = approved;
            return this;
        }

        public Address build() {
            Address address = new Address(houseNumber, road, city, state, zip);
            address.setApproved(approved);
            return address;
        }


    }

}
