package fr.ib.test.springMvcTest.repository;


import fr.ib.test.springMvcTest.domain.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Product> findByNameContain(String namePart) {
        TypedQuery<Product> query = em.createNamedQuery(Product.FIND_BY_NAME_LIKE, Product.class);
        query.setParameter("name", "%" + namePart + "%");

        return query.getResultList();

    }

    @Override
    public List<Product> findAll() {

        TypedQuery<Product> query = em.createNamedQuery(Product.FIND_ALL, Product.class);
        return query.getResultList();

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }
}
