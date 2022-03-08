package com.tsis.drs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Deptdivsion {
    수습사원(0),사원(1),대리(2),과장(3),
    차장(4),부장(5);

    private final int value;
}
