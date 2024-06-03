package com.tcs.training.userms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "dob")
    private String dob;

    @Column(name = "address")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void updateUser(User updatedData) {
        if (updatedData.firstName != null || updatedData.firstName != "")
            this.firstName = updatedData.firstName;
        if (updatedData.lastName != null || updatedData.lastName != "")
            this.lastName = updatedData.lastName;
        if (updatedData.phone != null || updatedData.phone != "")
            this.phone = updatedData.phone;
        if (updatedData.address != null || updatedData.address != "")
            this.address = updatedData.address;
        if (updatedData.email != null || updatedData.email != "")
            this.email = updatedData.email;
        if (updatedData.dob != null || updatedData.dob != "")
            this.dob = updatedData.dob;
    }
}
