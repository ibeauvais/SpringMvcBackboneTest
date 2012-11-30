package fr.ib.test.springMvcTest.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/index.do")
public class AdminController {




    @RequestMapping(method = RequestMethod.GET)
    public void index(){

    }
}
