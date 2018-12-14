package com.taxi.speedy.company.model;

import java.time.LocalDateTime;


//Учет заработной платы
public class PayrollAccounting extends AbstractBaseEntity {
    private DepartmentCompany idDepartmentCompany;          //Название отдела
    private UserBankCard idUserBankCard;                    //Юзер и его банковськая карта
    private String monthYear;                               //За какой год и месяц начисляется заробатная плата
    private Integer normNumberDaysWorkedMonth;              //Норма отработаных дней
    private Integer numberDaysWorkedMonth;                  //Отработаные дни юзера
    private Double stakeDays;                               //Ставка за день
    private Integer normHoursWorkedMonth;                   //Норма отработаных часов за месяц
    private Integer hoursWorkedMonthl;                      //Отработаные часы за месяц
    private Double stakeHour;                               //Ставка за час
    private LocalDateTime paymentDateTime;                  //Дата и время оплаты
    private TypePayment idTypePayment;                      //Тип оплаты
    private Double payout;                                  //Выплата
    private Double sumPayoutMonth;                          //Всего выплачено за месяц

    public PayrollAccounting() {
    }

    public PayrollAccounting(DepartmentCompany idDepartmentCompany, UserBankCard idUserBankCard, String monthYear, Integer normNumberDaysWorkedMonth, Integer numberDaysWorkedMonth, Double stakeDays, Integer normHoursWorkedMonth, Integer hoursWorkedMonthl, Double stakeHour, LocalDateTime paymentDateTime, TypePayment idTypePayment, Double payout, Double sumPayoutMonth) {
        this.idDepartmentCompany = idDepartmentCompany;
        this.idUserBankCard = idUserBankCard;
        this.monthYear = monthYear;
        this.normNumberDaysWorkedMonth = normNumberDaysWorkedMonth;
        this.numberDaysWorkedMonth = numberDaysWorkedMonth;
        this.stakeDays = stakeDays;
        this.normHoursWorkedMonth = normHoursWorkedMonth;
        this.hoursWorkedMonthl = hoursWorkedMonthl;
        this.stakeHour = stakeHour;
        this.paymentDateTime = paymentDateTime;
        this.idTypePayment = idTypePayment;
        this.payout = payout;
        this.sumPayoutMonth = sumPayoutMonth;
    }

    public PayrollAccounting(Integer id, DepartmentCompany idDepartmentCompany, UserBankCard idUserBankCard, String monthYear, Integer normNumberDaysWorkedMonth, Integer numberDaysWorkedMonth, Double stakeDays, Integer normHoursWorkedMonth, Integer hoursWorkedMonthl, Double stakeHour, LocalDateTime paymentDateTime, TypePayment idTypePayment, Double payout, Double sumPayoutMonth) {
        super(id);
        this.idDepartmentCompany = idDepartmentCompany;
        this.idUserBankCard = idUserBankCard;
        this.monthYear = monthYear;
        this.normNumberDaysWorkedMonth = normNumberDaysWorkedMonth;
        this.numberDaysWorkedMonth = numberDaysWorkedMonth;
        this.stakeDays = stakeDays;
        this.normHoursWorkedMonth = normHoursWorkedMonth;
        this.hoursWorkedMonthl = hoursWorkedMonthl;
        this.stakeHour = stakeHour;
        this.paymentDateTime = paymentDateTime;
        this.idTypePayment = idTypePayment;
        this.payout = payout;
        this.sumPayoutMonth = sumPayoutMonth;
    }

    public DepartmentCompany getIdDepartmentCompany() {
        return idDepartmentCompany;
    }

    public void setIdDepartmentCompany(DepartmentCompany idDepartmentCompany) {
        this.idDepartmentCompany = idDepartmentCompany;
    }

    public UserBankCard getIdUserBankCard() {
        return idUserBankCard;
    }

    public void setIdUserBankCard(UserBankCard idUserBankCard) {
        this.idUserBankCard = idUserBankCard;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public Integer getNormNumberDaysWorkedMonth() {
        return normNumberDaysWorkedMonth;
    }

    public void setNormNumberDaysWorkedMonth(Integer normNumberDaysWorkedMonth) {
        this.normNumberDaysWorkedMonth = normNumberDaysWorkedMonth;
    }

    public Integer getNumberDaysWorkedMonth() {
        return numberDaysWorkedMonth;
    }

    public void setNumberDaysWorkedMonth(Integer numberDaysWorkedMonth) {
        this.numberDaysWorkedMonth = numberDaysWorkedMonth;
    }

    public Double getStakeDays() {
        return stakeDays;
    }

    public void setStakeDays(Double stakeDays) {
        this.stakeDays = stakeDays;
    }

    public Integer getNormHoursWorkedMonth() {
        return normHoursWorkedMonth;
    }

    public void setNormHoursWorkedMonth(Integer normHoursWorkedMonth) {
        this.normHoursWorkedMonth = normHoursWorkedMonth;
    }

    public Integer getHoursWorkedMonthl() {
        return hoursWorkedMonthl;
    }

    public void setHoursWorkedMonthl(Integer hoursWorkedMonthl) {
        this.hoursWorkedMonthl = hoursWorkedMonthl;
    }

    public Double getStakeHour() {
        return stakeHour;
    }

    public void setStakeHour(Double stakeHour) {
        this.stakeHour = stakeHour;
    }

    public LocalDateTime getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(LocalDateTime paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public TypePayment getIdTypePayment() {
        return idTypePayment;
    }

    public void setIdTypePayment(TypePayment idTypePayment) {
        this.idTypePayment = idTypePayment;
    }

    public Double getPayout() {
        return payout;
    }

    public void setPayout(Double payout) {
        this.payout = payout;
    }

    public Double getSumPayoutMonth() {
        return sumPayoutMonth;
    }

    public void setSumPayoutMonth(Double sumPayoutMonth) {
        this.sumPayoutMonth = sumPayoutMonth;
    }

    @Override
    public String toString() {
        return "PayrollAccounting{" +
                "idDepartmentCompany=" + idDepartmentCompany +
                ", idUserBankCard=" + idUserBankCard +
                ", monthYear='" + monthYear + '\'' +
                ", normNumberDaysWorkedMonth=" + normNumberDaysWorkedMonth +
                ", numberDaysWorkedMonth=" + numberDaysWorkedMonth +
                ", stakeDays=" + stakeDays +
                ", normHoursWorkedMonth=" + normHoursWorkedMonth +
                ", hoursWorkedMonthl=" + hoursWorkedMonthl +
                ", stakeHour=" + stakeHour +
                ", paymentDateTime=" + paymentDateTime +
                ", idTypePayment=" + idTypePayment +
                ", payout=" + payout +
                ", sumPayoutMonth=" + sumPayoutMonth +
                ", id=" + id +
                '}';
    }
}
