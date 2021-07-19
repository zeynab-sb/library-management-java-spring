package com.library.main.controller;

import com.library.main.model.Book;
import com.library.main.repository.BookRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController("/reader")
public class ReaderController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public ModelAndView getAllBooks(HttpServletRequest request){


        List<Book> books = bookRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("reader");
        modelAndView.addObject("books", books);

        return modelAndView;
    }

}
