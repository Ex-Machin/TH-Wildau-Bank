package com.dmytro.customer;

import com.dmytro.authorities.Authority;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private String username;
    private String password;
    private Boolean enabled;
    private double money;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    public Customer(Integer id, String name, String email, Integer age, String username, String password, Boolean enabled, double money) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.money = money;
    }

    public Customer() {}

    public Integer getId() {return id;}
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public Integer getAge() {
        return age;
    }

    public double getMoney() {
        return money;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public void setEnabled(Boolean enabled) {this.enabled = enabled;}
    public void setMoney(double money) {this.money = money;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id)
                && Objects.equals(name, customer.name)
                && Objects.equals(email, customer.email)
                && Objects.equals(age, customer.age)
                && Objects.equals(username, customer.username)
                && Objects.equals(password, customer.password)
                && Objects.equals(enabled, customer.enabled)
                && Objects.equals(money, customer.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, email, age, enabled, money);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled='" + password + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
