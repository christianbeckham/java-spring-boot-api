package com.dcc.jpa_stream_lab.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.jpa_stream_lab.repository.ProductsRepository;
import com.dcc.jpa_stream_lab.repository.RolesRepository;
import com.dcc.jpa_stream_lab.repository.ShoppingcartItemRepository;
import com.dcc.jpa_stream_lab.repository.UsersRepository;
import com.dcc.jpa_stream_lab.models.Product;
import com.dcc.jpa_stream_lab.models.Role;
import com.dcc.jpa_stream_lab.models.ShoppingcartItem;
import com.dcc.jpa_stream_lab.models.User;

@Transactional
@Service
public class StreamLabService {
	
	@Autowired
	private ProductsRepository products;
	@Autowired
	private RolesRepository roles;
	@Autowired
	private UsersRepository users;
	@Autowired
	private ShoppingcartItemRepository shoppingcartitems;


    // <><><><><><><><> R Actions (Read) <><><><><><><><><>

    public List<User> RDemoOne() {
    	// This query will return all the users from the User table.
    	return users.findAll().stream().toList();
    }

    public long RProblemOne()
    {
        // Return the COUNT of all the users from the User table.
        // You MUST use a .stream(), don't listen to the squiggle here!
        // Remember yellow squiggles are warnings and can be ignored.
    	return users.findAll().stream().count();
    }

    public List<Product> RDemoTwo()
    {
        // This query will get each product whose price is greater than $150.
    	return products.findAll().stream().filter(p -> p.getPrice() > 150).toList();
    }

    public List<Product> RProblemTwo()
    {
        // Write a query that gets each product whose price is less than or equal to $100.
        // Return the list
        return products.findAll().stream().filter(p -> p.getPrice() <= 100).toList();
    }

    public List<Product> RProblemThree()
    {
        // Write a query that gets each product that CONTAINS an "s" in the products name.
        // Return the list
    	return products.findAll().stream().filter(p -> p.getName().contains("s")).toList();
    }

    public List<User> RProblemFour() throws ParseException {
        // Write a query that gets all the users who registered BEFORE 2016
        // Return the list
        // Research 'java create specific date' and 'java compare dates'
        // You may need to use the helper classes imported above!
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date year = yearFormat.parse("2016");
        return users.findAll().stream().filter(u -> u.getRegistrationDate().before(year)).toList();
    }

    public List<User> RProblemFive() throws ParseException {
        // Write a query that gets all of the users who registered AFTER 2016 and BEFORE 2018
        // Return the list
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date yearBefore = yearFormat.parse("2018");
        Date yearAfter = yearFormat.parse("2016");

        return users.findAll().stream().filter(u -> u.getRegistrationDate().before(yearBefore) && u.getRegistrationDate().after(yearAfter)).toList();
    }

    // <><><><><><><><> R Actions (Read) with Foreign Keys <><><><><><><><><>

    public List<User> RDemoThree()
    {
        // Write a query that retrieves all of the users who are assigned to the role of Customer.
    	Role customerRole = roles.findAll().stream().filter(r -> r.getName().equals("Customer")).findFirst().orElse(null);
    	List<User> customers = users.findAll().stream().filter(u -> u.getRoles().contains(customerRole)).toList();

    	return customers;
    }

    public List<Product> RProblemSix()
    {
        // Write a query that retrieves all of the products in the shopping cart of the user who has the email "afton@gmail.com".
        // Return the list
        User user = users.findAll().stream().filter(u -> u.getEmail().equals("afton@gmail.com")).findFirst().orElse(null);
        List<Product> items = shoppingcartitems.findAll().stream().filter(i -> i.getUser().getId() == user.getId()).map(p -> p.getProduct()).toList();
    	return items;
    }

    public long RProblemSeven()
    {
        // Write a query that retrieves all of the products in the shopping cart of the user who has the email "oda@gmail.com" and returns the sum of all of the products prices.
    	// Remember to break the problem down and take it one step at a time!
        List<ShoppingcartItem> items = shoppingcartitems.findAll().stream().filter(i -> i.getUser().getEmail().equals("oda@gmail.com")).toList();
        Integer total = items.stream().reduce(0, (acc, p) -> acc + p.getProduct().getPrice(), Integer::sum);
    	return total;

    }

    public List<Product> RProblemEight()
    {
        // Write a query that retrieves all of the products in the shopping cart of users who have the role of "Employee".
    	// Return the list
        Role employeeRole = roles.findAll().stream().filter(r -> r.getName().equals("Employee")).findFirst().orElse(null);
        List<Product> items = shoppingcartitems.findAll().stream().filter(i -> i.getUser().getRoles().contains(employeeRole)).map(p -> p.getProduct()).toList();
    	return items;
    }

    // <><><><><><><><> CUD (Create, Update, Delete) Actions <><><><><><><><><>

