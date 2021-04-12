package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import java.util.Date;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();

        store.save(new Post(0, "Java Job", "create", new Date()));

        store.save(new Post(1, " Update Java Job", "update", new Date()));

        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName() + " "
                    + post.getDescription() + " " + post.getCreated());
        }

        store.save(new Candidate(0, "Java Job", "create", new Date()));

        store.save(new Candidate(1, " Update Java Job", "update", new Date()));

        for (Candidate can : store.findAllCandidates()) {
            System.out.println(can.getId() + " " + can.getName() + " "
                    + can.getDescription() + " " + can.getCreated());
        }
    }
}