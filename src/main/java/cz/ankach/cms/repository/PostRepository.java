package cz.ankach.cms.repository;

import cz.ankach.cms.entity.Post;
import cz.ankach.cms.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class PostRepository {
    private final HashMap<Long, Post> storage;

    public PostRepository() {
        this.storage = new HashMap<>();
    }

    public void addPost(Post post) {
        this.storage.put(post.getId(), post);
    }

    public Post getPostById(long id) {
        return this.storage.get(id);
    }

    public List<Post> getPostByUser(User user) {
        Collection<Post> posts = this.storage.values();
        return posts.stream().filter(post -> post.getAuthor().getId() == user.getId()).toList();
    }
}
