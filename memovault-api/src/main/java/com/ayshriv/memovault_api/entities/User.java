package com.ayshriv.memovault_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "PASSWORD")
    @JsonProperty("password")
    private String password;

    @Column(name = "FIRST_NAME")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "LAST_NAME")
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "MOBILE_NUMBER")
    @JsonProperty("mobile_number")
    private String mobileNo;

    @Column(name = "EMAIL_ID")
    @JsonProperty("email_address")
    private String emailId;

    @Column(name = "PHOTO")
    @JsonProperty("photo")
    private String photo;

    @Column(name = "STATUS")
    @JsonProperty("status")
    private String status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @Column(name = "JOURNAL_ENTRIES")
    List<JournalEntry> journalEntries;

    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(String password, String firstName, String lastName, String mobileNo, String emailId, String photo, String status) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.photo = photo;
        this.status = status;
    }

    public User(Long id, String password, String firstName, String lastName, String mobileNo, String emailId, String photo, String status) {
        super(id);
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.photo = photo;
        this.status = status;
    }
}
