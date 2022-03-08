package com.tsis.drs.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Requestitems {

    String requestitems_id;
    String document_id;
    String item_id;
    Date rental_date;
    Date return_date;
    String checker_name;
}
