package com.astradev_back.astradev_back.db.repository;

import com.astradev_back.astradev_back.db.entity.KeyWords;
import com.astradev_back.astradev_back.db.entity.Users_KeyWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface Users_KeyWordsRepository extends JpaRepository<Users_KeyWords, Long> {

    @Query(value = "Select u.word from Users_KeyWords u where u.\"user\" = :user", nativeQuery = true)
    List<KeyWords> getWordsByUser(@Param("user") Long user);

    @Modifying
    @Transactional
    @Query(value = "Insert into Users_KeyWords VALUES (nextval('users_keywords_seq'), :user, :word);",
            nativeQuery = true)
    void add(@Param("user") Long user,
             @Param("word") Long word);

}

