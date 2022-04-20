package com.yangshop.yangbum.repository;

import com.yangshop.yangbum.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
