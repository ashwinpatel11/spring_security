package com.example.webosmotic.service;


import com.example.webosmotic.config.JwtAuthenticationFilter;
import com.example.webosmotic.entity.Contact;
import com.example.webosmotic.helper.JwtUtil;
import com.example.webosmotic.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JwtUtil jwtUtil;
    public Contact addContact(Contact contact) {

        contact.setPid(jwtUtil.getUid());
        return contactRepository.save(contact);
    }
    public Contact getContactById(Long id) {
        return contactRepository.findById(id).get();
    }

    public List<Contact> getContacts() {
        return contactRepository.findByPid(jwtUtil.getUid());
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public Contact updateContact(Contact contact) {
        contact.setPid(jwtUtil.getUid());
        return contactRepository.save(contact);
    }

}
