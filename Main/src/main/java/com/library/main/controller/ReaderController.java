package com.library.main.controller;

import com.library.main.model.Book;
import com.library.main.model.Comment;
import com.library.main.model.User;
import com.library.main.repository.BookRepository;
import com.library.main.repository.CommentRepository;
import com.library.main.repository.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/books")
    public ModelAndView getAllBooks(){


        List<Book> books = bookRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("reader");
        modelAndView.addObject("books", books);

        return modelAndView;
    }

    @GetMapping("/bookinfo/{bookid}/{userid}")
    public ModelAndView getBookInfo(@PathVariable("bookid") long bookid, @PathVariable("userid") long userid){

        Optional<Book> book = bookRepository.findById(bookid);
        List<Comment> comments = commentRepository.findAllByBook(book.get());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookinfo");
        modelAndView.addObject("book", book.get());
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("userid", userid);

        return modelAndView;
    }

    @RequestMapping(value = "/addcomment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void addComment(@RequestParam Map<String, String> comment, HttpServletResponse response) throws IOException {
        Comment cm = new Comment();
        Optional<Book> book = bookRepository.findById(Long.valueOf(comment.get("bookid")));
        Optional<User> user = userRepository.findById(Long.valueOf(comment.get("userid")));

        cm.setBook(book.get());
        cm.setUser(user.get());
        cm.setText(comment.get("text"));

        commentRepository.save(cm);

        response.sendRedirect("http://localhost:9090/reader/bookinfo/" + comment.get("bookid") + "/" + comment.get("userid"));
    }

    @RequestMapping(value="/editcomment/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView updateUser(@PathVariable("id") long id, @RequestParam Map<String, String> commentRequest) {
        Optional<Comment> commentData = commentRepository.findById(id);
        try {
            if (commentData.isPresent()) {
                Comment cm = commentData.get();

                cm.setText(commentRequest.get("text"));
                commentRepository.save(cm);
                return new ModelAndView("redirect:" +"http://localhost:9090/reader/bookinfo/" + commentRequest.get("bookid") + "/" + commentRequest.get("userid") ) ;
            } else {
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                return new ModelAndView("404");

            }
        }catch(Exception e){
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }
    @PostMapping("/deletecomment")
    public void deleteComment(@RequestParam Map<String, String> comment, HttpServletResponse response) throws IOException {

        commentRepository.deleteById(Long.parseLong(comment.get("id")));
        response.sendRedirect("http://localhost:9090/reader/bookinfo/" + comment.get("bookid") + "/" + comment.get("userid"));

    }




}
