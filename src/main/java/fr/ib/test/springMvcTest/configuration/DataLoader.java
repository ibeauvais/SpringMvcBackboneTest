package fr.ib.test.springMvcTest.configuration;

import fr.ib.test.springMvcTest.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Date;


public class DataLoader implements ApplicationListener<ApplicationEvent> {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @PersistenceContext
    private EntityManager em;



    private void initDataIfEmpty() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Product.class)));
        Long nbEntity = em.createQuery(cq).getSingleResult();
         log.info("nbentity= {}",nbEntity);
        if (nbEntity == 0)
            loadData();


    }

    private void loadData() {
        String names[] = {"Product1", "Product2","Product3"};

        for (String name : names) {
            Product p = new Product();
            p.name = name;
            p.description = "product with name " + name;

            em.persist(p);
            log.info("add product {}",p);
        }

    }

      @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
       if (applicationEvent instanceof ContextRefreshedEvent)
           initDataIfEmpty();
    }
}
