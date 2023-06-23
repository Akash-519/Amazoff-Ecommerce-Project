package com.springBootProject2.Ecommerce.Repository;

import com.springBootProject2.Ecommerce.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
