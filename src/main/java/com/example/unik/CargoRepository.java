package com.example.unik;

import java.util.List;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
  @Query("SELECT p FROM Cargo p WHERE CONCAT(p.cargo_name, '', p.cargo_content, '', p.shipment_city, '', p.shipment_date, '', p.delivery_city, '', p.delivery_date) LIKE %?1%") List<Cargo> search(String keyword);
  @Query("SELECT p.shipment_date FROM Cargo p") List<String> getDate();
  @Query("SELECT p.delivery_date FROM Cargo p") List<String> getDelDate();
  @Query("SELECT COUNT(p) FROM Cargo p WHERE CONCAT(p.cargo_name, '', p.cargo_content, '', p.shipment_city, '', p.shipment_date, '', p.delivery_city, '', p.delivery_date) LIKE %?1%") Long getCount(String keyword);
  @Query("SELECT COUNT(p) FROM Cargo p") Long getAllCount();
  @Query("SELECT p FROM Cargo p WHERE CONCAT(p.cargo_name, '', p.cargo_content, '', p.shipment_city, '', p.shipment_date, '', p.delivery_city, '', p.delivery_date) LIKE %?1% ORDER BY p.delivery_date ASC") List<Cargo> getOrdered(String keyword);
  @Query("SELECT p FROM Cargo p ORDER BY p.delivery_date ASC") List<Cargo> getAllOrdered();
  }

