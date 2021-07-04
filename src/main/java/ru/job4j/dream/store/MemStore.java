package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {

    private static final MemStore INST = new MemStore();
    private static AtomicInteger postId = new AtomicInteger(4);
    private static AtomicInteger candidateId = new AtomicInteger(4);
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    public MemStore() {
        posts.put(1, new Post(1, "Junior Java Job", "first level", new Date()));
        posts.put(2, new Post(2, "Middle Java Job", "second level", new Date()));
        posts.put(3, new Post(3, "Senior Java Job", "third level", new Date()));
        candidates.put(1, new Candidate(1, "Junior Java", "first level", new Date()));
        candidates.put(2, new Candidate(2, "Middle Java", "second level", new Date()));
        candidates.put(3, new Candidate(3, "Senior Java", "third level", new Date()));
    }

    public static MemStore instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public Post findByIdPost(int id) {
        return posts.get(id);
    }

    public Candidate findByIdCandidate(int id) {
        return candidates.get(id);
    }

    @Override
    public void deleteCandidate(int id) {
    }

    @Override
    public void createUser(User user) {
    }

    @Override
    public User findByEmailUser(String email) {
        return null;
    }

    @Override
    public int findCityId(String city) {
        return 0;
    }

    @Override
    public Map<Integer, String> findCity() {
        return null;
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(postId.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(candidateId.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }
}
