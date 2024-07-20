package com.tr.simpleerp.repository;

import com.tr.simpleerp.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

}
