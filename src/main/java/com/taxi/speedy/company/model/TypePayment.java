package com.taxi.speedy.company.model;

//Тип выплаты
public class TypePayment extends AbstractBaseEntity {
    private String nameTp;                          //Название выплаты (аванс, зарплата и т.д.)

    public TypePayment() {
    }

    public TypePayment(String nameTp) {
        this.nameTp = nameTp;
    }

    public TypePayment(Integer id, String nameTp) {
        super(id);
        this.nameTp = nameTp;
    }

    public String getNameTp() {
        return nameTp;
    }

    public void setNameTp(String nameTp) {
        this.nameTp = nameTp;
    }

    @Override
    public String toString() {
        return "TypePayment{" +
                "nameTp='" + nameTp + '\'' +
                ", id=" + id +
                '}';
    }
}
