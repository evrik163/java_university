package com.example.unik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {

      @Autowired
      private CargoService service;

      @RequestMapping(path = "/")
      public String viewHomePage(Model model, @Param("keyword") String keyword) {
          List<Cargo> listCargo = service.listAll(keyword);
          model.addAttribute("listCargo", listCargo);
          model.addAttribute("keyword", keyword);
          return "main";
      }

      @RequestMapping(path = "/new")
      public String showNewCargoForm(Model model){
            Cargo cargo = new Cargo();
            model.addAttribute("cargo", cargo);
            return "new_cargo";
      }

      @RequestMapping(value = "/save", method=RequestMethod.POST)
      public String saveCargo(@ModelAttribute("cargo") Cargo cargo){
        service.save(cargo);
        return "redirect:/";
      }

      @RequestMapping("/edit/{id}")
      public ModelAndView showEditCargoForm(@PathVariable(name="id") Long id) {
       ModelAndView nav = new ModelAndView("edit_cargo");
       Cargo cargo = service.get(id);
       nav.addObject("cargo", cargo);
       return nav;
      }

      @RequestMapping("/delete/{id}")
      public String deleteCargo(@PathVariable(name="id") Long id) {
      service.delete(id);
      return "redirect:/";
      }
  }
