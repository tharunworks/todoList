package com.to.todolist.repositories;

import com.to.todolist.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

    List<User> findAllByUserEmail(String userEmail);

    User findByUserEmailAndPassWord(String userEmail, String password);

    User findByUserId(String userId);
}
