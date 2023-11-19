package com.example.unik;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Entity
public class Cargo{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String cargo_name;
  private String cargo_content;
  private String shipment_city;
  private LocalDate shipment_date;
  private String delivery_city;
  private LocalDate delivery_date;

  protected Cargo(){
  }

  public Long getId(){
    return id;
  };

  public void setCargo_name(String cargo_name) {
    this.cargo_name = cargo_name;
  }

  public String getCargo_name() {
    return cargo_name;
  }

  public void setCargo_content(String cargo_content) {
    this.cargo_content = cargo_content;
  }

  public String getCargo_content() {
    return cargo_content; 
  }

  public void setShipment_city(String shipment_city) {
    this.shipment_city = shipment_city;
  }

  public String getShipment_city() {
    return shipment_city; 
  }

  public void setShipment_date(LocalDate shipment_city) {
    this.shipment_date = shipment_date;
  }

  public LocalDate getShipment_date() {
    return shipment_date; 
  }

  public void setDelivery_city(String delivery_city) {
    this.delivery_city = delivery_city;
  }

  public String getDelivery_city() {
    return delivery_city; 
  }

  public void setDelivery_date(LocalDate delivery_date) {
    this.delivery_date = delivery_date;
  }

  public LocalDate getDelivery_date() {
    return delivery_date; 
  }
}
