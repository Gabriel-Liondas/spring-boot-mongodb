package com.gabrielliondas.workshopmongo.config;

import com.gabrielliondas.workshopmongo.domain.Post;
import com.gabrielliondas.workshopmongo.domain.Profilepicture;
import com.gabrielliondas.workshopmongo.domain.User;
import com.gabrielliondas.workshopmongo.dto.AuthorDTO;
import com.gabrielliondas.workshopmongo.dto.CommentDTO;
import com.gabrielliondas.workshopmongo.repository.ImageRepository;
import com.gabrielliondas.workshopmongo.repository.PostRepository;
import com.gabrielliondas.workshopmongo.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import java.nio.file.Files;
import java.io.File;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        return baos.toByteArray();
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();
        imageRepository.deleteAll();

        BufferedImage mariaImage = ImageIO.read(new File("C:\\Users\\gabriel.liondas\\Documents\\Curso Java\\APIProject\\workshopmongo\\src\\main\\java\\com\\gabrielliondas\\workshopmongo\\config\\instantiationImages\\mariaBrown.jpg"));
        BufferedImage alexImage = ImageIO.read(new File("C:\\Users\\gabriel.liondas\\Documents\\Curso Java\\APIProject\\workshopmongo\\src\\main\\java\\com\\gabrielliondas\\workshopmongo\\config\\instantiationImages\\alexGreen.jpg"));
        BufferedImage bobImage = ImageIO.read(new File("C:\\Users\\gabriel.liondas\\Documents\\Curso Java\\APIProject\\workshopmongo\\src\\main\\java\\com\\gabrielliondas\\workshopmongo\\config\\instantiationImages\\bobGrey.jpg"));


        Profilepicture mariaImg = new Profilepicture(null, new Binary(BsonBinarySubType.BINARY, toByteArray(mariaImage, "jpg")));
        Profilepicture alexImg = new Profilepicture(null, new Binary(BsonBinarySubType.BINARY, toByteArray(alexImage, "jpg")));
        Profilepicture bobImg = new Profilepicture(null, new Binary(BsonBinarySubType.BINARY, toByteArray(bobImage, "jpg")));
        imageRepository.saveAll(Arrays.asList(mariaImg, alexImg, bobImg));


        User maria = new User(null, "Maria Brown", "maria@gmail.com", mariaImg.getId());
        User alex = new User(null, "Alex Green", "alex@gmail.com", alexImg.getId());
        User bob = new User(null, "Bob Grey", "bob@gmail.com", bobImg.getId());

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
