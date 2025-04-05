package com.company.medicalappointmentsystem.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.NumberFormat;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "MEDICINE")
@Entity
public class Medicine {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;
    @Column(name = "PHOTO", length = 1024)
    private FileRef photo;
    @InstanceName
    @Column(name = "NAME")
    private String name;
    @NumberFormat(pattern = "#,### VNĐ")
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "DOSAGE")
    private String dosage;
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;
    @Column(name = "ORIGIN_BRAND")
    private String originBrand;
    @Column(name = "COUNTRY_OF_PRODUCTION")
    private String countryOfProduction;

    @Column(name = "REGISTRATION_NUMBER")
    private String registrationNumber;

    public FileRef getPhoto() {
        return photo;
    }

    public void setPhoto(FileRef photo) {
        this.photo = photo;
    }

    public String getCountryOfProduction() {
        return countryOfProduction;
    }

    public void setCountryOfProduction(String countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOriginBrand() {
        return originBrand;
    }

    public void setOriginBrand(String originBrand) {
        this.originBrand = originBrand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}