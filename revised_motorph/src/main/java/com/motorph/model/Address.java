package com.motorph.model;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Address entity representing employee address information.
 */
public class Address {
    
    private String addressId;
    
    @NotBlank(message = "Street is required")
    private String street;
    
    @NotBlank(message = "Barangay is required")
    private String barangay;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "Province is required")
    private String province;
    
    @NotBlank(message = "Postal code is required")
    private String postalCode;
    
    // Default constructor
    public Address() {}
    
    // Constructor with all fields
    public Address(String addressId, String street, String barangay, 
                  String city, String province, String postalCode) {
        this.addressId = addressId;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }
    
    // Getters and Setters
    public String getAddressId() {
        return addressId;
    }
    
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getBarangay() {
        return barangay;
    }
    
    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public String getFullAddress() {
        return String.format("%s, %s, %s, %s %s", 
                           street, barangay, city, province, postalCode);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
    
    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", street='" + street + '\'' +
                ", barangay='" + barangay + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
