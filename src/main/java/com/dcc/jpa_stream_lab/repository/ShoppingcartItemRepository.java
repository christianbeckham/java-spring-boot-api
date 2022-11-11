package com.dcc.jpa_stream_lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcc.jpa_stream_lab.models.ShoppingcartItem;
//import com.dcc.stream_test.models.ShoppingcartItemPK;


public interface ShoppingcartItemRepository extends JpaRepository<ShoppingcartItem, Integer> {

}
