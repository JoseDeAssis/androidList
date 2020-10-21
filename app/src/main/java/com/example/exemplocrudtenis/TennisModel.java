package com.example.exemplocrudtenis;

public class TennisModel {
    private String model;
    private int id;
    private double price;

    public TennisModel(String modelo, int id, double preco) {
        this.model = modelo;
        this.id = id;
        this.price = preco;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
