package com.gabrielliondas.workshopmongo.services;

import com.gabrielliondas.workshopmongo.domain.Profilepicture;
import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.dto.UserDTO;
import com.gabrielliondas.workshopmongo.exception.ObjectNotFoundException;
import com.gabrielliondas.workshopmongo.repository.ImageRepository;
import com.gabrielliondas.workshopmongo.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository photoRepo;

    public String addProfilepicture(MultipartFile file) throws IOException {
        Profilepicture photo = new Profilepicture();
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
        return photo.getId();
    }

    public Profilepicture getProfilepicture(String id) {
        Optional<Profilepicture> obj = photoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Imagem não encontrada"));
    }
}
