package fr.ib.test.springMvcTest.web;

import fr.ib.test.springMvcTest.domain.Product;
import fr.ib.test.springMvcTest.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    @Inject
    private ProductRepository productRepository;


    @RequestMapping(value = "/search.do")
    public ModelAndView search() {
        return new ModelAndView("search");
    }


    @RequestMapping(value = "/rs/productSearch", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> searchQuery(@RequestParam("searchString") String searchString) {
      log.error("searchString {}",searchString);
        List<Product> company= productRepository.findByNameContain(searchString);

        log.error("{}",company);
        return company;
    }

}
