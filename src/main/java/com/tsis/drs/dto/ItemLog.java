package com.tsis.drs.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ItemLog {
    String name; // 대여자
    String dept_name; // 부서 이름
    Date rental_date; // 대여날짜
    String checker_name; // 반납확인자
    Date return_date; // 반납일자
}
