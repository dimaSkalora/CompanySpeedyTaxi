package com.taxi.speedy.company.model;


import java.time.LocalDateTime;

public class UserVehicle extends AbstractBaseEntity{
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User idUser;
    private Vehicle idVehicle;
    private Integer isCurrentUserMachine;

    public UserVehicle() {
    }

    public UserVehicle(Integer id, LocalDateTime startDate, LocalDateTime endDate, Integer isCurrentUserMachine) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrentUserMachine = isCurrentUserMachine;
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

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Vehicle getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Vehicle idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Integer getIsCurrentUserMachine() {
        return isCurrentUserMachine;
    }

    public void setIsCurrentUserMachine(Integer isCurrentUserMachine) {
        this.isCurrentUserMachine = isCurrentUserMachine;
    }

    @Override
    public String toString() {
        return "UserVehicle{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", idUser=" + idUser +
                ", idVehicle=" + idVehicle +
                ", isCurrentUserMachine=" + isCurrentUserMachine +
                ", id=" + id +
                '}';
    }
}