package com.taxi.speedy.company.model;

//Расчетный лист
public class PaySheet extends AbstractBaseEntity {
    private User idUser;                                //Юзер
    private BankCard idBankCard;                        //Банковськая карта юзера
    private DepartmentCompany idDepartmentCompany;      //Название отдела
    private String monthYear;                           //За какой год и месяц начисляется заробатная плата
    private Double prepayment;                          //Аванс
    private Double salary;                              //Зарплата
    private Double premium;                             //Премия
    private Double hospitals;                           //Больничные
    private Double holiday;                             //Отпускные
    private Double amountWithoutTaxes;                  //Начислена сумма без налогов
    private Double ndfl;                                //Подоходный налог с зарплаты, или налог на доходы физических лиц (НДФЛ) установлен в размере 18-20%.
    private Double esv;                                 //Единый социальный взнос (ЕСВ). Ставка составляет от 22% до 41% в зависимости от категорий плательщиков и классов риска вида деятельности.
    private Double vs;                                  //Военный сбор – 1,5%.
    private Double amountWithTexas;                     //Начислена сумма с налогами
    private Integer normHoursWorked;                    //Норма отработаных часов
    private Integer hoursWorked;                        //Отработаны часы'
    private Integer hours_holiday;                      //Сколько часов был на больничном

    public PaySheet() {
    }

    public PaySheet(User idUser, BankCard idBankCard, DepartmentCompany idDepartmentCompany, String monthYear, Double prepayment, Double salary, Double premium, Double hospitals, Double holiday, Double amountWithoutTaxes, Double ndfl, Double esv, Double vs, Double amountWithTexas, Integer normHoursWorked, Integer hoursWorked, Integer hours_holiday) {
        this.idUser = idUser;
        this.idBankCard = idBankCard;
        this.idDepartmentCompany = idDepartmentCompany;
        this.monthYear = monthYear;
        this.prepayment = prepayment;
        this.salary = salary;
        this.premium = premium;
        this.hospitals = hospitals;
        this.holiday = holiday;
        this.amountWithoutTaxes = amountWithoutTaxes;
        this.ndfl = ndfl;
        this.esv = esv;
        this.vs = vs;
        this.amountWithTexas = amountWithTexas;
        this.normHoursWorked = normHoursWorked;
        this.hoursWorked = hoursWorked;
        this.hours_holiday = hours_holiday;
    }

    public PaySheet(Integer id, User idUser, BankCard idBankCard, DepartmentCompany idDepartmentCompany, String monthYear, Double prepayment, Double salary, Double premium, Double hospitals, Double holiday, Double amountWithoutTaxes, Double ndfl, Double esv, Double vs, Double amountWithTexas, Integer normHoursWorked, Integer hoursWorked, Integer hours_holiday) {
        super(id);
        this.idUser = idUser;
        this.idBankCard = idBankCard;
        this.idDepartmentCompany = idDepartmentCompany;
        this.monthYear = monthYear;
        this.prepayment = prepayment;
        this.salary = salary;
        this.premium = premium;
        this.hospitals = hospitals;
        this.holiday = holiday;
        this.amountWithoutTaxes = amountWithoutTaxes;
        this.ndfl = ndfl;
        this.esv = esv;
        this.vs = vs;
        this.amountWithTexas = amountWithTexas;
        this.normHoursWorked = normHoursWorked;
        this.hoursWorked = hoursWorked;
        this.hours_holiday = hours_holiday;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public BankCard getIdBankCard() {
        return idBankCard;
    }

    public void setIdBankCard(BankCard idBankCard) {
        this.idBankCard = idBankCard;
    }

    public DepartmentCompany getIdDepartmentCompany() {
        return idDepartmentCompany;
    }

    public void setIdDepartmentCompany(DepartmentCompany idDepartmentCompany) {
        this.idDepartmentCompany = idDepartmentCompany;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public Double getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Double prepayment) {
        this.prepayment = prepayment;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Double getHospitals() {
        return hospitals;
    }

    public void setHospitals(Double hospitals) {
        this.hospitals = hospitals;
    }

    public Double getHoliday() {
        return holiday;
    }

    public void setHoliday(Double holiday) {
        this.holiday = holiday;
    }

    public Double getAmountWithoutTaxes() {
        return amountWithoutTaxes;
    }

    public void setAmountWithoutTaxes(Double amountWithoutTaxes) {
        this.amountWithoutTaxes = amountWithoutTaxes;
    }

    public Double getNdfl() {
        return ndfl;
    }

    public void setNdfl(Double ndfl) {
        this.ndfl = ndfl;
    }

    public Double getEsv() {
        return esv;
    }

    public void setEsv(Double esv) {
        this.esv = esv;
    }

    public Double getVs() {
        return vs;
    }

    public void setVs(Double vs) {
        this.vs = vs;
    }

    public Double getAmountWithTexas() {
        return amountWithTexas;
    }

    public void setAmountWithTexas(Double amountWithTexas) {
        this.amountWithTexas = amountWithTexas;
    }

    public Integer getNormHoursWorked() {
        return normHoursWorked;
    }

    public void setNormHoursWorked(Integer normHoursWorked) {
        this.normHoursWorked = normHoursWorked;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Integer getHours_holiday() {
        return hours_holiday;
    }

    public void setHours_holiday(Integer hours_holiday) {
        this.hours_holiday = hours_holiday;
    }

    @Override
    public String toString() {
        return "PaySheet{" +
                "idUser=" + idUser +
                ", idBankCard=" + idBankCard +
                ", idDepartmentCompany=" + idDepartmentCompany +
                ", monthYear='" + monthYear + '\'' +
                ", prepayment=" + prepayment +
                ", salary=" + salary +
                ", premium=" + premium +
                ", hospitals=" + hospitals +
                ", holiday=" + holiday +
                ", amountWithoutTaxes=" + amountWithoutTaxes +
                ", ndfl=" + ndfl +
                ", esv=" + esv +
                ", vs=" + vs +
                ", amountWithTexas=" + amountWithTexas +
                ", normHoursWorked=" + normHoursWorked +
                ", hoursWorked=" + hoursWorked +
                ", hours_holiday=" + hours_holiday +
                ", id=" + id +
                '}';
    }
}