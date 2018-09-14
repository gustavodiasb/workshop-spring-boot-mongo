package com.gustavodiasb.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gustavodiasb.workshopmongo.domain.User;
import com.gustavodiasb.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	// ou @GetMapping
	public ResponseEntity<List<User>> findAll() {		
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
