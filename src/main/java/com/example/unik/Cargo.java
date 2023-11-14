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

  public void setCargoName(String cargo_name) {
    this.cargo_name = cargo_name;
  }

  public String getCargoName() {
    return cargo_name;
  }

  public void setCargoContent(String cargo_content) {
    this.cargo_content = cargo_content;
  }

  public String getCargoContent() {
    return cargo_content; 
  }

  public void setShipmentCity(String shipment_city) {
    this.shipment_city = shipment_city;
  }

  public String getShipmentCity() {
    return shipment_city; 
  }

  public void setShipmentDate(LocalDate shipment_city) {
    this.shipment_date = shipment_date;
  }

  public LocalDate getShipmentDate() {
    return shipment_date; 
  }

  public void setDeliveryCity(String delivery_city) {
    this.delivery_city = delivery_city;
  }

  public String getDeliveryCity(String delivery_city) {
    return delivery_city; 
  }

  public void setDeliveryDate(LocalDate delivery_date) {
    this.delivery_date = delivery_date;
  }
}
