package com.storeApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.storeApp.models.phone.Phone;
import jakarta.persistence.*;

@Entity
@Table(name = "phone_rom")
public class PhoneRom {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rom_size")
    private Short romSize;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "phone_id",referencedColumnName = "id")
    private Phone phone;

    public PhoneRom(){}

    public PhoneRom(Long id, Short romSize, Integer price, Phone phone) {
        this.id = id;
        this.romSize = romSize;
        this.price = price;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getRomSize() {
        return romSize;
    }

    public void setRomSize(Short romSize) {
        this.romSize = romSize;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}