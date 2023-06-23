package com.springBootProject2.Ecommerce.Repository;

import com.springBootProject2.Ecommerce.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
