package com.example.task.service;

import com.example.task.UserDetail;
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

    public void adduser(UserDetail newuser){
        System.out.println("Invoked adduser =====> "+ newuser);
        userRepository.save(newuser);
    }

    public void deleteUser(long userId){
        System.out.println("Invoked deleteUser + "+ userId);
        userRepository.deleteById(userId);
    }

    public void updateUserById(UserDetail updatedUser) {
        System.out.println("Invoked updateUserById + "+ updatedUser);
        long userID = updatedUser.getId();
        UserDetail existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userID));

        System.out.println("Existing User Details =====> "+ existingUser);

        existingUser.setName(updatedUser.getName());
        existingUser.setMobile(updatedUser.getMobile());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        userRepository.save(existingUser);
    }

}
