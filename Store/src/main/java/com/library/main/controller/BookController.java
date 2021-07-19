package com.library.main.controller;

import com.library.main.model.Book;
import com.library.main.model.User;
import com.library.main.repository.BookRepository;
import com.library.main.repository.UserRepository;
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

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/book_publisher/{id}")
    public ModelAndView getBooksByPublisherId(@PathVariable("id") long id){

        Optional<User> users = userRepository.findById(id);
        List<Book> books = bookRepository.findBookByPublisher(users.get());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book");
        modelAndView.addObject ( "books", books);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView addBook(@RequestParam Map<String, String> bookRequest){
        try { Book book = new Book();
            book.setISSN(Long.valueOf(bookRequest.get("ISSN")));
            book.setTitle(bookRequest.get("Title"));
            book.setWriters(bookRequest.get("Writer"));
            book.setDate(book.convertStrToDate(bookRequest.get("Date").substring(0,10)));
            book.setKeywords(bookRequest.get("Keywords"));
            Optional<User> user = userRepository.findById(Long.valueOf(bookRequest.get("Publisher")));
            book.setPublisher(user.get());
            bookRepository.save(book);
            return new ModelAndView("redirect:" + "http://localhost:9091/book_publisher/" + bookRequest.get("Publisher"));

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }

    @GetMapping("/books/{bookid}/{userid}")
    public ModelAndView deleteBook(@PathVariable("bookid") long id, @PathVariable("userid") long user) {
            bookRepository.deleteById(id);
            return new ModelAndView("redirect:" + "http://localhost:9091/book_publisher/"+ user);
    }

    @RequestMapping(value="/books/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView updateBook(@PathVariable("id") long id, @RequestParam Map<String, String> bookRequest) {

        Optional<Book> bookData = bookRepository.findById(id);
        try {
            if (bookData.isPresent()) {
                Book book = bookData.get();
                book.setDate(book.convertStrToDate(bookRequest.get("Date").substring(0,10)));
                book.setISSN(Long.valueOf(bookRequest.get("ISSN")));
                book.setKeywords(bookRequest.get("Keywords"));
                book.setTitle(bookRequest.get("Title"));
                book.setWriters(bookRequest.get("Writers"));
                bookRepository.save(book);
                return new ModelAndView("redirect:" + "http://localhost:9091/book_publisher/"+ bookRequest.get("Publisher"));
            } else {
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
