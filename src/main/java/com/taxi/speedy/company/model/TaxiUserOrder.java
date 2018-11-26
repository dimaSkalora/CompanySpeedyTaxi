package com.taxi.speedy.company.model;

import java.time.LocalDateTime;

public class TaxiUserOrder extends AbstractBaseEntity {
    private LocalDateTime dateTimeOrder;
    private User idUser;
    private String addressDeparture;
    private String addressArrival;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer fulfilled;

    public TaxiUserOrder() {
    }

    public TaxiUserOrder(LocalDateTime dateTimeOrder, User idUser, String addressDeparture, String addressArrival, LocalDateTime startDate, LocalDateTime endDate, Integer fulfilled) {
        this.dateTimeOrder = dateTimeOrder;
        this.idUser = idUser;
        this.addressDeparture = addressDeparture;
        this.addressArrival = addressArrival;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fulfilled = fulfilled;
    }

    public TaxiUserOrder(Integer id, LocalDateTime dateTimeOrder, User idUser, String addressDeparture, String addressArrival, LocalDateTime startDate, LocalDateTime endDate, Integer fulfilled) {
        super(id);
        this.dateTimeOrder = dateTimeOrder;
        this.idUser = idUser;
        this.addressDeparture = addressDeparture;
        this.addressArrival = addressArrival;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fulfilled = fulfilled;
    }

    public TaxiUserOrder(Integer id, User idUser, String addressDeparture, String addressArrival, LocalDateTime startDate, LocalDateTime endDate, Integer fulfilled) {
       this(id, LocalDateTime.now(), idUser, addressDeparture, addressArrival, startDate, endDate, fulfilled);
    }

    public LocalDateTime getDateTimeOrder() {
        return dateTimeOrder;
    }

    public void setDateTimeOrder(LocalDateTime dateTimeOrder) {
        this.dateTimeOrder = dateTimeOrder;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getAddressDeparture() {
        return addressDeparture;
    }

    public void setAddressDeparture(String addressDeparture) {
        this.addressDeparture = addressDeparture;
    }

    public String getAddressArrival() {
        return addressArrival;
    }

    public void setAddressArrival(String addressArrival) {
        this.addressArrival = addressArrival;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Integer fulfilled) {
        this.fulfilled = fulfilled;
    }

    @Override
    public String toString() {
        return "TaxiUserOrder{" +
                "dateTimeOrder=" + dateTimeOrder +
                ", idUser=" + idUser +
                ", addressDeparture='" + addressDeparture + '\'' +
                ", addressArrival='" + addressArrival + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fulfilled=" + fulfilled +
                ", id=" + id +
                '}';
    }
}
