package com.taxi.speedy.company.model;

//Роботчий статус такси
public class TaxiJobStatus extends AbstractBaseEntity {
    private String nameTJS;                            //Название статуса (1-свободный, 2-занятый, 3-на обеде)

    public TaxiJobStatus() {
    }

    public TaxiJobStatus(String nameTJS) {
        this.nameTJS = nameTJS;
    }

    public TaxiJobStatus(Integer id, String nameTJS) {
        super(id);
        this.nameTJS = nameTJS;
    }

    public String getNameTJS() {
        return nameTJS;
    }

    public void setNameTJS(String nameTJS) {
        this.nameTJS = nameTJS;
    }

    @Override
    public String toString() {
        return "TaxiJobStatus{" +
                "nameTJS='" + nameTJS + '\'' +
                ", id=" + id +
                '}';
    }
}
