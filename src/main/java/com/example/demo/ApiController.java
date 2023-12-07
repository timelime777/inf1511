package com.example.demo;

import ch.qos.logback.core.BasicStatusManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    private final List<String> messages = new ArrayList<>(); /// тут хранятся ТЕМЫ!!!
    private final List<List<String>> comments = new ArrayList<>();

    @GetMapping("themes") /// _______3!_______
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    ///curl -X GET localhost:8080/themes



    @PostMapping("themes") /// _______1!_______
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        List<String> comm = new ArrayList<>();
        comments.add(comm);
        return ResponseEntity.accepted().build();
    }
    ///curl -H "Content-Type: text/plain" -d gg localhost:8080/themes



    @GetMapping("themes/{index}") /// _______7!_______
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }
    ///curl -X GET localhost:8080/themes/0



    @GetMapping("themes/number_of_themes") /// _______5!_______
    public ResponseEntity<Integer> getNumberOfThemes() {
        int ans = messages.size();
        return ResponseEntity.ok(ans);
    }
    ///curl -X GET localhost:8080/themes/number_of_themes



    @DeleteMapping("themes") /// _______6!_______
    public ResponseEntity<Void> deleteAll() {
        messages.clear();
        comments.clear();
        return ResponseEntity.noContent().build();
    }
    ///curl -X DELETE localhost:8080/themes




    @DeleteMapping("themes/{index}") /// _______2!_______
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        comments.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    ///curl -X DELETE localhost:8080/themes/0




    @PutMapping("themes/{index}") /// _______4!_______
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        List<String> comm = new ArrayList<>();
        comments.remove((int) i);
        comments.add(i, comm);
        return ResponseEntity.accepted().build();
    }
    ///curl -X PUT -H "Content-Type: text/plain" -d PUT_SOMETHING localhost:8080/themes/1



    @PostMapping("themes/{index}/commentaries") /// _______8!_______
    public ResponseEntity<Void> addComment(@RequestBody String text, @PathVariable("index") Integer
            index) {
        List<String> comm = comments.get((int) index);
        comm.add(text);
        comments.remove((int) index);
        comments.add(index, comm);
        return ResponseEntity.accepted().build();
    }
    ///curl -H "Content-Type: text/plain" -d wp localhost:8080/themes/0/commentaries



    @DeleteMapping("themes/{index}/commentaries/{index2}") /// _______9!_______
    public ResponseEntity<Void> deleteComment(@PathVariable("index") Integer index,
                                              @PathVariable("index2") Integer index2) {
        List<String> comm = comments.get((int) index);
        comm.remove((int) index2);
        comments.remove((int) index);
        comments.add((int) index, comm);
        return ResponseEntity.noContent().build();
    }
    ///curl -X DELETE localhost:8080/themes/0/commentaries/0



    @PutMapping("themes/{index}/commentaries/{index2}") /// _______10!_______
    public ResponseEntity<Void> updateComment(
            @PathVariable("index") Integer i,
            @PathVariable("index") Integer index2,
            @RequestBody String comment) {
        List<String> comm = comments.get((int) i);
        comm.remove((int) index2);
        comm.add((int) index2, comment);
        comments.remove((int) i);
        comments.add((int) i, comm);

        return ResponseEntity.accepted().build();
    }
    ///curl -X PUT -H "Content-Type: text/plain" -d PUT_SOMETHING_to_gg localhost:8080/themes/0/commentaries/0



    @GetMapping("themes/{index}/commentaries") /// _______11!_______
    public ResponseEntity<List<String>> getComments(@PathVariable("index") Integer index) {
        List<String> comm = comments.get((int) index);
        return ResponseEntity.ok(comm);
    }
    ///curl -X GET localhost:8080/themes/0/commentaries
}
