package com.tsis.drs.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DocumentResponse {
    Document document;
    String drafted_user_name;
    String reviewed_user_name;
    String approval_user_name;
    List<String> itemnames;

    public DocumentResponse(Document doc, String drname, String rvname, String tmp, List<String> itemnames) {
        this.document = doc;
        this.drafted_user_name = drname;
        this.reviewed_user_name = rvname;
        this.approval_user_name = tmp;
        this.itemnames = itemnames;
    }
}