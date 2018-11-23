package com.taxi.speedy.company.model;

import java.time.LocalDateTime;

public class AccessToRoute extends AbstractBaseEntity {
    private LocalDateTime checksDateTime;
    private UserVehicle idUserVehicle;
    private UserState idUserState;
    private VehicleState idVehicleState;
    private Integer isAccess;

    public AccessToRoute() {
    }

    public AccessToRoute(LocalDateTime checksDateTime, UserVehicle idUserVehicle, UserState idUserState, VehicleState idVehicleState, Integer isAccess) {
        this.checksDateTime = checksDateTime;
        this.idUserVehicle = idUserVehicle;
        this.idUserState = idUserState;
        this.idVehicleState = idVehicleState;
        this.isAccess = isAccess;
    }

    public AccessToRoute(UserVehicle idUserVehicle, UserState idUserState, VehicleState idVehicleState, Integer isAccess) {
        this(LocalDateTime.now(), idUserVehicle, idUserState, idVehicleState, isAccess);
    }

    public AccessToRoute(Integer id, LocalDateTime checksDateTime, UserVehicle idUserVehicle, UserState idUserState, VehicleState idVehicleState, Integer isAccess) {
        super(id);
        this.checksDateTime = checksDateTime;
        this.idUserVehicle = idUserVehicle;
        this.idUserState = idUserState;
        this.idVehicleState = idVehicleState;
        this.isAccess = isAccess;
    }

    public AccessToRoute(Integer id, UserVehicle idUserVehicle, UserState idUserState, VehicleState idVehicleState, Integer isAccess) {
        this(id, LocalDateTime.now(), idUserVehicle, idUserState, idVehicleState, isAccess);
    }

    public LocalDateTime getChecksDateTime() {
        return checksDateTime;
    }

    public void setChecksDateTime(LocalDateTime checksDateTime) {
        this.checksDateTime = checksDateTime;
    }

    public UserVehicle getIdUserVehicle() {
        return idUserVehicle;
    }

    public void setIdUserVehicle(UserVehicle idUserVehicle) {
        this.idUserVehicle = idUserVehicle;
    }

    public UserState getIdUserState() {
        return idUserState;
    }

    public void setIdUserState(UserState idUserState) {
        this.idUserState = idUserState;
    }

    public VehicleState getIdVehicleState() {
        return idVehicleState;
    }

    public void setIdVehicleState(VehicleState idVehicleState) {
        this.idVehicleState = idVehicleState;
    }

    public Integer getIsAccess() {
        return isAccess;
    }

    public void setIsAccess(Integer isAccess) {
        this.isAccess = isAccess;
    }

    @Override
    public String toString() {
        return "AccessToRoute{" +
                "checksDateTime=" + checksDateTime +
                ", idUserVehicle=" + idUserVehicle +
                ", idUserState=" + idUserState +
                ", idVehicleState=" + idVehicleState +
                ", isAccess=" + isAccess +
                ", id=" + id +
                '}';
    }
}