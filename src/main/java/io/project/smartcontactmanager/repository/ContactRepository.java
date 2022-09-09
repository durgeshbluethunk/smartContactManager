package io.project.smartcontactmanager.repository;

import io.project.smartcontactmanager.model.Contact;
import io.project.smartcontactmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {


    @Query("from Contact as c where c.user.id = :userId")
//    CurrentPage-page
//    Contact per page
    public Page<Contact> findContactsByUser(@Param("userId")int userId, Pageable pageable);

    public List<Contact> findByNameContainingAndUser(String name, User user);
}
