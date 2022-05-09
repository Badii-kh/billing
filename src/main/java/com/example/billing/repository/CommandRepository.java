package com.example.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.billing.entity.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long>{

}
