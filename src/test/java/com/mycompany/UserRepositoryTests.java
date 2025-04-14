package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest  //in order to use spring data jpa we need to use the annotation
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  //in order to run the test cases against the real database we need to use this annotation
@Rollback(value = false)//  we want to keep the data commited to the db
public class UserRepositoryTests {

    @Autowired 
    private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("alex.wood@gmail.com");
        user.setPassword("alex13");
        user.setFirstName("alex13");
        user.setLastName("wood");

       User savedUser= repo.save(user); //calling this method to proceed this user object into db

    }

    @Test
    public  void testListAll(){
        Iterable<User> users = repo.findAll();

        for(User user : users){
            System.out.println(user.toString());
        }

    }

    @Test
    public void testUpdateUser(){
         Integer userId= 1;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("johndeer");
        repo.save(user);

        User updatedUser = repo.findById(userId).get();

    }

    @Test
    public void testGet(){
        Integer userId =9;
        Optional<User> optionalUser = repo.findById(userId);

        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId =1;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);

    }
}
