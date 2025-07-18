package com.motorph.model;

public class Address {
    private String addressId;
    private String street;
    private String barangay;
    private String city;
    private String province;
    private String postalCode;

    public Address(String addressId, String street, String barangay, 
                  String city, String province, String postalCode) {
        this.addressId = addressId;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    // Getters
    public String getAddressId() { return addressId; }
    public String getStreet() { return street; }
    public String getBarangay() { return barangay; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getPostalCode() { return postalCode; }
}
