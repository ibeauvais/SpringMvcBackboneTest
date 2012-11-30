package fr.ib.test.springMvcTest.repository;


import fr.ib.test.springMvcTest.domain.Product;

import java.util.List;

public interface ProductRepository {

       List<Product> findByNameContain(String namePart);

    List<Product> findAll();

    Product save(Product product);
}
