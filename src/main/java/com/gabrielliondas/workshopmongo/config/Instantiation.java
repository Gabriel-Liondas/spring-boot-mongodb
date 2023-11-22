package com.gabrielliondas.workshopmongo.config;

import com.gabrielliondas.workshopmongo.domain.Post;
import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.dto.AuthorDTO;
import com.gabrielliondas.workshopmongo.dto.CommentDTO;
import com.gabrielliondas.workshopmongo.repository.PostRepository;
import com.gabrielliondas.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("20/03/2018"), "Partiu viagem", "Vou viajar para São Paulo, abraços!", new AuthorDTO(maria));
        Post post3 = new Post(null, sdf.parse("20/02/2018"), "Meu aniversário", "Vou fazer uma festa!", new AuthorDTO(bob));
        Post post2 = new Post(null, sdf.parse("20/04/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem! Aproveita bastante", sdf.parse("22/03/2018"), new AuthorDTO(bob));
        CommentDTO c2 = new CommentDTO("Boa viagem!", sdf.parse("22/04/2018"), new AuthorDTO(alex));
        CommentDTO c3 = new CommentDTO("Tá atrasado Alex 🤣, ela já voltou, isso que dá não ver o celular hehe", sdf.parse("22/04/2018"), new AuthorDTO(bob));
        CommentDTO c4 = new CommentDTO("Bom dia Maria!", sdf.parse("22/04/2018"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2, c3));
        post2.getComments().add(c4);

        postRepository.saveAll(Arrays.asList(post1, post3, post2));

        maria.setPosts(Arrays.asList(post1, post2));
        bob.setPosts(List.of(post3));
        userRepository.save(maria);
        userRepository.save(bob);
    }
}
