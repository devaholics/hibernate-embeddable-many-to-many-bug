package org.hibernate.bugs;

import jakarta.persistence.*;

import java.util.Set;

@Embeddable
public class UsersEmbeddable {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserEntity> users;

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
