package com.dmytro.authorities;

import com.dmytro.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username; // This should match the username in your Customer entity
    private String authority;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Authority(Integer id, String username, String authority) {
        this.id = id;
        this.username = username;
        this.authority = authority;
    }


    public Authority() {}


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Integer getId() {return id;}

    public String getUsername() {
        return username;
    }
    public String getAuthority() {
        return authority;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(id, authority.id)
                && Objects.equals(username, authority.username)
                && Objects.equals(username, authority.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, authority);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", authority=" + authority +
                '}';
    }
}