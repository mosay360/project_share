package com.adminpanel.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class Roof {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String category;
    private String format;
    private  double listPrice;
    private  double ourPrice;
    private  boolean active= true;

    @Column(columnDefinition = "text")
    private  String description;
    private  int inStockNumber;

    @Transient
    private MultipartFile roofImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInStockNumber() {
        return inStockNumber;
    }

    public void setInStockNumber(int inStockNumber) {
        this.inStockNumber = inStockNumber;
    }

    public MultipartFile getRoofImage() {
        return roofImage;
    }

    public void setRoofImage(MultipartFile roofImage) {
        this.roofImage = roofImage;
    }
}
