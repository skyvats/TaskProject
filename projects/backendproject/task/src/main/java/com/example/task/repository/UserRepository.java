package com.example.task.repository;

import com.example.task.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public interface UserRepository extends JpaRepository<UserDetail,Long> {

    /*@PersistenceContext
    private EntityManager entityManager;

    public void saveUser(User user){
        entityManager.persist(user);
    }*/
}
