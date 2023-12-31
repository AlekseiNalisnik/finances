package com.application.finances.repository;

import com.application.finances.dictionaries.TransactionPurposeDictionary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.Repository;

import java.util.List;

public interface DictionaryRepository extends CrudRepository<TransactionPurposeDictionary, Long> {
    @Query("""
        select tpd from TransactionPurposeDictionary tpd
    """)
    List<TransactionPurposeDictionary> findTransactionPurposeDictionary();
}
