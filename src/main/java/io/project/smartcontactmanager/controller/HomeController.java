package io.project.smartcontactmanager.controller;

import io.project.smartcontactmanager.helper.Message;
import io.project.smartcontactmanager.model.User;
import io.project.smartcontactmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title", "Register - Smart Contact Manager");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping("/signin")
    public String signin(Model model){
        model.addAttribute("title", "Login - Smart Contact Manager");
        return "login";
    }

//    Handler for registering the User
    @PostMapping(value = "/do_register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session){
        try{
            if(!agreement){
                System.out.println("You have not agreed to the terms and conditions");
                throw new Exception("You have not agreed to the terms and conditions");
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.png");

            System.out.println("Agreement " + agreement);
            System.out.println("User" + user);


            User result = this.userRepository.save(user);

            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered!!", "alert-success"));
            return "signup";

        }
        catch(Exception e){
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something Went Wrong !!" + e.getMessage(), "alert-error"));
            return "signup";
        }


    }
}
