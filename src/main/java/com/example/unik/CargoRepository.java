package com.example.unik;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
  @Query("SELECT p FROM Cargo p WHERE CONCAT(p.cargo_name, '', p.cargo_content, '', p.shipment_city, '', p.shipment_date, '', p.delivery_city, '', p.delivery_date) LIKE %?1%") List<Cargo> search(String keyword); }
