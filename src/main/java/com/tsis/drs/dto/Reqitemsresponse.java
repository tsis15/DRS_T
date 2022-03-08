package com.tsis.drs.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Reqitemsresponse {
    String requestitems_id;
    String document_id;
    String item_id;
    String rental_name;
    Date rental_date;
    Date return_date;
    String checker_name;
}
