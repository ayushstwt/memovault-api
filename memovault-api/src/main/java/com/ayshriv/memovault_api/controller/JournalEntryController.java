package com.ayshriv.memovault_api.controller;

import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.common.Resources;
import com.ayshriv.memovault_api.entities.JournalEntry;
import com.ayshriv.memovault_api.service.JournalEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/journal")
public class JournalEntryController {

    static Logger LOGGER = LoggerFactory.getLogger(JournalEntryController.class);

    @Autowired
    JournalEntryService journalEntryService;

    @PostMapping("/add/{username}")
    public MappingJacksonValue addJournalEntry(@RequestBody JournalEntry journalEntry,@PathVariable String username) {
        LOGGER.info("JournalController >> addJournalEntry called.");
        DesireStatus status = journalEntryService.addJournalEntry(journalEntry,username);
        String[] properties = { "statusType", "text", "journalEntry" };
        return Resources.formatedResponse(status, properties);
    }

    @PutMapping("/update")
    public MappingJacksonValue updateJournalEntry(@RequestBody JournalEntry journalEntry) {
        LOGGER.info("JournalController >> updateJournalEntry called.");
        DesireStatus status = journalEntryService.updateJournalEntry(journalEntry);
        String[] properties = { "statusType", "text", "journalEntry" };
        return Resources.formatedResponse(status, properties);
    }

    @DeleteMapping("/delete/{id}")
    public MappingJacksonValue deleteJournalEntry(@PathVariable("id") Long id) {
        LOGGER.info("JournalController >> deleteJournalEntry called for ID: " + id);
        DesireStatus status = journalEntryService.deleteJournalEntry(id);
        String[] properties = { "statusType", "text" };
        return Resources.formatedResponse(status, properties);
    }

    @GetMapping("/get/{id}")
    public MappingJacksonValue getSingleJournalEntry(@PathVariable("id") Long id) {
        LOGGER.info("JournalController >> getSingleJournalEntry called for ID: " + id);
        DesireStatus status = journalEntryService.getSingleJournalEntry(id);
        String[] properties = { "statusType", "text", "journalEntry" };
        return Resources.formatedResponse(status, properties);
    }

    @GetMapping("/getAll/{username}")
    public MappingJacksonValue getAllJournalEntries(@PathVariable String username) {
        LOGGER.info("JournalController >> getAllJournalEntries called.");
        DesireStatus status = journalEntryService.getAllJournalEntries(username);
        String[] properties = { "statusType", "text", "journalEntries" };
        return Resources.formatedResponse(status, properties);
    }



}
