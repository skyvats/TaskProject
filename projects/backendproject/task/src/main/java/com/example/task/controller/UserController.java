package com.example.task.controller;

import com.example.task.User;
import com.example.task.service.EntityService;
import com.example.task.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    public String addUser(@RequestParam("userDetails") String userDetails){
        System.out.println("Invoked Request Mapping => "+ userDetails);

        Gson gson = new Gson();
        User user = gson.fromJson(userDetails, User.class);

        try{

            /*boolean checkValidPhone = checkValidPhoneNumber(user.getMobile());
            if(checkValidPhone){
                return "INVALID PHONE NUMBER";
            }*/

            userService.adduser(user);
            return "SUCCESS";
        }
        catch (Exception e){
            System.out.println("Failed to add User => "+ e);
        }
        return "FAILURE";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userDetails") String userDetails){
        System.out.println("Invoked deleteUser => "+ userDetails);

        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(userDetails, Map.class);
        System.out.println("Map%%%%% => "+ map);
        String  userId = (String) map.get("userId");

        try{
            userService.deleteUser(Long.parseLong(userId));
            return "SUCCESS";
        }
        catch (Exception e){
            System.out.println("Failed to add User => "+ e);
        }
        return "FAILURE";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("userDetails") String userDetails){
        System.out.println("Invoked updateUser => "+ userDetails);

        Gson gson = new Gson();
        User updatedUser = gson.fromJson(userDetails, User.class);

        try{
            userService.updateUserById(updatedUser);
            return "SUCCESS";
        }
        catch (Exception e){
            System.out.println("Failed to add User => "+ e);
        }
        return "FAILURE";
    }

}
