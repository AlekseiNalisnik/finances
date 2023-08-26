package com.application.finances.controller;

import com.application.finances.dictionaries.TransactionPurposeDictionary;
import com.application.finances.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/finances/secured/dictionaries")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("transaction-purpose")
    public ResponseEntity<List<TransactionPurposeDictionary>> getTransactionPurposeDictionary() {
        return ResponseEntity.ok(dictionaryService.findTransactionPurposeDictionary());
    }
}
