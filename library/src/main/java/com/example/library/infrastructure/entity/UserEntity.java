package com.example.library.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users", schema = "library")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "name")
    private String name;



    @Basic
    @Column(name = "lastName")
    private String lastName;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<LoanEntity> loans;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}