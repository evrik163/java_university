package com.example.unik;

import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
  @Autowired
  private CargoRepository repo;

  public List<Cargo> listAll(String keyword, String sorting){
    if (keyword != null && sorting != null) {
          return repo.getOrdered(keyword);
    }
    else if (keyword != null && sorting == null){
          return repo.search(keyword);
      }
    else if (keyword == null && sorting != null){
          return repo.getAllOrdered(); 
    }
      return repo.findAll();
  }

  public List<Cargo> listSorted(String keyword){
      if (keyword != null){
          return repo.getOrdered(keyword);
      }
      return repo.getAllOrdered();
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

  public List<String> getDate(String keyword){
    if (keyword != null){
      return repo.getFilteredDate(keyword);
    }
    return repo.getDate();
  }

  public long getCount(String keyword){
      if (keyword != null){
          return repo.getCount(keyword);
      }
    return repo.getAllCount();
  }

  public List<String> getDelDate(String keyword){
    if (keyword != null){
      return repo.getFilteredDelDate(keyword);
    }
    return repo.getDelDate();
  }
}
