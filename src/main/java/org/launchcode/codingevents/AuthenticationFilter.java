package org.launchcode.codingevents;

/* 19.5
 * TODO: Add notes from 19.5
 */

import org.launchcode.codingevents.controllers.AuthenticationController;
import org.launchcode.codingevents.data.UserRepository;
import org.launchcode.codingevents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthenticationController authController;

    public static final List<String> whitelist = Arrays.asList("/login","/register","/logout","/css"); // TODO: Add /js and /images

    private static boolean isWhitelisted(String path){
        // TODO: Isn't there some lambda expression (similar to JavaScript's Array.prototype.includes() method that could do this?
        for(String pathRoot : whitelist){
            if(path.startsWith(pathRoot)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest req,
            HttpServletResponse res,
            Object handler
    ) throws IOException {
        // Don't require sign-in for whitelisted pages
        if(isWhitelisted((req.getRequestURI()))){
            // Returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = req.getSession();
        User user = authController.getUserFromSession(session);

        // The User is Logged in
        if(user != null){
            return true;
        }

        // The User is Not Logged In (Logged Out)
        res.sendRedirect("/login");
        return false;
    }
}
