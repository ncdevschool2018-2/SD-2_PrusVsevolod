package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "ba_id")
    private BillingAccount ba;

    public Owner(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Owner() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getuser() {
        return user;
    }

    public void setuser(User user) {
        this.user = user;
    }

    public BillingAccount getba() {
        return ba;
    }

    public void setba(BillingAccount ba) {
        this.ba = ba;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id &&
                Objects.equals(name, owner.name) &&
                Objects.equals(user, owner.user) &&
                Objects.equals(ba, owner.ba);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user, ba);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", ba=" + ba +
                '}';
    }
}
