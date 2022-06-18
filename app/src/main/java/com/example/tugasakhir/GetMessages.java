package com.example.tugasakhir;

public class
GetMessages {

    private String sender_email, receiver_email, email_subject, email_message, email_datetime;

    public GetMessages(String sender_email, String receiver_email, String email_subject, String email_message, String email_datetime) {
        this.sender_email = sender_email;
        this.receiver_email = receiver_email;
        this.email_subject = email_subject;
        this.email_message = email_message;
        this.email_datetime = email_datetime;
    }

    public String getSenderEmail() {
        return sender_email;
    }
    public String getReceiverEmail() {
        return receiver_email;
    }
    public String getEmailSubject() {
        return email_subject;
    }
    public String getEmailMessage() {
        return email_message;
    }
    public String getEmailDatetime() {
        return email_datetime;
    }

}
