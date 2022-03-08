package com.tsis.drs.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Notice {
    String notice_id;
    String user_id;
    Date create_date;
    Date modify_date;
    String category;
    String title;
    String content;

}
