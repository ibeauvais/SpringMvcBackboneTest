package fr.ib.test.springMvcTest.web;


import fr.ib.test.springMvcTest.domain.Product;
import fr.ib.test.springMvcTest.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/admin/rs/*")
public class ProductResources {


    @Inject
    private ProductRepository productRepository;

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Product> findAll() {

        return productRepository.findAll();

    }

    @RequestMapping(value = "product", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

}
