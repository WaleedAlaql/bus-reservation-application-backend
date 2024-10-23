package com.waleed.BusReservation.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.waleed.BusReservation.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMobileOrEmail(String mobile, String email);

    Optional<Customer> findByMobile(String mobile);

    Optional<Customer> findByEmail(String email);

    Boolean existsByMobile(String mobile);

    Boolean existsByEmail(String email);

    Boolean existsByMobileOrEmail(String mobile, String email);
}
