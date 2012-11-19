package fr.ib.test.springMvcTest.repository;


import fr.ib.test.springMvcTest.domain.Product;
import fr.ib.test.test.listener.dbunit.DataSetLocation;
import fr.ib.test.test.listener.dbunit.DbInsertTestExecutionListener;
import org.fest.assertions.core.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:spring/persistence-context.xml"})
@TestExecutionListeners({DbInsertTestExecutionListener.class,DependencyInjectionTestExecutionListener.class,TransactionalTestExecutionListener.class})
@Transactional
public class ProductRepositoryImplTest {

    @Autowired
    private ProductRepository productRepository;
    @PersistenceContext
    private EntityManager em;


    @DataSetLocation("dataSet/productRepositoryTest.xml")
    @Test
    public void testFindByNameContain() {

        List<Product> result = productRepository.findByNameContain("myProd");
        assertThat(result).hasSize(2).haveExactly(1, nameEquals("myProduct1"))
                .haveExactly(1, nameEquals("myProduct2"));

    }


    private Condition<? super Product> nameEquals(final String productName) {

        return new Condition<Product>() {
            @Override
            public boolean matches(Product value) {
                return productName.equals(value.name);
            }
        };
    }


}
