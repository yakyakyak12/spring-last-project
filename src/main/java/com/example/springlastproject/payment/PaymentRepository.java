package com.example.springlastproject.payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByUserId(Integer userId);

    List<Payment> findAllByUserId(Integer userId);

}
