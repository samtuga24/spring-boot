package com.samtuga.schoolmanagement.repository;

import com.samtuga.schoolmanagement.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Optional <Admin> findAllAdminByLastName(String lastName);
//    query did not return a unique result: 3
    Optional<Admin> findById(long id);
    Optional<Admin> findByEmail(String email);

}

