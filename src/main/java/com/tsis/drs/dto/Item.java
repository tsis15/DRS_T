package com.tsis.drs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Item {
    String item_id;
    String serialnum;
    String name;
    int price;
    String brand;
    String os;
    String link;
    String category;
    String status;
    String memo;
}
