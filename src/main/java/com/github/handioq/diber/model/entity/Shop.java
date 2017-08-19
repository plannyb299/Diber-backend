package com.github.handioq.diber.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.handioq.diber.model.base.BaseEntity;
import com.github.handioq.diber.model.dto.ShopDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Order> orders;

    public Shop() {
    }

    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }

    public static Shop toEntity(ShopDto shopDto) {
        return new Shop(shopDto.getName(), shopDto.getAddress());
    }

}
