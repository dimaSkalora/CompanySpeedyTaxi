package com.taxi.speedy.company.model;

import com.taxi.speedy.company.HasId;

public abstract class AbstractBaseEntity implements HasId {

    protected Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }
}