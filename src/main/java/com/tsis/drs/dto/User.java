package com.tsis.drs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class User {
    String user_id;
    String name;
    String email;
    String phone;
    int role;
    String dept_name;
    int position;
}
