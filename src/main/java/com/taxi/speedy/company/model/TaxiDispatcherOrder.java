package com.taxi.speedy.company.model;

import java.time.LocalDateTime;

public class TaxiDispatcherOrder extends AbstractBaseEntity {
    private LocalDateTime dateTimeOrder;
    private TaxiDispatcher idTaxiDispatcher;
    private String userName;
    private String userPhone;
    private String addressDeparture;
    private String addressArrival;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer fulfilled;

    public TaxiDispatcherOrder() {
    }

    public TaxiDispatcherOrder(LocalDateTime dateTimeOrder, TaxiDispatcher idTaxiDispatcher, String userName, String userPhone, String addressDeparture, String addressArrival, LocalDateTime startDate, LocalDateTime endDate, Integer fulfilled) {
        this.dateTimeOrder = dateTimeOrder;
        this.idTaxiDispatcher = idTaxiDispatcher;
        this.userName = userName;
        this.userPhone = userPhone;
        this.addressDeparture = addressDeparture;
        this.addressArrival = addressArrival;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fulfilled = fulfilled;
    }

    public TaxiDispatcherOrder(Integer id, LocalDateTime dateTimeOrder, TaxiDispatcher idTaxiDispatcher, String userName, String userPhone, String addressDeparture, String addressArrival, LocalDateTime startDate, LocalDateTime endDate, Integer fulfilled) {
        super(id);
        this.dateTimeOrder = dateTimeOrder;
        this.idTaxiDispatcher = idTaxiDispatcher;
        this.userName = userName;
        this.userPhone = userPhone;
        this.addressDeparture = addressDeparture;
        this.addressArrival = addressArrival;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fulfilled = fulfilled;
    }

    public TaxiDispatcherOrder(Integer id, TaxiDispatcher idTaxiDispatcher, String userName, String userPhone, String addressDeparture, String addressArrival, LocalDateTime startDate, LocalDateTime endDate, Integer fulfilled) {
        this( id, LocalDateTime.now(), idTaxiDispatcher, userName, userPhone, addressDeparture, addressArrival, startDate, endDate, fulfilled);
    }

    public LocalDateTime getDateTimeOrder() {
        return dateTimeOrder;
    }

    public void setDateTimeOrder(LocalDateTime dateTimeOrder) {
        this.dateTimeOrder = dateTimeOrder;
    }

    public TaxiDispatcher getIdTaxiDispatcher() {
        return idTaxiDispatcher;
    }

    public void setIdTaxiDispatcher(TaxiDispatcher idTaxiDispatcher) {
        this.idTaxiDispatcher = idTaxiDispatcher;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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
        return "TaxiDispatcherOrder{" +
                "dateTimeOrder=" + dateTimeOrder +
                ", idTaxiDispatcher=" + idTaxiDispatcher +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", addressDeparture='" + addressDeparture + '\'' +
                ", addressArrival='" + addressArrival + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fulfilled=" + fulfilled +
                ", id=" + id +
                '}';
    }
}
