package com.ayshriv.memovault_api.service.impl;
import com.ayshriv.memovault_api.common.Constants;
import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.common.Resources;
import com.ayshriv.memovault_api.entities.JournalEntry;
import com.ayshriv.memovault_api.entities.User;
import com.ayshriv.memovault_api.repository.JournalEntryRepository;
import com.ayshriv.memovault_api.repository.UserRepository;
import com.ayshriv.memovault_api.service.JournalEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryServiceImpl implements JournalEntryService {

    static Logger LOGGER = LoggerFactory.getLogger(JournalEntryService.class);

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DesireStatus addJournalEntry(JournalEntry journalEntry,String username) {
        LOGGER.info("JournalEntryService >> addJournalEntry called!");
        DesireStatus status = new DesireStatus();
        Date dtNow = new Date();
        LOGGER.info("JournalEntryService >> addJournalEntry object received >> Title: " + journalEntry.getTitle());
        LOGGER.info("JournalEntryService >> addJournalEntry object received >> Content: " + journalEntry.getContent());
        try {
            User user=userRepository.findByEmailId(username);
            if (user!=null) {
                if (journalEntry.getTitle() == null || journalEntry.getContent() == null) {
                    LOGGER.error("Invalid JournalEntry object: Missing required fields (title/content)");
                    status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.PARAMETER_MISSING, "Title or Content");
                    return status;
                }
                journalEntry.setCreatedOn(dtNow);
                journalEntry.setUpdatedOn(dtNow);
                journalEntry.setUser(user);
                JournalEntry savedJournalEntry = journalEntryRepository.save(journalEntry);
                if (savedJournalEntry != null) {
                    status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.SAVE_SUCCESS, "JournalEntry");
                    status.setJournalEntry(savedJournalEntry);
                } else {
                    status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.SAVE_FAILURE, "JournalEntry");
                }
            }
            else {
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.OBJ_NOT_EXIST, "user");
            }

        } catch (Exception e) {
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Add journal entry");
        }
        return status;
    }

    @Override
    public DesireStatus updateJournalEntry(JournalEntry journalEntry) {
        LOGGER.info("JournalEntryService >> updateJournalEntry called!");
        DesireStatus status = new DesireStatus();
        try {
            Optional<JournalEntry> existingEntry = journalEntryRepository.findById(journalEntry.getId());
            if (existingEntry.isPresent()) {
                JournalEntry updatedEntry = existingEntry.get();
                updatedEntry.setTitle(journalEntry.getTitle());
                updatedEntry.setContent(journalEntry.getContent());
                updatedEntry.setUpdatedOn(new Date());
                journalEntryRepository.save(updatedEntry);

                status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.UPDATE_SUCCESS, "JournalEntry");
                status.setJournalEntry(updatedEntry);
            } else {
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.OBJ_NOT_EXIST, "JournalEntry");
            }
        } catch (Exception e) {
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Update journal entry");
        }
        return status;
    }

    @Override
    public DesireStatus deleteJournalEntry(Long id) {
        LOGGER.info("JournalEntryService >> deleteJournalEntry called!");
        DesireStatus status = new DesireStatus();
        try {
            Optional<JournalEntry> existingEntry = journalEntryRepository.findById(id);
            if (existingEntry.isPresent()) {
                journalEntryRepository.deleteById(id);
                status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.DELETE_SUCCESS, "JournalEntry");
            } else {
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.OBJ_NOT_EXIST, "JournalEntry");
            }
        } catch (Exception e) {
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Delete journal entry");
        }
        return status;
    }

    @Override
    public DesireStatus getSingleJournalEntry(Long id) {
        LOGGER.info("JournalEntryService >> getSingleJournalEntry called!");
        DesireStatus status = new DesireStatus();
        try {
            Optional<JournalEntry> journalEntry = journalEntryRepository.findById(id);
            if (journalEntry.isPresent()) {
                status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.OBJ_EXIST, "JournalEntry");
                status.setJournalEntry(journalEntry.get());
            } else {
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.OBJ_NOT_EXIST, "JournalEntry");
            }
        } catch (Exception e) {
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Get journal entry");
        }
        return status;
    }

    @Override
    public DesireStatus getAllJournalEntries(String username) {
        LOGGER.info("JournalEntryService >> getAllJournalEntries called!");
        DesireStatus status = new DesireStatus();
        try {
            User user = userRepository.findByEmailId(username);
            if (user!=null) {
                List<JournalEntry> journalEntries = user.getJournalEntries();
                if (!journalEntries.isEmpty()) {
                    status = Resources.setStatus(Constants.STATUS_SUCCESS, Constants.LIST_SUCCESS, "JournalEntries");
                    status.setJournalEntries(journalEntries);
                } else {
                    status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.LIST_FAILURE, "JournalEntries");
                }
            }
            else {
                status = Resources.setStatus(Constants.STATUS_FAILURE, Constants.OBJ_NOT_EXIST, "user");
            }

        } catch (Exception e) {
            status = Resources.setStatus(Constants.STATUS_ERROR, Constants.EXECUTION_ERROR + e.getMessage(), "Fetch all journal entries");
        }
        return status;
    }


}
