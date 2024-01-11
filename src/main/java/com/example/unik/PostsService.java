package com.example.unik;

import java.util.*;

import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repo;

    public List<HashMap<String, String>> get_by_name(String keyword){
        if (keyword == null) {
            keyword = "";
        }
        List<Posts> listPosts = repo.search_by_name(keyword);
        List<HashMap<String, String>> newListPosts = new ArrayList<>();
        for (int i = 0; i < listPosts.size(); i++){
            HashMap<String, String> newPost = new HashMap<>();
            Posts post = listPosts.get(i);
            String postId = post.getId().toString();
            String postTopic = post.getTopic();
            List<String> authors = new ArrayList<>();
            List<Users> owners = new ArrayList<>();
            owners.addAll(post.getOwners());
            for (int j = 0; j < owners.size(); j++){
                authors.add(owners.get(j).getUsername());
            }
            newPost.put("id", postId);
            newPost.put("topic", postTopic);
            newPost.put("authors", String.join(", ", authors));
            newListPosts.add(newPost);
        }
        return newListPosts;

    }

    public void save(Posts post, Users user) {
        post.getOwners().add(user);
        repo.save(post);

    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public boolean check_owner_access(Posts post, Users user) {
        if (
            user.getRole() != "Viewer" && post.getOwners().contains(user) ||
            user.getRole() == "Admin")
        {
            return true;
        }
        return false;
    }
}