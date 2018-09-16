package com.gustavodiasb.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gustavodiasb.workshopmongo.domain.Post;
import com.gustavodiasb.workshopmongo.resources.util.URL;
import com.gustavodiasb.workshopmongo.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
		
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	// ou @GetMapping
	public ResponseEntity<Post> findById(@PathVariable String id) { //@PathVariable -> deixa a URL com "/"
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
	// ou @GetMapping
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) { // @RequestParam -> deixa a URL com "?"
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
	// ou @GetMapping
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text, // @RequestParam -> deixa a URL com "?"
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {			
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L)); // caso surgir problemas para gerar a senha, o Date(0L) gerará a data mínima do Java (01/01/1970)
		Date max = URL.convertDate(maxDate, new Date()); // new Date() nesse caso gera a data atual do sistema
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
}
