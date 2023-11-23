package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    private final List<String> messages = new ArrayList<>();
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages() {
        return ResponseEntity.ok(messages);
    }
    ///curl -X GET localhost:8080/messages
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    ///curl -H "Content-Type: text/plain" -d gg localhost:8080/messages
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }
    ///curl -X GET localhost:8080/messages/1
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    ///curl -X DELETE localhost:8080/messages/0
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
    ///curl -X PUT -H "Content-Type: text/plain" -d PUT_SOMETHING localhost:8080/messages/1
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> searchMessage(
            @PathVariable("text") String s){
        int ans = -1;
        for (String elem: messages){
            if (elem.contains(s)){
                ans = messages.indexOf(elem);
                break;
            }
        }
        return ResponseEntity.ok(messages.indexOf(messages.get(ans)));
    }
    //curl -X GET localhost:8080/messages/search/g
}