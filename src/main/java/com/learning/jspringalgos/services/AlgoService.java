package com.learning.jspringalgos.services;

import com.learning.jspringalgos.domain.AlgoResults;
import com.learning.jspringalgos.domain.AlgoSearchResults;
import com.learning.jspringalgos.domain.AlgoSortResults;

public interface AlgoService {

    AlgoSearchResults simpleSearch(int numToSearch);
    AlgoSearchResults binarySearch(int numToSearch);
    AlgoSortResults selectionSort();
    AlgoResults quickSort();

}
