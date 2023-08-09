package com.example.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Account> accounts;

    public User() {
        accounts = new HashSet<>();
    }

    public User(String username, int age) {
        this();
        this.username = username;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Long> getAccountIds() {
        return this.accounts
                .stream()
                .map(Account::getId)
                .collect(Collectors.toList());
    }
}
