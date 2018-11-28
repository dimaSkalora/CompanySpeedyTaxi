package com.taxi.speedy.company.model;

//Тип банковськой карты
public class TypeBankCard extends AbstractBaseEntity{
    private String typeName;                        //Название типа (зарплатная, водителькая, заправная и т.д.)

    public TypeBankCard() {
    }

    public TypeBankCard(String typeName) {
        this.typeName = typeName;
    }

    public TypeBankCard(Integer id, String typeName) {
        super(id);
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "TypeBankCard{" +
                "typeName='" + typeName + '\'' +
                ", id=" + id +
                '}';
    }
}
