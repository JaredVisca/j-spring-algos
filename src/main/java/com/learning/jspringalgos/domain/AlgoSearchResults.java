package com.learning.jspringalgos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class AlgoSearchResults extends AlgoResults {
    private int numToSearch;

    @Builder
    public AlgoSearchResults(String algoName, List<String> algoPrints, long duration, int count, String status, int numToSearch) {
        super(algoName, algoPrints, duration, count, status);
        this.numToSearch = numToSearch;
    }
}
