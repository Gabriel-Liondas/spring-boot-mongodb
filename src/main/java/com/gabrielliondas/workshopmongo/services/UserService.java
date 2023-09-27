package com.gabrielliondas.workshopmongo.services;

import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.exception.ObjectNotFoundException;
import com.gabrielliondas.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }


    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }
}
