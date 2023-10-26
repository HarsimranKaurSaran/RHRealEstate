package com.example.rhrealestate;

// pojo class for houses
public class Houses {

    private int houseId, image;
    private String address, price, description;

    public Houses() {
    }

    public Houses(int houseId, String address, String price, String description, int image) {
        this.houseId = houseId;
        this.address = address;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    // getters and setters
    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
