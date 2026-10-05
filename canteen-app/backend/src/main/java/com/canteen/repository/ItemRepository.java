package com.canteen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.canteen.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {}
