package com.sjarno.springangularcrud.repository;

import java.util.Optional;

import com.sjarno.springangularcrud.models.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
    Optional<UserAccount> findByUsername(String username);
}
