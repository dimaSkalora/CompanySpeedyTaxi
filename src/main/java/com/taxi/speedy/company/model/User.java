package com.taxi.speedy.company.model;

import com.taxi.speedy.company.HashId;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class User implements HashId {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Set<Role> roles;
    private Date registered;        //Дата регистраиции пользователя
    private boolean enabled;        //true - активный, false - не активный

    public User() {
    }

    public User(Integer id, String name, String email, String password, String phone, String address, Set<Role> roles, Date registered, boolean enabled) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.roles = roles;
        this.registered = registered;
        this.enabled=enabled;
    }

    public User(Integer id, String name, String email, String password, String phone, String address, Role role, Role ... roles) {
        this(id, name, email, password, phone,address, EnumSet.of(role, roles), new Date(), true);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", roles=" + roles +
                ", registered=" + registered +
                ", enabled=" + enabled +
                ", id=" + id +
                '}';
    }
}