package com.taxi.speedy.company.dto;

import com.taxi.speedy.company.model.Role;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.Vehicle;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class UserVehicleFull extends BaseTo{
    private LocalDateTime startDate;                //Дата когда пользователь взял машину
    private LocalDateTime endDate;                  //Дата когда пользователь вернул машину
    private Integer isCurrentUserMachine;           //Машина текущего пользователя: 0 - нету машины; 1 - есть машина
    private Integer idUser;
    private String nameUser;
    private String emailUser;
    private String passwordUser;
    private String phoneUser;
    private String addressUser;
    private Set<Role> rolesUser;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registeredUser;        //Дата регистраиции пользователя
    private boolean enabledUser;        //true - активный, false - не активный
    private int idVehicle;
    private String nameCarVehicle;                    //Название, модель ТС
    private String vehicleNumberVehicle;              //Номер ТС
    private Integer yearIssueVehicle;                 //Год выпуска ТС
    private String categoryVehicle;                    //Легковая, грузовая, автобус и т.д.
    private String colorVehicle;                       //Цвет ТС
    private Integer fuelConsumptionVehicle;           //Расход топлива л/100км

    public UserVehicleFull(Integer id, LocalDateTime startDate, LocalDateTime endDate, Integer isCurrentUserMachine, Integer idUser, String nameUser, String emailUser, String passwordUser, String phoneUser, String addressUser, Set<Role> rolesUser, Date registeredUser, boolean enabledUser, int idVehicle, String nameCarVehicle, String vehicleNumberVehicle, Integer yearIssueVehicle, String categoryVehicle, String colorVehicle, Integer fuelConsumptionVehicle) {
        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrentUserMachine = isCurrentUserMachine;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.phoneUser = phoneUser;
        this.addressUser = addressUser;
        this.rolesUser = rolesUser;
        this.registeredUser = registeredUser;
        this.enabledUser = enabledUser;
        this.idVehicle = idVehicle;
        this.nameCarVehicle = nameCarVehicle;
        this.vehicleNumberVehicle = vehicleNumberVehicle;
        this.yearIssueVehicle = yearIssueVehicle;
        this.categoryVehicle = categoryVehicle;
        this.colorVehicle = colorVehicle;
        this.fuelConsumptionVehicle = fuelConsumptionVehicle;
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

    public Integer getIsCurrentUserMachine() {
        return isCurrentUserMachine;
    }

    public void setIsCurrentUserMachine(Integer isCurrentUserMachine) {
        this.isCurrentUserMachine = isCurrentUserMachine;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public Set<Role> getRolesUser() {
        return rolesUser;
    }

    public void setRolesUser(Set<Role> rolesUser) {
        this.rolesUser = rolesUser;
    }

    public Date getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(Date registeredUser) {
        this.registeredUser = registeredUser;
    }

    public boolean isEnabledUser() {
        return enabledUser;
    }

    public void setEnabledUser(boolean enabledUser) {
        this.enabledUser = enabledUser;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getNameCarVehicle() {
        return nameCarVehicle;
    }

    public void setNameCarVehicle(String nameCarVehicle) {
        this.nameCarVehicle = nameCarVehicle;
    }

    public String getVehicleNumberVehicle() {
        return vehicleNumberVehicle;
    }

    public void setVehicleNumberVehicle(String vehicleNumberVehicle) {
        this.vehicleNumberVehicle = vehicleNumberVehicle;
    }

    public Integer getYearIssueVehicle() {
        return yearIssueVehicle;
    }

    public void setYearIssueVehicle(Integer yearIssueVehicle) {
        this.yearIssueVehicle = yearIssueVehicle;
    }

    public String getCategoryVehicle() {
        return categoryVehicle;
    }

    public void setCategoryVehicle(String categoryVehicle) {
        this.categoryVehicle = categoryVehicle;
    }

    public String getColorVehicle() {
        return colorVehicle;
    }

    public void setColorVehicle(String colorVehicle) {
        this.colorVehicle = colorVehicle;
    }

    public Integer getFuelConsumptionVehicle() {
        return fuelConsumptionVehicle;
    }

    public void setFuelConsumptionVehicle(Integer fuelConsumptionVehicle) {
        this.fuelConsumptionVehicle = fuelConsumptionVehicle;
    }

    @Override
    public String toString() {
        return "UserVehicleFull{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", isCurrentUserMachine=" + isCurrentUserMachine +
                ", idUser=" + idUser +
                ", nameUser='" + nameUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", passwordUser='" + passwordUser + '\'' +
                ", phoneUser='" + phoneUser + '\'' +
                ", addressUser='" + addressUser + '\'' +
                ", rolesUser=" + rolesUser +
                ", registeredUser=" + registeredUser +
                ", enabledUser=" + enabledUser +
                ", idVehicle=" + idVehicle +
                ", nameCarVehicle='" + nameCarVehicle + '\'' +
                ", vehicleNumberVehicle='" + vehicleNumberVehicle + '\'' +
                ", yearIssueVehicle=" + yearIssueVehicle +
                ", categoryVehicle='" + categoryVehicle + '\'' +
                ", colorVehicle='" + colorVehicle + '\'' +
                ", fuelConsumptionVehicle=" + fuelConsumptionVehicle +
                ", id=" + id +
                '}';
    }
}
