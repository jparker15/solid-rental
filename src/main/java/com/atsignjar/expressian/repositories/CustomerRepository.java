package com.atsignjar.expressian.repositories;

import com.atsignjar.expressian.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
