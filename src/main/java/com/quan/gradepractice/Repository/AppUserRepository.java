package com.quan.gradepractice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quan.gradepractice.Entity.AppUser;

@Repository

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    
    @Query(
        value = "select * from users where user_name = ?1",
        nativeQuery = true
    )
    Optional<AppUser> findByUserName(String userName);

   
}
