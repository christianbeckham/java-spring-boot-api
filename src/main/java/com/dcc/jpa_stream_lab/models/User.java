package com.dcc.jpa_stream_lab.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String password;

	@Column(name="registration_date")
	private Date registrationDate = new Date();

	//bi-directional many-to-one association to ShoppingcartItem
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<ShoppingcartItem> shoppingcartItems;

	//bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="user_roles"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<ShoppingcartItem> getShoppingcartItems() {
		return this.shoppingcartItems;
	}

	public void setShoppingcartItems(List<ShoppingcartItem> shoppingcartItems) {
		this.shoppingcartItems = shoppingcartItems;
	}

	public ShoppingcartItem addShoppingcartItem(ShoppingcartItem shoppingcartItem) {
		shoppingcartItem.setUser(this);
		getShoppingcartItems().add(shoppingcartItem);

		return shoppingcartItem;
	}

	public ShoppingcartItem removeShoppingcartItem(ShoppingcartItem shoppingcartItem) {
		//Note: This method will remove the connection between the user and the shoppingcartitem, but it will NOT delete the shoppingcartitem from the database.
		getShoppingcartItems().remove(shoppingcartItem);
		shoppingcartItem.setUser(null);

		return shoppingcartItem;
	}

	public List<Role> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.addUser(this);
		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.removeUser(this);
		return role;
	}

}