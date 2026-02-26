package com.learning.jspringalgos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class AlgoResults {
    private String algoName;
    private List<String> algoPrints;
    private long duration;
    private int count;
    private String status;
}
