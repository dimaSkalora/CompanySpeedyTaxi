package com.taxi.speedy.company.model;

//Роботчий статус такси
public class TaxiJobStatus extends AbstractBaseEntity {
    private String name_tjs;                            //Название статуса (1-свободный, 2-занятый, 3-на обеде)

    public TaxiJobStatus() {
    }

    public TaxiJobStatus(String name_tjs) {
        this.name_tjs = name_tjs;
    }

    public TaxiJobStatus(Integer id, String name_tjs) {
        super(id);
        this.name_tjs = name_tjs;
    }

    public String getName_tjs() {
        return name_tjs;
    }

    public void setName_tjs(String name_tjs) {
        this.name_tjs = name_tjs;
    }

    @Override
    public String toString() {
        return "TaxiJobStatus{" +
                "name_tjs='" + name_tjs + '\'' +
                ", id=" + id +
                '}';
    }
}
