package com.example.unik;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repo;

    public List<HashMap<String, String>> get_dict_by_name(String keyword){
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
            String postText = post.getPost_text();
            List<String> authors = new ArrayList<>();
            List<Users> owners = new ArrayList<>();
            owners.addAll(post.getOwners());
            for (int j = 0; j < owners.size(); j++){
                authors.add(owners.get(j).getUsername());
            }
            newPost.put("number", String.valueOf(i+1));
            newPost.put("id", postId);
            newPost.put("topic", postTopic);
            newPost.put("post_text", postText);
            newPost.put("authors", String.join(", ", authors));
            newListPosts.add(newPost);
        }
        return newListPosts;

    }

    public HashMap<String, String> get_dict_by_id(Long post_id){
        Optional<Posts> post = repo.findById(post_id);
        HashMap<String, String> newPost = new HashMap<>();
        String postId = post.get().getId().toString();
        String postTopic = post.get().getTopic();
        String postText = post.get().getPost_text();
        List<String> authors = new ArrayList<>();
        List<Users> owners = new ArrayList<>();
        owners.addAll(post.get().getOwners());
        for (int j = 0; j < owners.size(); j++){
            authors.add(owners.get(j).getUsername());
        }
        newPost.put("id", postId);
        newPost.put("topic", postTopic);
        newPost.put("post_text", postText);
        newPost.put("authors", String.join(", ", authors));
        return newPost;

    }

    public void save(Posts post, List<Users> users) {
        if (!users.isEmpty()) {
            post.getOwners().clear();
            for (int i = 0; i < users.size(); i++) {
                post.getOwners().add(users.get(i));
            }
        }
        repo.save(post);

    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Posts get_by_id(Long id) {
        return repo.findById(id).get();
    }

    public boolean check_owner_access(Posts post, Users user) {
        if (
            !user.getRole().equals("Viewer") && post.getOwners().contains(user) ||
            user.getRole().equals("Admin"))
        {
            return true;
        }
        return false;
    }
}
