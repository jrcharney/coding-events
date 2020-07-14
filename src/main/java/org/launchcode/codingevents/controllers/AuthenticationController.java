package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.UserRepository;
import org.launchcode.codingevents.models.User;
import org.launchcode.codingevents.models.dto.LoginFormDTO;
import org.launchcode.codingevents.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepo;

    private static final String userSessionKey = "user";    // TODO: Do we need to change this?

    /* NOTE:
     * getUserFromSession and setUserInSession will allow us to retrieve and store
     * the login status of a user in a session. More specifically, a logged-in user's
     * user ID will be stored in their session.
     */

    public User getUserFromSession(HttpSession session){
        // TODO: Add some note later about what sessions are.
        // NOTE: There is no HttpsSession (with 4 Ss) in case you are wondering.
        // IIRC, a session is like a cookie but on the server side?
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if(userId == null){
            return null;
        }

        Optional<User> user = userRepo.findById(userId);

        if(user.isEmpty()){
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/register")
    public String renderRegistrationForm(Model model){
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title","Register");
        return "register";
    }

    // TODO: Add the notes form 19.4.4. that describe what is happening in this file.
    @PostMapping("/register")
    public String processRegisterationForm(
            @ModelAttribute @Valid RegisterFormDTO registerFormDTO,
            Errors errors,
            HttpServletRequest request,
            Model model
    ){
        if(errors.hasErrors()){
            model.addAttribute("title","Register");
            return "register";
        }

        User existingUser = userRepo.findByUsername(registerFormDTO.getUsername());

        if(existingUser != null){
            errors.rejectValue("username", "username.alreadyexists", "We're sorry. That username already exists. Pick something else and try again.");
            model.addAttribute("title","Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if(!password.equals(verifyPassword)){
            errors.rejectValue("password","password.mismatch", "Sorry, but your passwords do not match. Try entering them in again.");
            model.addAttribute("title","Register");
            return "register";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepo.save(newUser);
        setUserInSession(request.getSession(),newUser);
        return "redirect:";
    }

    /* 19.4.5. Login form */
    @GetMapping("/login")
    public String renderLoginForm(Model model){
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title","Log In");
        return "login";
    }

    /* TODO: add notes for this part too! */
    @PostMapping("/login")
    public String processLoginForm(
            @ModelAttribute @Valid LoginFormDTO loginFormDTO,
            Errors errors,
            HttpServletRequest request,
            Model model
    ){
        if(errors.hasErrors()){
            model.addAttribute("title","Login");
            return "login";
        }

        /* Why not just "user"? Because it's already being used. */
        User theUser = userRepo.findByUsername(loginFormDTO.getUsername());
        if(theUser == null){
            errors.rejectValue("username","user.invalid","Sorry, that user doesn't exists.");
            model.addAttribute("title","Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();
        if(!theUser.isMatchingPassword(password)){
            errors.rejectValue("password","password.invalid","Invalid password. Try again.");
            model.addAttribute("title","Log In");
            return "login";
        }

        setUserInSession(request.getSession(),theUser);
        return "redirect:";
    }

    /* 19.4.6. Log out */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";       // or should it be the homepage?
    }

    // TODO: Check to see if user is logged in and if so use it to tell the site how to show stuff.
    // TODO: Forgot username
    // TODO: Forgot password
    // TODO: Ban check
    // TODO: Captcha support
}
