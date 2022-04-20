package com.yangshop.yangbum.repository;

import com.yangshop.yangbum.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
