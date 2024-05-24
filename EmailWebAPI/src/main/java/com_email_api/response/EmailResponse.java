package com_email_api.response;

public class EmailResponse {

    private String mailResponse;

    public EmailResponse(String mailResponse) {
        this.mailResponse = mailResponse;
    }

    public String getMailResponse() {
        return mailResponse;
    }

    public void setMailResponse(String mailResponse) {
        this.mailResponse = mailResponse;
    }
}
