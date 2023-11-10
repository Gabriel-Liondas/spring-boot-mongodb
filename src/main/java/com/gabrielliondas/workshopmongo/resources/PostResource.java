package com.gabrielliondas.workshopmongo.resources;

import com.gabrielliondas.workshopmongo.domain.Post;
import com.gabrielliondas.workshopmongo.resources.util.URL;
import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.dto.UserDTO;
import com.gabrielliondas.workshopmongo.services.PostService;
import com.gabrielliondas.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findAll() {
        HttpHeaders allowLocalHostHeaders = new HttpHeaders();
        allowLocalHostHeaders.add("Access-Control-Allow-Origin", "http://localhost:3000");
        allowLocalHostHeaders.add("Access-Control-Allow-Methods", "GET");
        allowLocalHostHeaders.add("Access-Control-Allow-Headers", "Content-Type, Authorization");

        List<Post> list = service.findAllSorted();
        return ResponseEntity.ok().headers(allowLocalHostHeaders).body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
    ) {
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());

        List<Post> list = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }


}
