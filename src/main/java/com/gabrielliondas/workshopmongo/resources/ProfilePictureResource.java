package com.gabrielliondas.workshopmongo.resources;

import com.gabrielliondas.workshopmongo.domain.Profilepicture;
import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.dto.UserDTO;
import com.gabrielliondas.workshopmongo.services.ImageService;
import com.gabrielliondas.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/profilePictures")
public class ProfilePictureResource {
    @Autowired
    private ImageService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addPhoto(@RequestParam("image") MultipartFile image) throws IOException {
        String id = service.addProfilepicture(image);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Profilepicture> findById(@PathVariable String id) {
        Profilepicture obj = service.getProfilepicture(id);
        return ResponseEntity.ok().body(obj);
    }
}
