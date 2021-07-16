package com.library.authentication.controller;

import com.library.authentication.dto.Credential;
import com.library.authentication.model.Session;
import com.library.authentication.model.User;
import com.library.authentication.repository.SessionRepository;
import com.library.authentication.repository.UserRepository;
import com.library.authentication.service.TokenService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.library.authentication.controller.AuthCookieFilter.extractAuthenticationCookie;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService = new TokenService();

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //    @PostMapping("/login")
//    public ResponseEntity<String> login(@Valid @RequestBody  Credential credential) {
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ModelAndView login(@RequestParam Map<String, String> loginRequest, Principal principal,
                       HttpServletResponse response) {

        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        System.out.println("hiii");
        List<User> userData = userRepository.findByUsername(username);

        if ((!userData.isEmpty()) && passwordEncoder.matches(password, userData.get(0).getPassword())) {

            String sessionId = tokenService.createToken();

            Session session = new Session();
            User user = userData.get(0);
            session.setUser(user);
            session.setId(sessionId);
            session.setValid_until(Timestamp.valueOf(LocalDateTime.now().plusDays(30)));
            sessionRepository.save(session);

            ResponseCookie cookie = ResponseCookie
                    .from(AuthCookieFilter.COOKIE_NAME, sessionId)
                    .maxAge(3600).sameSite("Strict")
                    .path("/").httpOnly(true).secure(false).build();

            JSONObject userInfo = new JSONObject();
            userInfo.put("authority", userData.get(0).getAuthority());
            userInfo.put("id", userData.get(0).getId());

             ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(userInfo.toString());
            // if(userData.get(0).getAuthority().equals("admin")) {
                 return new ModelAndView("redirect:" + "http://localhost:9090/admin/users");
            // }

        }

         ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return new ModelAndView("redirect:" + "http://localhost:9090/admin/users");
       // return new ModelAndView("401");

    }
//    @GetMapping("/401")
//    public ModelAndView get401Error() {
//
//
//    }
    @GetMapping("/authenticate")
    public Object authenticate(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String sessionId = extractAuthenticationCookie(httpServletRequest);
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
}
