package com.example.webosmotic.controller;


import com.example.webosmotic.entity.Contact;
import com.example.webosmotic.service.ContactService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Contact")
@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/testcontact")
    public String test() {
        return "hello cobn";
    }

    @PostMapping("/addContact")
    public Contact addContact(@RequestBody @Valid Contact contact) {
        return contactService.addContact(contact);
    }

    @GetMapping("/getContact/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    @GetMapping("/getContacts")
    public List<Contact> getAllContact() {
        return contactService.getContacts();
    }

    @PutMapping("/updateContact")
    public Contact updateContact(@RequestBody Contact contact) {
        return contactService.updateContact(contact);
    }

    @DeleteMapping("/deleteContact/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "contact deleted";
    }


}
