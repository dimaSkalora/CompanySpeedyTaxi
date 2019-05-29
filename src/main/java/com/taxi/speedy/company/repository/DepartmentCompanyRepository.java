package com.taxi.speedy.company.repository;

import com.taxi.speedy.company.model.DepartmentCompany;

import java.util.List;

public interface DepartmentCompanyRepository {
    DepartmentCompany save(DepartmentCompany departmentCompany);
    boolean delete(int id);
    DepartmentCompany get(int id);
    DepartmentCompany getName(String nameDC);
    List<DepartmentCompany> getAll();
}
