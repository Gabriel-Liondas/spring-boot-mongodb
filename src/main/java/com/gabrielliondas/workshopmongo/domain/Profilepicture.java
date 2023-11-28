package com.gabrielliondas.workshopmongo.domain;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Document(collection = "profilePictures")
public class Profilepicture implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Binary image;

    public String getId() {
        return id;
    }

    public Profilepicture(String id, Binary image) {
        this.id = id;
        this.image = image;
    }

    public Profilepicture() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary imageData) {
        this.image = imageData;
    }

    @Override
    public boolean equals(Object objectComp) {
        if (this == objectComp) return true;
        if (objectComp == null || getClass() != objectComp.getClass()) return false;
        Profilepicture pfpic = (Profilepicture) objectComp;
        return Objects.equals(id, pfpic.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
