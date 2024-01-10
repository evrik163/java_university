package com.example.unik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Posts get_by_id(Long id) {
        return repo.findById(id).get();
    }

    public void save(Posts post) {
        repo.save(post);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
