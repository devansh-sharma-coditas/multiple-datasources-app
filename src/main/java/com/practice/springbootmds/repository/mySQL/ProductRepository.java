package com.practice.springbootmds.repository.mySQL;

import com.practice.springbootmds.entities.mySQL.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
