package com.application.finances.service;

import com.application.finances.dictionaries.TransactionPurposeDictionary;
import com.application.finances.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    public List<TransactionPurposeDictionary> findTransactionPurposeDictionary() {
        return dictionaryRepository.findTransactionPurposeDictionary();
    }

    public Optional<TransactionPurposeDictionary> findTransactionPurposeDictionaryById(Long id) {
        return dictionaryRepository.findById(id);
    }
}
