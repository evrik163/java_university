package com.example.unik;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    private CargoService service;

    @Autowired
    private PostsService posts_service;

    private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    @RequestMapping(path = "/")
    public String viewHomePage(Model model, @Param("keyword") String keyword,
                               @Param("sorting") String sorting, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return "redirect:/auth";
        }

        List<Cargo> listCargo = service.listAll(keyword, sorting);
        model.addAttribute("listCargo", listCargo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("count", service.getCount(keyword));
        return "main";
    }

    @RequestMapping(path = "/new")
    public String showNewCargoForm(Model model, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return "redirect:/auth";
        }

        Cargo cargo = new Cargo();
        model.addAttribute("cargo", cargo);
        return "new_cargo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCargo(@ModelAttribute("cargo") Cargo cargo, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return "redirect:/auth";
        }

        service.save(cargo);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCargoForm(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return new ModelAndView("redirect:/auth");
        }

        ModelAndView mav = new ModelAndView("edit_cargo");
        Cargo cargo = service.get(id);
        mav.addObject("cargo", cargo);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCargo(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return "redirect:/auth";
        }

        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String getChart(@Param("keyword") String keyword, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return "Access Denied";
        }

        List<String> listDates = service.getDate(keyword);
        List<String> listDelDates = service.getDelDate(keyword);
        Map<String, Integer> dict = new HashMap<>();

        for (int i = 0; i < listDates.size(); i++) {
            String val = listDates.get(i) + "/" + listDelDates.get(i);
            dict.put(val, dict.getOrDefault(val, 0) + 1);
        }

        return new Gson().toJson(dict);
    }

    @RequestMapping(path = "/posts")
    public String viewPostsPage(Model model, @Param("keyword") String keyword) {
        List<HashMap<String, String>> listPosts = posts_service.get_by_name(keyword);
        model.addAttribute("listPosts", listPosts);
        model.addAttribute("keyword", keyword);
        return "posts";
    }
  }