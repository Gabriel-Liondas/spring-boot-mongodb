package com.gabrielliondas.workshopmongo.services;

import com.gabrielliondas.workshopmongo.domain.Post;
import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.exception.NonValidObjectException;
import com.gabrielliondas.workshopmongo.exception.ObjectNotFoundException;
import com.gabrielliondas.workshopmongo.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    @Autowired
    private UserPostRelatedDataSevice relatedDataSevice;

    public List<String> findAllSortedbyDate() {
        List<Post> findAllPost = repo.findAll();
        findAllPost.sort(Comparator.comparing(Post::getDate).reversed());
        return findAllPost.stream().map(Post::getId).toList();
    }

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repo.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repo.fullSearch(text, minDate, maxDate);
    }

    public Post insert(Post obj) {
        relatedDataSevice.comparePostAuthor(obj);
        return repo.insert(obj);
    }
    // eu preciso trocar pra ao invés dele dar um catch eu fazer a pesquisa nesse
    // próprio serviço e ai sim jogar a exessão, ou fazer o esquema do GPT
    // e colocar em um serviço separado, que provavelmente é a melhor prática
}
