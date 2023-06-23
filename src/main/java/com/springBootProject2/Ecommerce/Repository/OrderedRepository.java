package com.springBootProject2.Ecommerce.Repository;

import com.springBootProject2.Ecommerce.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
