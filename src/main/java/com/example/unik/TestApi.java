package com.example.unik;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestApi {
      @GetMapping(path = "/test")
       public String getTest() {
          return "HURAAAAA";
   } 
}
