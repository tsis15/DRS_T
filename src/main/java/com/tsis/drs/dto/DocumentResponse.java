package com.tsis.drs.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class DocumentResponse {
    Document document;
    String drafted_user_name;
    String reviewed_user_name;
}
