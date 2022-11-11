package com.dcc.jpa_stream_lab.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	private String name;

	private Integer price;

	//bi-directional many-to-one association to ShoppingcartItem
	@JsonIgnore
	@OneToMany(mappedBy="product")
	private List<ShoppingcartItem> shoppingcartItems;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<ShoppingcartItem> getShoppingcartItems() {
		return this.shoppingcartItems;
	}

	public void setShoppingcartItems(List<ShoppingcartItem> shoppingcartItems) {
		this.shoppingcartItems = shoppingcartItems;
	}

	public ShoppingcartItem addShoppingcartItem(ShoppingcartItem shoppingcartItem) {
		getShoppingcartItems().add(shoppingcartItem);
		shoppingcartItem.setProduct(this);

		return shoppingcartItem;
	}

	public ShoppingcartItem removeShoppingcartItem(ShoppingcartItem shoppingcartItem) {
		getShoppingcartItems().remove(shoppingcartItem);
		shoppingcartItem.setProduct(null);

		return shoppingcartItem;
	}

}