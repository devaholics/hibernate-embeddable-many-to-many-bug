package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void shouldStoreUsersCollection() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        UserEntity u = new UserEntity();
        u.setId(1L);
        u.setName("user");
        entityManager.persist(u);

        ProductEntity productToStore = new ProductEntity();
        productToStore.setId(1L);
        productToStore.setName("hello");
        productToStore.setUsers(new HashSet<>(Collections.singletonList(u)));
        ProductEntity storedProduct = entityManager.merge(productToStore);
        Assert.assertNotNull(storedProduct.getUsers());

        UsersEmbeddable users = new UsersEmbeddable();
        users.setUsers(new HashSet<>(Collections.singletonList(u)));
        ProductWithEmbeddableEntity productWithEmbeddableToStore = new ProductWithEmbeddableEntity();
        productWithEmbeddableToStore.setId(2L);
        productWithEmbeddableToStore.setName("hello 2");
        productWithEmbeddableToStore.setUsers(users);
        ProductWithEmbeddableEntity storedProductWithEmbeddable = entityManager.merge(productWithEmbeddableToStore);
        Assert.assertNotNull(storedProductWithEmbeddable.getUsers().getUsers());
        // Do stuff...
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
