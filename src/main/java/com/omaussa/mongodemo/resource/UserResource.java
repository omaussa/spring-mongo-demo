package com.omaussa.mongodemo.resource;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.omaussa.mongodemo.document.User;
import com.omaussa.mongodemo.document.response.SimpleResponse;
import com.omaussa.mongodemo.repository.UserRepository;
import com.omaussa.mongodemo.document.request.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserResource {
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(SimpleResponse.class);

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<SimpleResponse> addSome(@RequestBody UserRequest body) {
        SimpleResponse sr;
        HttpStatus httpStatus;
        try {
            userRepository.save(new User(null, body.getName(), body.getTeamName(), body.getSalary()));
            logger.info("USER SAVED");
            sr = new SimpleResponse("200", "OK");
            httpStatus = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            e.printStackTrace();
            sr = new SimpleResponse("500", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(sr, httpStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleResponse> updateSome(@PathVariable String id, @RequestBody UserRequest body) {
        SimpleResponse sr;
        HttpStatus httpStatus;
        try {
            if (userRepository.existsById(id)) {
                userRepository.save(new User(id, body.getName(), body.getTeamName(), body.getSalary()));
                sr = new SimpleResponse("200", "OK");
                httpStatus = HttpStatus.ACCEPTED;
            } else {
                sr = new SimpleResponse("404", "NotFound");
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sr = new SimpleResponse("500", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(sr, httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleResponse> deleteSome(@PathVariable String id) {
        SimpleResponse sr;
        HttpStatus httpStatus;
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                sr = new SimpleResponse("200", "OK");
                httpStatus = HttpStatus.ACCEPTED;
            } else {
                sr = new SimpleResponse("404", "NotFound");
                httpStatus = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sr = new SimpleResponse("500", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(sr, httpStatus);
    }
}
