package org.hibernate.bugs;

import jakarta.persistence.*;

@Entity
public class ProductWithEmbeddableEntity {
    @Id
    private Long id;

    private String name;

    @Embedded
    private UsersEmbeddable users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UsersEmbeddable getUsers() {
        return users;
    }

    public void setUsers(UsersEmbeddable users) {
        this.users = users;
    }
}
