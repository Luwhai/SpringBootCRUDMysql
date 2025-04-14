package com.mycompany.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Integer > {


    public Long countById(Integer id);
    
    // 分页模糊查询（方法名自动生成）
    //Page<User> findByFirstnameContaining(String keyword, Pageable pageable);
    
   // 或自定义 JPQL
    @Query(value = "SELECT * FROM users t WHERE LOWER(t.first_name) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
    Page<User> findByUserLike(String title, Pageable pageable);
}
