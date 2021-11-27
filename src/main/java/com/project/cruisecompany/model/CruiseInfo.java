package com.project.cruisecompany.model;

import com.project.cruisecompany.model.enums.RoomType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cruise_info")
public class CruiseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //todo связи ship & user

    @Column(name = "ship_id")
    private long shipId;
    @Column(name = "room_type")
    private RoomType roomType;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "user_id")
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
        this.shipId = shipId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CruiseInfo)) return false;
        CruiseInfo that = (CruiseInfo) o;
        return id == that.id &&
                shipId == that.shipId &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                roomType == that.roomType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, shipId, roomType, totalPrice);
    }

    @Override
    public String toString() {
        return "CruiseInfoDaoDaoImpl{" +
                "id=" + id +
                ", shipId=" + shipId +
                ", roomType=" + roomType +
                ", price=" + totalPrice +
                '}';
    }


}
