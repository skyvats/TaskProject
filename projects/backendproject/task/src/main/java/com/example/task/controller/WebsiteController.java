package com.example.task.controller;

import com.example.task.Website;
import com.example.task.service.EntityService;
import com.example.task.service.WebsiteService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @RequestMapping("/addWebsite")
    public String addWebsite(@RequestParam("websiteDetails") String websiteDetails){
        System.out.println("Invoked addWebsite => "+ websiteDetails);

        Gson gson = new Gson();
        Website website = gson.fromJson(websiteDetails, Website.class);

        try{
            websiteService.addWebsite(website);
            return "SUCCESS";
        }
        catch (Exception e){
            System.out.println("Failed to add User => "+ e);
        }
        return "FAILURE";
    }

    @RequestMapping("/getAllWebsites")
    public List<Map<String, String>> getAllWebsites(){
        System.out.println("Invoked getAllWebsites");

        List<Map<String, String>> webSiteStatusList = new ArrayList<>();

        try{
            List<Website> allWebsites = websiteService.getAllWebsites();

            if(allWebsites == null){
                return new ArrayList<>();
            }

            for(Website website : allWebsites){
                System.out.println("Website $$$$ => "+ website);
                Map<String, String> webSiteStatus = new HashMap<>();
                String formatedURL = formatUrl(website.getWebsiteName());
                int status = checkWebsiteStatus(formatedURL);
                webSiteStatus.put("website",website.getWebsiteName());
                if(status == 200){
                    webSiteStatus.put("status", "SUCCESS");
                }
                else{
                    webSiteStatus.put("status", "FAILURE");
                }
                webSiteStatusList.add(webSiteStatus);
            }

            return webSiteStatusList;
        }
        catch (Exception e){
            System.out.println("Failed to add User => "+ e);
        }
        return new ArrayList<>();
    }

    private static String formatUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            return "http://" + url;
        }
        return url;
    }

    public static int checkWebsiteStatus(String url) throws IOException {
        URL websiteUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) websiteUrl.openConnection();
        connection.setRequestMethod("GET");

        return connection.getResponseCode();
    }
}
