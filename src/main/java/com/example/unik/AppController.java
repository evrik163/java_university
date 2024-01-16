package com.example.unik;

import com.google.gson.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class AppController {

    @Autowired
    private CargoService service;

    @Autowired
    private PostsService posts_service;

    @Autowired
    private UsersService users_service;

    private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("user") == null;
    }

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
    public String showNewCargoForm(Model model) {
        Cargo cargo = new Cargo();
        model.addAttribute("cargo", cargo);
        return "new_cargo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCargo(@ModelAttribute("cargo") Cargo cargo) {
        service.save(cargo);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCargoForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_cargo");
        Cargo cargo = service.get(id);
        mav.addObject("cargo", cargo);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCargo(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        service.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET, produces = "text/plain")
    @ResponseBody
    public String getChart(@Param("keyword") String keyword, HttpServletRequest request) {
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
    public String viewPostsPage(
            Model model, @Param("keyword") String keyword, HttpServletRequest request
    ) {
        if (isAuthenticated(request)) {
            return "redirect:/auth";
        }
        Users user = users_service.get_current_user(request);
        List<HashMap<String, String>> listPosts = posts_service.get_dict_by_name(keyword);
        model.addAttribute("listPosts", listPosts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("user", user);
        return "posts";
    }

    @RequestMapping(path = "/new_post")
    public String showNewPostForm(Model model, HttpServletRequest request){
        if (isAuthenticated(request)) {
            return "redirect:/auth";
        }
        if (Objects.equals(users_service.get_current_user(request).getRole(), "Viewer")) {
            return "redirect:/posts";
        }

        Posts posts = new Posts();
        String current_user = users_service.get_current_user(request).getUsername();
        List<String> authors = users_service.get_all_users();
        authors.remove(current_user);
        model.addAttribute("posts", posts);
        model.addAttribute("authors", authors);
        return "new_post";
    }

    @RequestMapping(value = "/save_post", method=RequestMethod.POST)
    public Object savePosts(
            @RequestBody String json_string,
            HttpServletRequest request
    ){
        if (isAuthenticated(request)) {
            return "Access Denied";
        }
        if (Objects.equals(users_service.get_current_user(request).getRole(), "Viewer")) {
            return "Access Denied";
        }
        JsonObject json_body = JsonParser.parseString(json_string).getAsJsonObject();
        String postText = json_body.get("postText").getAsString();
        String topic = json_body.get("topic").getAsString();
        JsonArray authors = json_body.get("users").getAsJsonArray();
        List<String> usernames = new ArrayList<>();
        for (JsonElement authorElement : authors) {
            usernames.add(authorElement.getAsString());
        }
        Posts new_post = new Posts();
        new_post.setCreated_dt(posts_service.get_now_date());
        new_post.setPost_text(postText);
        new_post.setTopic(topic);
        List<Users> users = new ArrayList<>();
        for (int i = 0; i < usernames.size(); i++) {
            users.add(users_service.get_by_name(usernames.get(i)));
        }
        String[] uri = request.getHeader("Referer").split("/");
        String endpoint = uri[uri.length-1];
        if (Objects.equals(endpoint, "new_post")) {
            users.add(users_service.get_current_user(request));
            posts_service.save(new_post, users);
        }
        else {
            String[] post_uri = endpoint.split("_");
            String post_id = post_uri[post_uri.length-1];
            Posts post = posts_service.get_by_id(Long.parseLong(post_id));
            post.setPost_text(postText);
            post.setTopic(topic);
            posts_service.save(post, users);
        }
        return "redirect:/posts";
    }

    @RequestMapping("/post_{id}")
    public String getPost(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request) {
        if (isAuthenticated(request)) {
            return "redirect:/auth";
        }
        HashMap<String, String> post = posts_service.get_dict_by_id(id);
        Users user = users_service.get_current_user(request);
        model.addAttribute("post", post);
        model.addAttribute("user", user);
        return "post_id";
    }

    @RequestMapping(value = "/post_edit_{id}", method = RequestMethod.GET)
    public String showEditPostForm(
            @PathVariable(name = "id") Long id,
            Model model,
            HttpServletRequest request
    ) {
        if (isAuthenticated(request)) {
            return "redirect:/auth";
        }
        if (!posts_service.check_owner_access(
                posts_service.get_by_id(id),
                users_service.get_current_user(request))) {
            return "redirect:/posts";
        }
        HashMap<String, String> post = posts_service.get_dict_by_id(id);
        List<String> users = users_service.get_all_users();
        model.addAttribute("post", post);
        model.addAttribute("users", users);
        return "post_edit";
    }

    @RequestMapping(value = "/post_edit_{id}", method = RequestMethod.POST)
    public String editPost(
            @PathVariable(name = "id") Long id,
            HttpServletRequest request
    ) {
        if (isAuthenticated(request)) {
            return "redirect:/auth";
        }
        if (!posts_service.check_owner_access(
                posts_service.get_by_id(id),
                users_service.get_current_user(request))) {
            return "redirect:/posts";
        }

        return "posts";
    }

    @RequestMapping("post_{id}/delete")
    public String deletePost(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        if (isAuthenticated(request)) {
            return "redirect:/auth";
        }
        if (!posts_service.check_owner_access(
                posts_service.get_by_id(id),
                users_service.get_current_user(request))) {
            return "redirect:/posts";
        }
        posts_service.delete(id);
        return "redirect:/posts";
    }

    @RequestMapping("admin_panel")
    public String adminPanel(HttpServletRequest request, Model model) {
        if (isAuthenticated(request)) {
            return "redirect:/auth";
        }
        if (Objects.equals(users_service.get_current_user(request).getRole(), "Admin")) {
            model.addAttribute("users", users_service.get_all_users_class());
            return "admin_panel";
        }
        return "redirect:/posts";
    }

    @RequestMapping(value = "/save_roles", method=RequestMethod.POST)
    public Object saveRoles(
            @RequestBody String json_string,
            HttpServletRequest request
    ) {
        if (isAuthenticated(request)) {
            return "Access Denied";
        }
        if (!Objects.equals(users_service.get_current_user(request).getRole(), "Admin")) {
            return "Access Denied";
        }
        JsonObject json_body = JsonParser.parseString(json_string).getAsJsonObject();
        for (String username : json_body.keySet()) {
            String role = json_body.get(username).getAsString();
            users_service.update_role(users_service.get_by_name(username), role);
        }
        return "redirect:/posts";
    }
}