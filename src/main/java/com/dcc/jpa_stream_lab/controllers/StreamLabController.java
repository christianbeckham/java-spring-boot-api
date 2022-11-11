package com.dcc.jpa_stream_lab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcc.jpa_stream_lab.service.StreamLabService;
import com.dcc.jpa_stream_lab.models.Product;
import com.dcc.jpa_stream_lab.models.Role;
import com.dcc.jpa_stream_lab.models.ShoppingcartItem;
import com.dcc.jpa_stream_lab.models.User;

@RestController
public class StreamLabController {

	@Autowired
	public StreamLabService service;

    // <><><><><><><><> R Actions (Read) <><><><><><><><><>
    @GetMapping("/RDemoOne")
    public List<User> RDemoOne() {
    	return service.RDemoOne();
    }
	
    @GetMapping("/RProblemOne")
    public long RProblemOne() {
    	return service.RProblemOne();
    }
    
    @GetMapping("/RDemoTwo")
    public List<Product> RDemoTwo() {
    	return service.RDemoTwo();
    }
    
    @GetMapping("/RProblemTwo")
    public List<Product> RProblemTwo() {
    	return service.RProblemTwo();
    }

    @GetMapping("/RProblemThree")
    public List<Product> RProblemThree() {
        return service.RProblemThree();
    }

    @GetMapping("/RProblemFour")
    public List<User> RProblemFour() {
    	return service.RProblemFour();
    }
    
    @GetMapping("/RProblemFive")
    public List<User> RProblemFive() {
    	return service.RProblemFive();
    }

    // <><><><><><><><> R Actions (Read) with Foreign Keys <><><><><><><><><>
    @GetMapping("/RDemoThree")
    public List<User> RDemoThree() {
    	return service.RDemoThree();
    }
    
    @GetMapping("/RProblemSix")
    public List<Product> RProblemSix() {
    	return service.RProblemSix();
    }
    
    @GetMapping("/RProblemSeven")
    public long RProblemSeven() {
    	return service.RProblemSeven();
    }
    
    @GetMapping("/RProblemEight")
    public List<Product> RProblemEight() {
    	return service.RProblemEight();
    }

    // <><><><><><><><> CUD (Create, Update, Delete) Actions <><><><><><><><><>

    // <><> C Actions (Create) <><>

    @PostMapping("/CDemoOne")
    public User PostDemoOne() {
    	return service.CDemoOne();
    }
    
    @PostMapping("/CProblemOne")
    public Product CProblemOne() {
    	return service.CProblemOne();
    }
    
    @PostMapping("/CDemoTwo")
    public List<Role> CDemoTwo() {
    	return service.CDemoTwo();
    }
    
    @PostMapping("/CProblemTwo")
    public ShoppingcartItem CProblemTwo() {
    	return service.CProblemTwo();
    }

    // <><> U Actions (Update) <><>
    
    @PutMapping("/UDemoOne")
    public User UDemoOne() {
    	return service.UDemoOne();
    }
    
    @PutMapping("/UProblemOne")
    public Product UProblemOne() {
    	return service.UProblemOne();
    }
    
    @PutMapping("/UProblemTwo")
    public User UProblemTwo() {
    	return service.UProblemTwo();
    }

    //BONUS:
    // <><> D Actions (Delete) <><> (Add Below)
    

    
}
