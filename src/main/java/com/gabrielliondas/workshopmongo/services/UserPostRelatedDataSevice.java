package com.gabrielliondas.workshopmongo.services;

import com.gabrielliondas.workshopmongo.domain.Post;
import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.dto.CommentDTO;
import com.gabrielliondas.workshopmongo.exception.NonValidObjectException;
import com.gabrielliondas.workshopmongo.exception.ObjectNotFoundException;
import com.gabrielliondas.workshopmongo.repository.PostRepository;
import com.gabrielliondas.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserPostRelatedDataSevice {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public void comparePostAuthor(Post postObj) {
        Optional<User> userObj = userRepository.findById(postObj.getAuthor().getId());
        if (userObj.isEmpty() || !Objects.equals(userObj.get().getName(), postObj.getAuthor().getName())) {
            throw new NonValidObjectException("Usuário autor inválido");
        }
        for (CommentDTO comment : postObj.getComments()) {
            compareCommentAuthor(comment);
        }
        //        findAllPost.sort(Comparator.comparing(Post::getDate).reversed());
    }

    public void compareCommentAuthor(CommentDTO commentObj) {
        Optional<User> userObj = userRepository.findById(commentObj.getAuthor().getId());
        if (userObj.isEmpty() || !Objects.equals(userObj.get().getName(), commentObj.getAuthor().getName())) {
            throw new NonValidObjectException("Usuário autor inválido em comentário");
        }
    }
}
