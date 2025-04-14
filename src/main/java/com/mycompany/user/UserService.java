package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired private  UserRepository repo;
    
    public Page<User> listAll (int page, int size, String keyword){
    	Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
    	        
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll(pageable);
        } else {
            return repo.findByUserLike(keyword,pageable);
        }

    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("could not find any users with id:"+id);
    }

    public void delete(Integer id) throws UserNotFoundException{
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new UserNotFoundException("could not find any users with id:"+id);
        }
        repo.deleteById(id);
    }
    
}
