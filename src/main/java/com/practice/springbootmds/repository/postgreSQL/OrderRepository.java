package com.practice.springbootmds.repository.postgreSQL;

import com.practice.springbootmds.entities.postgreSQL.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
