package com.astradev_back.astradev_back.db.repository;

import com.astradev_back.astradev_back.db.entity.KeyWords;
import com.astradev_back.astradev_back.db.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface KeyWordsRepository extends JpaRepository<KeyWords, Long> {

    @Query(value = "Select * from keywords n where n.word = :word", nativeQuery = true)
    KeyWords getByWord(@Param("word") String word);

    @Modifying
    @Transactional
    @Query(value = "Insert into KeyWords VALUES (nextval('keywords_seq'), :word);",
            nativeQuery = true)
    void add(@Param("word") String word);

}
