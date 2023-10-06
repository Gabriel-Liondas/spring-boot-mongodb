package com.gabrielliondas.workshopmongo.resources;

import com.gabrielliondas.workshopmongo.domain.Post;
import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.dto.UserDTO;
import com.gabrielliondas.workshopmongo.services.PostService;
import com.gabrielliondas.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService service;

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<Post>> findAll(){
//        List<User> list = service.findAll();
//        List<UserDTO> listDto = list.stream().map(UserDTO::new).toList();
//        return ResponseEntity.ok().body(listDto);
//    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

}
