package com.tsis.drs.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Document {
    String document_id;
    String drafted_user_id;
    String reviewed_user_id;
    String approval_user_id;
    String serialnum;
    String title;
    Date requestdate;
    Date confirmdate;
    Date rentaldate;
    String reviewed_status;
    String approval_status;
    String reason_for_rejection;
    String document_status;
}
