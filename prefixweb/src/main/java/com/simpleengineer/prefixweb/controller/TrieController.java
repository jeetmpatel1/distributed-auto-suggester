package com.simpleengineer.prefixweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleengineer.prefixweb.service.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trie")
public class TrieController {

    @Autowired
    private TrieService service;

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/insert/{prefix}")
    public String addPrefix(@PathVariable("prefix") String prefix){
        service.insertPhrases(prefix);
        return prefix + " was inserted into the trie successfully.";
    }

    @GetMapping("/retrieve/{prefix}")
    public String retrievePrefix(@PathVariable("prefix") String prefix) throws JsonProcessingException {
        List<String> results = service.getMatchingPhrases(prefix);
        return mapper.writeValueAsString(results);
    }
}
