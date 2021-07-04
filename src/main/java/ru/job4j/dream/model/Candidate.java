package ru.job4j.dream.model;

import java.util.Date;
import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private String description;
    private Date created;
    private int cityId;
    private boolean hasPhoto;

    public Candidate(int id, String name, String description, Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }
    public Candidate(int id, String name, String description, Date created, int cityId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCityId() {
        return cityId;
    }

    public boolean isHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(boolean value) {
        this.hasPhoto = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && Objects.equals(name, candidate.name)
                && Objects.equals(description, candidate.description)
                && Objects.equals(created, candidate.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }
}