package com.example.helloworldcounter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloWorldController {

    private int counter = 0;

    @RequestMapping("/")
    public String hello(Model model) {
	System.out.println("Hello, World!");
        counter++;
        model.addAttribute("message", "hello-world-" + counter);
        return "hello";
    }

  
}
