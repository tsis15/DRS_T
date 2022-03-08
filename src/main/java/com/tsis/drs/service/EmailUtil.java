package com.tsis.drs.service;

public interface EmailUtil {
    void sendEmail(String toAddress, String subject, String body);
}
