package com.example.webosmotic.repository;

import com.example.webosmotic.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    public List<Contact> findByPid(Long id);
}
