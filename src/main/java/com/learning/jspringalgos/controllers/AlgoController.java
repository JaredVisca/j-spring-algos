package com.learning.jspringalgos.controllers;

import com.learning.jspringalgos.domain.AlgoResults;
import com.learning.jspringalgos.domain.AlgoSearchResults;
import com.learning.jspringalgos.domain.AlgoSortResults;
import com.learning.jspringalgos.services.AlgoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AlgoController {

    @NonNull
    private AlgoService algoService;

    @GetMapping("/simple")
    public AlgoSearchResults simpleSearch(@RequestParam(name = "numToSearch") int numToSearch) {
        return algoService.simpleSearch(numToSearch);
    }

    @GetMapping("/binary")
    public AlgoSearchResults binarySearch(@RequestParam(name = "numToSearch") int numToSearch) {
        return algoService.binarySearch(numToSearch);
    }

    @GetMapping("/selection")
    public AlgoSortResults selectionSort() {
        return algoService.selectionSort();
    }

    @GetMapping("/quick")
    public AlgoResults quickSort() {
        return algoService.quickSort();
    }

}
