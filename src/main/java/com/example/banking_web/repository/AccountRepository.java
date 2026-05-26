package com.example.banking_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.banking_web.entity.account;

public interface AccountRepository extends JpaRepository<account, Long>{

}
