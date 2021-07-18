package com.library.main.controller;

import com.library.main.model.Book;
import com.library.main.repository.BookRepository;
import com.library.main.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/")
public class BookController {
    private final Mapper mapper = new Mapper();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/books", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView addBook(@RequestParam Map<String, String> bookRequest){
        //TODO authentication
        try { Book book = new Book();
//            book.setEnabled(true);
//            book.setPassword(passwordEncoder.encode(bookRequest.get("password")));
//            user.setUsername(userRequest.get("username"));
//            user.setAuthority(userRequest.get("authority"));
            book.setDate(book.convertStrToDate(bookRequest.get("Date")));
            book.setISSN(Long.valueOf(bookRequest.get("ISSN")));
            book.setKeywords(bookRequest.get("Keywords"));
            book.setTitle(bookRequest.get("Title"));
book.setWriters(bookRequest.get("Writers"));


            bookRepository.save(book);
            return new ModelAndView("redirect:" + "http://localhost:7070/books");

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }

    @GetMapping("/books/{id}")
    public ModelAndView deleteBook(@PathVariable("id") long id) {
        //TODO authentication
        try {

            RestTemplate restTemplate = new RestTemplate();
            String clearSessionURL = "http://localhost:8080/auth/clear";
            ResponseEntity<String> response = restTemplate.getForEntity(clearSessionURL + "/" + id, String.class);

            if(response.getStatusCode().equals(HttpStatus.OK)){
                bookRepository.deleteById(id);
            }

            return new ModelAndView("redirect:" + "http://localhost:7070/books");
        } catch (Exception e) {
            return new ModelAndView("500");
        }
    }

    @RequestMapping(value="/books/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView updateUser(@PathVariable("id") long id, @RequestParam Map<String, String> bookRequest) {
        //TODO authentication
        System.out.println("caaal shoood");
        Optional<Book> bookData = bookRepository.findById(id);
        try {
            if (bookData.isPresent()) {
                Book book = bookData.get();
//                _user.setUsername(userRequest.get("username"));
//                _user.setPassword(userRequest.get("password"));
//                _user.setAuthority(userRequest.get("authority"));
                book.setDate(book.convertStrToDate(bookRequest.get("Date")));
                book.setISSN(Long.valueOf(bookRequest.get("ISSN")));
                book.setKeywords(bookRequest.get("Keywords"));
                book.setTitle(bookRequest.get("Title"));
                book.setWriters(bookRequest.get("Writers"));
                bookRepository.save(book);
                return new ModelAndView("redirect:" + "http://localhost:7070/books");
            } else {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                return new ModelAndView("404");

            }
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }

    @GetMapping("/books")
    public ModelAndView getAllUsers() {
        //TODO authentication
        try {
            List<Book> books = new ArrayList<>(bookRepository.findAll());
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("book");
            modelAndView.addObject ( "books", books);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("500");
        }
    }
    @GetMapping("/401")
    public ModelAndView get401() {
        return new ModelAndView("401");
    }

}
