package com.library.search.controller;

import com.library.search.model.Book;
import com.library.search.repository.BookRepository;

import java.util.*;

import com.library.search.model.Book;
import com.library.search.repository.BookRepository;
import com.library.search.repository.BookSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
//@Validated
@RequestMapping({"/"})
public class BookSearchController {

    @Autowired
    private BookRepository bookRepository;

    public BookSearchController() {
    }

    @GetMapping("/search")
    public ModelAndView search() {
        //TODO authentication
        try {
            System.out.println("hiiiiiiiiiiiiiiiiii");

//            modelAndView.addObject ( "users", users);
            return new ModelAndView("search");
        } catch (Exception e) {
            return new ModelAndView("500");
        }
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

        Specification<Book> spec;
System.out.println("fuckkk y");
        spec = new BookSpecification(search, from, to);


        books = bookRepository.findAll( spec);

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
            return new ModelAndView("books" , response);
        } catch (Exception var3) {
            return new ModelAndView("500");
        }
    }
}

