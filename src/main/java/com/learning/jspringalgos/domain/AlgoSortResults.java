package com.learning.jspringalgos.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AlgoSortResults extends AlgoResults {
    private List<Integer> randomIntegerArray;
    private List<Integer> sortedIntegerArray;

    @Builder
    public AlgoSortResults(String algoName, List<String> algoPrints, long duration, int count, String status, List<Integer> randomIntegerArray, List<Integer> sortedIntegerArray) {
        super(algoName, algoPrints, duration, count, status);
        this.randomIntegerArray = randomIntegerArray;
        this.sortedIntegerArray = sortedIntegerArray;
    }

}
