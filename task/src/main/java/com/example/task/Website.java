package com.example.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String websiteName;

    public Website() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", websiteName='" + websiteName + '\'' +
                '}';
    }
}
