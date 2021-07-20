package com.library.authentication.controller;

import com.library.authentication.model.Session;
import com.library.authentication.model.User;
import com.library.authentication.repository.SessionRepository;
import com.library.authentication.repository.UserRepository;
import com.library.authentication.service.TokenService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.library.authentication.controller.AuthCookieFilter.extractAuthenticationCookie;

@RestController
@Transactional
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService = new TokenService();

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    void login(@RequestParam Map<String, String> loginRequest, Principal principal,
                       HttpServletResponse response) throws IOException {

        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        List<User> userData = userRepository.findByUsername(username);

        if ((!userData.isEmpty()) && passwordEncoder.matches(password, userData.get(0).getPassword())) {

            String sessionId = tokenService.createToken();

            Session session = new Session();
            User user = userData.get(0);
            session.setUser(user);
            session.setId(sessionId);
            session.setValid_until(Timestamp.valueOf(LocalDateTime.now().plusDays(30)));
            sessionRepository.save(session);

            Cookie cookie = new Cookie("persist", sessionId);
            cookie.setMaxAge(3600);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            JSONObject userInfo = new JSONObject();
            userInfo.put("authority", userData.get(0).getAuthority());
            userInfo.put("id", userData.get(0).getId());

            response.addCookie(cookie);

             if(userData.get(0).getAuthority().equals("admin")) {
                 response.sendRedirect("http://localhost:9090/admin/users");
             }
            else if(userData.get(0).getAuthority().equals("publisher")) {
                response.getWriter().write(userInfo.toString());
                response.sendRedirect("http://localhost:7070/book_publisher/" + userData.get(0).getId());
            }
             else if(userData.get(0).getAuthority().equals("reader")) {
//                 response.getWriter().write(userInfo.toString());
                 response.sendRedirect("http://localhost:9090/reader/books");
             }

        }

//         ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
////        return new ModelAndView("redirect:" + "http://localhost:9090/admin/users");
//         return new ModelAndView("401");

    }

    @GetMapping("/authenticate/{sessionId}")
    public Object authenticate(@PathVariable("sessionId") String sessionId) {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String sessionId = extractAuthenticationCookie(httpServletRequest);
        if (sessionId != null) {
            var sessionInfo = sessionRepository.findSessionById(sessionId);
            if (!sessionInfo.isEmpty()) {
                var record = userRepository.findById(sessionInfo.get(0).getUser().getId());
                if (record.isPresent()) {
                    JSONObject userInfo = new JSONObject();
                    userInfo.put("authority", record.get().getAuthority());
                    userInfo.put("id", record.get().getId());
                    return userInfo.toString();
                }
            }

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @GetMapping("/clear/{id}")
    public Boolean clearAllSessions(@PathVariable("id") long id){
        Optional<User> user = userRepository.findById(id);
        sessionRepository.deleteAllByUser(user.get());
        return true;
    }

}
