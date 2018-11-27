package com.taxi.speedy.company.model;

public class UserState extends AbstractBaseEntity{
    private String nameUS;                       //Название состояния (здоровый, больной, и т.д.)

    public UserState() {
    }

    public UserState(Integer id, String nameUS) {
        super(id);
        this.nameUS = nameUS;
    }

    public String getNameUS() {
        return nameUS;
    }

    public void setNameUS(String nameUS) {
        this.nameUS = nameUS;
    }

    @Override
    public String toString() {
        return "UserState{" +
                "nameUS='" + nameUS + '\'' +
                ", id=" + id +
                '}';
    }
}

