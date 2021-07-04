package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.Map;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    void save(Candidate can);

    Post findByIdPost(int id);

    Candidate findByIdCandidate(int id);

    void deleteCandidate(int id);

    void createUser(User user);

    User findByEmailUser(String email);

    int findCityId(String city);

    Map<Integer, String> findCity();
}