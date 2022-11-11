package com.dcc.jpa_stream_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcc.jpa_stream_lab.models.Product;


public interface ProductsRepository extends JpaRepository<Product, Integer> {

}