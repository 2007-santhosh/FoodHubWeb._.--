package com.canteen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.canteen.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
