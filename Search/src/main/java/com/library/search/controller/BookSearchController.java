package com.library.search.controller;

import com.library.search.model.Book;
import com.library.search.repository.BookRepository;

import java.util.*;

import com.library.search.model.Book;
import com.library.search.repository.BookRepository;
import com.library.search.repository.BookSpecification;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Validated
@RequestMapping({"/"})
public class BookSearchController {

    @Autowired
    private BookRepository bookRepository;

    public BookSearchController() {
    }
    @RequestMapping(
            value = {"search/books"},
            method = {RequestMethod.POST},
            consumes = {"application/x-www-form-urlencoded"},
            produces = {"application/atom+xml", "application/json"}
    )

    public ModelAndView searchBook(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                   @RequestParam(name = "size", required = false, defaultValue = "3") String size,
                                   @RequestParam Map<String, String> req,
                                   @ModelAttribute Book search,

                                   Model model) {
        String from = req.get("from");
        String to = req.get("to");
        List<Book> books = null;
        JSONObject jsonResponse = null;
        Specification<Book> spec;

        spec = new BookSpecification(search, from, to);


        books = bookRepository.findAll(spec);

        Map<String, Object> response = new HashMap<String, Object>();

        response.put("books", books);
        try {
//            Book book = new Book();
//            book.setDate(book.convertStrToDate((String)bookRequest.get("Date")));
//            book.setISSN(Long.valueOf((String)bookRequest.get("ISSN")));
//            book.setKeywords((String)bookRequest.get("Keywords"));
//            book.setTitle((String)bookRequest.get("Title"));
//            book.setWriters((String)bookRequest.get("Writers"));
//            this.bookRepository.save(book);
            return new ModelAndView("redirect:http://localhost:7070/books");
        } catch (Exception var3) {
            return new ModelAndView("500");
        }
    }
    @RequestMapping(
            value = {"/books"},
            method = {RequestMethod.POST},
            consumes = {"application/x-www-form-urlencoded"},
            produces = {"application/atom+xml", "application/json"}
    )

    public ModelAndView addBook(@RequestParam Map<String, String> bookRequest) {
        try {
            Book book = new Book();
            book.setDate(book.convertStrToDate((String)bookRequest.get("Date")));
            book.setISSN(Long.valueOf((String)bookRequest.get("ISSN")));
            book.setKeywords((String)bookRequest.get("Keywords"));
            book.setTitle((String)bookRequest.get("Title"));
            book.setWriters((String)bookRequest.get("Writers"));
            this.bookRepository.save(book);
            return new ModelAndView("redirect:http://localhost:7070/books");
        } catch (Exception var3) {
            return new ModelAndView("500");
        }
    }

    @GetMapping({"/books/{id}"})
    public ModelAndView deleteBook(@PathVariable("id") long id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String clearSessionURL = "http://localhost:8080/auth/clear";
            ResponseEntity<String> response = restTemplate.getForEntity(clearSessionURL + "/" + id, String.class, new Object[0]);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                this.bookRepository.deleteById(id);
            }

            return new ModelAndView("redirect:http://localhost:7070/books");
        } catch (Exception var6) {
            return new ModelAndView("500");
        }
    }

    @RequestMapping(
            value = {"/books/{id}"},
            method = {RequestMethod.POST},
            consumes = {"application/x-www-form-urlencoded"},
            produces = {"application/atom+xml", "application/json"}
    )
    public ModelAndView updateUser(@PathVariable("id") long id, @RequestParam Map<String, String> bookRequest) {
        System.out.println("caaal shoood");
        Optional bookData = this.bookRepository.findById(id);

        try {
            if (bookData.isPresent()) {
                Book book = (Book)bookData.get();
                book.setDate(book.convertStrToDate((String)bookRequest.get("Date")));
                book.setISSN(Long.valueOf((String)bookRequest.get("ISSN")));
                book.setKeywords((String)bookRequest.get("Keywords"));
                book.setTitle((String)bookRequest.get("Title"));
                book.setWriters((String)bookRequest.get("Writers"));
                this.bookRepository.save(book);
                return new ModelAndView("redirect:http://localhost:7070/books");
            } else {
                return new ModelAndView("404");
            }
        } catch (Exception var6) {
            var6.printStackTrace();
            return new ModelAndView("500");
        }
    }

    @GetMapping({"/books"})
    public ModelAndView getAllUsers() {
        try {
            List<Book> books = new ArrayList(this.bookRepository.findAll());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("book");
            modelAndView.addObject("books", books);
            return modelAndView;
        } catch (Exception var3) {
            return new ModelAndView("500");
        }
    }

    @GetMapping({"/401"})
    public ModelAndView get401() {
        return new ModelAndView("401");
    }
}

