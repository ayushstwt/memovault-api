package com.ayshriv.memovault_api.repository;

import com.ayshriv.memovault_api.entities.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

}
