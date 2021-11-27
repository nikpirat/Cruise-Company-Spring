package com.project.cruisecompany.model.enums;

public enum RoomType {
    PRESIDENT(500),COMFORT(250),STANDART(100);

    private int price;

    RoomType(int price){
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
}}