    // <><> C Actions (Create) <><>

    public User CDemoOne()
    {
        // Create a new User object and add that user to the Users table.
        User newUser = new User();        
        newUser.setEmail("david@gmail.com");
        newUser.setPassword("DavidsPass123");
        users.save(newUser);
        return newUser;
    }

    public Product CProblemOne()
    {
        // Create a new Product object and add that product to the Products table.
        // Return the product
        Product newProduct = new Product();
        newProduct.setName("AirPods");
        newProduct.setDescription("Wireless ear buds made by Apple.");
        newProduct.setPrice(200);
        products.save(newProduct);
    	return newProduct;
    }

    public List<Role> CDemoTwo()
    {
        // Add the role of "Customer" to the user we just created in the UserRoles junction table.
    	Role customerRole = roles.findAll().stream().filter(r -> r.getName().equals("Customer")).findFirst().orElse(null);
    	User david = users.findAll().stream().filter(u -> u.getEmail().equals("david@gmail.com")).findFirst().orElse(null);
    	david.addRole(customerRole);
    	return david.getRoles();
    }

    public ShoppingcartItem CProblemTwo()
    {
    	// Create a new ShoppingCartItem to represent the new product you created being added to the new User you created's shopping cart.
        // Add the product you created to the user we created in the ShoppingCart junction table.
        // Return the ShoppingcartItem
        User newUser = users.findAll().stream().filter(u -> u.getEmail().equals("david@gmail.com")).findFirst().orElse(null);
        Product newProduct = products.findAll().stream().filter(p -> p.getName().equals("AirPods")).findFirst().orElse(null);

        ShoppingcartItem newCartItem = new ShoppingcartItem();
        newCartItem.setProduct(newProduct);
        newCartItem.setUser(newUser);
        newCartItem.setQuantity(1);
        shoppingcartitems.save(newCartItem);
    	return newCartItem;
    }

    // <><> U Actions (Update) <><>

    public User UDemoOne()
    {
         //Update the email of the user we created in problem 11 to "mike@gmail.com"
          User user = users.findAll().stream().filter(u -> u.getEmail().equals("david@gmail.com")).findFirst().orElse(null);
          user.setEmail("mike@gmail.com");
          return user;
    }

    public Product UProblemOne()
    {
        // Update the price of the product you created to a different value.
        // Return the updated product
        Product productToUpdate = products.findAll().stream().filter(p -> p.getName().equals("AirPods")).findFirst().orElse(null);
        productToUpdate.setPrice(150);
    	return productToUpdate;
    }

    public User UProblemTwo()
    {
        // Change the role of the user we created to "Employee"
        // HINT: You need to delete the existing role relationship and then create a new UserRole object and add it to the UserRoles table
        User user = users.findAll().stream().filter(u -> u.getId() == 6).findFirst().orElse(null);
        Role customerRole = roles.findAll().stream().filter(r -> r.getName().equals("Customer")).findFirst().orElse(null);
        Boolean containsRole = user.getRoles().contains(customerRole);

        if (containsRole) {
            user.removeRole(customerRole);
            Role employeeRole = roles.findAll().stream().filter(r -> r.getName().equals("Employee")).findFirst().orElse(null);
            user.addRole(employeeRole);
        }

    	return user;
    }

    //BONUS:
    // <><> D Actions (Delete) <><>

    // For these bonus problems, you will also need to create their associated routes in the Controller file!
    
    // DProblemOne
    // Delete the role relationship from the user who has the email "oda@gmail.com".
    public List<Role> DProblemOne() {
        User user = users.findAll().stream().filter(u -> u.getEmail().equals("oda@gmail.com")).findFirst().orElse(null);

        roles.findAll().removeIf(role -> {
            if (user.getRoles().contains(role)) {
                user.removeRole(role);
            }
            return false;
        });

        return user.getRoles();
    }

    // DProblemTwo
    // Delete all the product relationships to the user with the email "oda@gmail.com" in the ShoppingCart table.
    public List<ShoppingcartItem> DProblemTwo() {
        User user = users.findAll().stream().filter(u -> u.getEmail().equals("oda@gmail.com")).findFirst().orElse(null);

        shoppingcartitems.findAll().removeIf(item -> {
            if (user.getShoppingcartItems().contains(item)) {
                user.removeShoppingcartItem(item);
            }
            return false;
        });

        return user.getShoppingcartItems();
    }

    // DProblemThree
    // Delete the user with the email "oda@gmail.com" from the Users table.
    public List<User> DProblemThree() {
        String userEmail = "oda@gmail.com";
        User user = users.findAll().stream().filter(u -> u.getEmail().equals(userEmail)).findFirst().orElse(null);

        if (user != null) {
            users.deleteById(user.getId());
        }

        return users.findAll().stream().filter(u -> u.getEmail().equals(userEmail)).toList();
    }

}
