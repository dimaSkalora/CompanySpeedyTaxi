package com.taxi.speedy.company.model;

//Отделы компании
public class DepartmentCompany extends AbstractBaseEntity {
    private String nameDC;                                  //Название отдела

    public DepartmentCompany() {
    }

    public DepartmentCompany(String nameDC) {
        this.nameDC = nameDC;
    }

    public DepartmentCompany(Integer id, String nameDC) {
        super(id);
        this.nameDC = nameDC;
    }

    public String getNameDC() {
        return nameDC;
    }

    public void setNameDC(String nameDC) {
        this.nameDC = nameDC;
    }

    @Override
    public String toString() {
        return "DepartmentCompany{" +
                "nameDC='" + nameDC + '\'' +
                ", id=" + id +
                '}';
    }
}
