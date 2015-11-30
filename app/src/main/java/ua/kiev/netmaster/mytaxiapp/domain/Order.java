package ua.kiev.netmaster.mytaxiapp.domain;

import java.util.Date;

/**
 * Created by ПК on 15.07.2015.
 * дата, клиент, сумма, адрес подачи, адрес назначения
 *
 */

public class Order {

    private Long id;
    private Date orderDay;
    private Client client;
    private Double orderSum;
    private String departureAddress;
    private String destinationAddress;
    private Boolean isComplite;

    public Order() {
    }

    public Order(Long id, Date orderDay, Client client, Double orderSum, String departureAddress, String destinationAddress, Boolean isComplite) {
        this.id = id;
        this.orderDay = orderDay;
        this.client = client;
        this.orderSum = orderSum;
        this.departureAddress = departureAddress;
        this.destinationAddress = destinationAddress;
        this.isComplite = isComplite;
    }

    public Order(Date orderDay, Client client, Double orderSum, String departureAddress, String destinationAddress, Boolean isComplite) {
        this.orderDay = orderDay;
        this.client = client;
        this.orderSum = orderSum;
        this.departureAddress = departureAddress;
        this.destinationAddress = destinationAddress;
        this.isComplite = isComplite;
    }

    public Boolean getIsComplite() {
        return isComplite;
    }

    public void setIsComplite(Boolean isComplite) {
        this.isComplite = isComplite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(Date orderDay) {
        this.orderDay = orderDay;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Double orderSum) {
        this.orderSum = orderSum;
    }

    public String getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(String departureAddress) {
        this.departureAddress = departureAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDay=" + orderDay +
                ", client=" + client +
                ", orderSum=" + orderSum +
                ", departureAddress='" + departureAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                '}';
    }
}
