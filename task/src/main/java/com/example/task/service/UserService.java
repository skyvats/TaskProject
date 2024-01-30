package com.example.task.service;

import com.example.task.User;
import com.example.task.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public void adduser(User newuser){
        System.out.println("Invoked adduser =====> "+ newuser);
        /*User user = new User();
        user.setName("Akash");
        user.setMobile("7272727272");
        user.setEmail("abc@gmail.com");*/
        userRepository.save(newuser);
    }

    public void deleteUser(long userId){
        System.out.println("Invoked deleteUser + "+ userId);
        userRepository.deleteById(userId);
    }

    public void updateUserById(User updatedUser) {
        System.out.println("Invoked updateUserById + "+ updatedUser);
        long userID = updatedUser.getId();
        User existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userID));

        System.out.println("Existing User Details =====> "+ existingUser);

        existingUser.setName(updatedUser.getName());
        existingUser.setMobile(updatedUser.getMobile());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        userRepository.save(existingUser);
    }

}
