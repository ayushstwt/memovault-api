package com.ayshriv.memovault_api.service;

import com.ayshriv.memovault_api.common.DesireStatus;
import com.ayshriv.memovault_api.entities.JournalEntry;

public interface JournalEntryService {
    DesireStatus addJournalEntry(JournalEntry journalEntry);

    DesireStatus getSingleJournalEntry(Long id);

    DesireStatus deleteJournalEntry(Long id);

    DesireStatus updateJournalEntry(JournalEntry journalEntry);

    DesireStatus getAllJournalEntries();
}
