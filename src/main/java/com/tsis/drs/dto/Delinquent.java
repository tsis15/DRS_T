package com.tsis.drs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Delinquent {
    private String name;
    private String email;
    private String overdue;
}
