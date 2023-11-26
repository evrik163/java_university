package com.example.unik;

import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
  @Autowired
  private CargoRepository repo;

  public List<Cargo> listAll(String keyword){
      if (keyword != null){
          return repo.search(keyword);
      }
      return repo.findAll();
  }

  public void save(Cargo cargo) {
    repo.save(cargo); 
  }

  public Cargo get(Long id) {
    return repo.findById(id).get(); 
  }

  public void delete(Long id) {
    repo.deleteById(id);
  }
  public List<Date> getDate(){
    return repo.getDate();
  }
}
