/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package com.community.hsr.testing.address;

public class Address {
   private final String road;
   private final String city;
   private final String state;
   private final String zip;
   private final String houseNumber;

   private boolean approved;

   public Address(String houseNumber, String road, String city, String state, String zip) {
      this.houseNumber = houseNumber;
      this.road = road;
      this.city = city;
      this.state = state;
      this.zip = zip;
   }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getRoad() {
      return road;
   }

   public String getCity() {
      return city;
   }

   public String getState() {
      return state;
   }

   public String getZip() {
      return zip;
   }

   public boolean isApproved() {
      return approved;
   }

   public String getHouseNumber() {

      return houseNumber;
   }



   @Override
   public String toString() {
      return houseNumber + " " + road + ", " + city + " " + state + " " + zip;
   }


   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Address address = (Address) o;

      if (!road.equals(address.road)) return false;
      if (!city.equals(address.city)) return false;
      if (!state.equals(address.state)) return false;
      if (!zip.equals(address.zip)) return false;
      return houseNumber.equals(address.houseNumber);
   }

   @Override
   public int hashCode() {
      int result = road.hashCode();
      result = 31 * result + city.hashCode();
      result = 31 * result + state.hashCode();
      result = 31 * result + zip.hashCode();
      result = 31 * result + houseNumber.hashCode();
      return result;
   }
}
