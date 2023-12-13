package com.example.unik;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ResponseBody;
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
      public String viewHomePage(Model model, @Param("keyword") String keyword,
          @Param("sorting") String sorting) {
          List<Cargo> listCargo = service.listAll(keyword, sorting);
          model.addAttribute("listCargo", listCargo);
          model.addAttribute("keyword", keyword);
          model.addAttribute("count", service.getCount(keyword));
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

      @RequestMapping(value = "/chart", method=RequestMethod.GET, produces="text/plain")
      @ResponseBody
      public String getChart(@Param("keyword") String keyword, @Param("sorting") String sorting) {
        System.out.println(keyword);
        System.out.println(sorting);
        List<String> listDates = service.getDate();
        List<String> listDelDates = service.getDelDate();
        Map<String, Integer> dict = new HashMap <String, Integer> ();

        for (int i = 0; i < listDates.size(); i++){
          String val = listDates.get(i) + "/" + listDelDates.get(i);
          boolean containsKey = dict.containsKey(val);
          if (containsKey){
            dict.put(val, dict.get(val) + 1);
             }
          else {
            dict.put(val, 1);
            }
          }

        Gson gson = new Gson(); 
        String json = gson.toJson(dict);

        return json;
      }
  }
