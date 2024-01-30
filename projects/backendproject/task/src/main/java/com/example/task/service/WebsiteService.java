package com.example.task.service;

import com.example.task.Website;
import com.example.task.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    public void addWebsite(Website website){
        System.out.println("Invoked addWebsite =====> "+ website);
        websiteRepository.save(website);
    }

    public List<Website> getAllWebsites(){
        System.out.println("Invoked getAllWebsites");
        List<Website> websiteList = websiteRepository.findAll();
        return websiteList;
    }
}
