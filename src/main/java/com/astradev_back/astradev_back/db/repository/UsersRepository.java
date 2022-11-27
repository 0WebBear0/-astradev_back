package com.astradev_back.astradev_back.db.repository;

import com.astradev_back.astradev_back.db.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "Select * from Users n where n.name = :name", nativeQuery = true)
    Users getByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "Insert into Users VALUES (nextval('users_seq'), :name, :mail, :password);",
            nativeQuery = true)
    void add(
            @Param("name") String name,
            @Param("mail") String mail,
            @Param("password") String password
    );

}