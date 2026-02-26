package com.learning.jspringalgos.services;

import com.learning.jspringalgos.domain.AlgoResults;
import com.learning.jspringalgos.domain.AlgoSearchResults;
import com.learning.jspringalgos.domain.AlgoSortResults;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AlgoServiceImpl implements AlgoService {
    private static final String SIMPLE_SEARCH = "Simple Search";
    private static final String BINARY_SEARCH = "Binary Search";
    private static final String SELECTION_SORT = "Selection Sort";
    private static final String QUICK_SORT = "Quick Sort";
    private static final String SUCCESS = "Success! Number found.";
    private static final int ARRAY_LIMIT = 500;

    private List<Integer> createRandomIntegerArrayList() {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 10; i++) {
            integers.add(random.nextInt(100));
        }
        return integers;
    }

    public AlgoSearchResults simpleSearch(int numToSearch) {
        // Initializing variables that will be used throughout method
        List<String> listOfMessages = new ArrayList<>(); // List of messages to track progress of algos
        int count = 0; // Setting to 0 to track number of iterations of algo
        int[] arrayToSearch;
        long startTime;

        // Keeping the array to search small since simple search is O(n)
        if (numToSearch <= ARRAY_LIMIT) {
            // Creates array to search for number parameter between 0 and ARRAY_LIMIT
            listOfMessages.add(String.format("Creating an array between 0 and %s to search.", ARRAY_LIMIT));
            arrayToSearch = java.util.stream.IntStream.rangeClosed(0, ARRAY_LIMIT).toArray();

            // Always sort the array (even though it's sorted already, best practice is to assume arrays aren't sorted and sort them
            listOfMessages.add("Sorting array.");
            Arrays.sort(arrayToSearch);

            // Measuring elapsed time of search
            startTime = System.currentTimeMillis();

            // Simple search iterates one by one until matching data is found
            listOfMessages.add(String.format("Beginning to search array for %s using simple search.", numToSearch));
            for (int i = 0; i < arrayToSearch.length; i++) {
                listOfMessages.add(String.format("Iteration %s of %s", i, arrayToSearch.length));
                // Check to see if the current value matches the number to search for
                listOfMessages.add(String.format("Checking if %s matches %s", i, numToSearch));
                if(i == numToSearch) {
                    // The numbers matched and the search is complete. Return results
                    listOfMessages.add(String.format("%s matches %s. Search has completed; returning results.", i, numToSearch));
                    return new AlgoSearchResults(SIMPLE_SEARCH, listOfMessages, System.currentTimeMillis() - startTime, count, SUCCESS, numToSearch);
                }
                // The numbers didn't match. Iterate the array.
                listOfMessages.add(String.format("%s did not match %s. Iterating.", i, numToSearch));

                //Incrementing counter
                count++;
            }
            return new AlgoSearchResults(SIMPLE_SEARCH, null, 0, count, "There was an error and no match was found.", numToSearch);
        } else return new AlgoSearchResults(SIMPLE_SEARCH, null, 0, count, String.format("Please search for a number between 1  and %s.", ARRAY_LIMIT), numToSearch);
    }

    public AlgoSearchResults binarySearch(int numToSearch) {
        // Initializing variables that will be used throughout method
        List<String> listOfMessages = new ArrayList<>(); // List of messages to track progress of algos
        int count = 0; // Setting to 0 to track number of iterations of algo
        int[] arrayToSearch;
        long startTime;

        // Since this is binary search, we can create a bigger array to search since binary search is O(log n), but want to be reasonable XD
        if(numToSearch <= 500000) {
            listOfMessages.add("Creating an array between 0 and 500,000 to search.");
            arrayToSearch = java.util.stream.IntStream.rangeClosed(0, 500000).toArray();

            // Always sort the array (even though it's sorted already, best practice is to assume arrays aren't sorted and sort them
            listOfMessages.add("Sorting array.");
            Arrays.sort(arrayToSearch);

            // Creating low and high variables to use in binary search
            int low = 0;
            int high = arrayToSearch.length - 1;
            listOfMessages.add(String.format("Creating the low variable (%s) and the high variable (%s) to be used in the binary search.", low, high));

            // Binary search halves the array by checking if number being searched for is greater or less than the middle number until match is found
            // and more than one element exists in the array.
            listOfMessages.add(String.format("Beginning to search array for %s using binary search", numToSearch));

            // Measuring elapsed time of search
            startTime = System.currentTimeMillis();

            // While there is still more than one element in the array
            while(low <= high) {
                // Takes the current low and high and grabs the middle index
                int middle = (low + high) / 2;
                listOfMessages.add(String.format("Getting the middle position (%s) by halving the sum of the low (%s) and the high (%s)", middle, low, high));

                // Uses the middle index to grab the middle value in the array
                int guess = arrayToSearch[middle];
                listOfMessages.add(String.format("Getting the middle value (%s) in the array using the middle position (%s)", guess, middle));

                // Check to see if the middle value and the number to search for match
                listOfMessages.add(String.format("Checking if the middle value (%s) matches the number to search for (%s)", guess, numToSearch));
                if(guess == numToSearch) {
                    // The numbers match and the search is complete. Return results
                    listOfMessages.add(String.format("The middle value (%s) matches the number to search for (%s). Search has completed; returning results.", guess, numToSearch));
                    return new AlgoSearchResults(BINARY_SEARCH, listOfMessages, System.currentTimeMillis() - startTime, count, SUCCESS, numToSearch);
                }

                // They did not match. Now we need to see if the middle value was greater or less than the number to search.
                listOfMessages.add(String.format("The middle value (%s) did not match the number to search (%s). Checking to see if it was greater or less than the number to search.", guess, numToSearch));
                if(guess > numToSearch) {
                    // The middle value was greater than the number to search. This has eliminated every value greater than the middle value and we need to set the high to 1 less than it.
                    listOfMessages.add(String.format("The middle value (%s) was greater than the number to search (%s). Setting high to the middle position - 1.", guess, numToSearch));
                    high = middle - 1;
                    listOfMessages.add(String.format("The low is still %s and the high is now %s. Iterating.", low, high));
                } else {
                    // The middle value was less than the number to search. This has eliminated every value less than the middle value and we need to set the low to 1 more than the middle it.
                    listOfMessages.add(String.format("The middle value (%s) was less than the number to search (%s). Setting the low to the middle position + 1.", guess, numToSearch));
                    low = middle + 1;
                    listOfMessages.add(String.format("The low is now %s and the high is still %s. Iterating.", low, high));
                }
                //Incrementing counter
                count++;
            }
            return new AlgoSearchResults(BINARY_SEARCH, null, 0, count, "There was an error and no match was found.", numToSearch);
        } else return new AlgoSearchResults(BINARY_SEARCH, null, 0, count, "Please search for a number between 1 and 500000", numToSearch);
    }

    public AlgoSortResults selectionSort() {
        // Initializing variables that will be used throughout method
        List<String> listOfMessages = new ArrayList<>(); // List of messages to track progress of algos
        int count = 0; // Setting to 0 to track number of iterations of algo

        // Creates an unsorted array of Integers for us to sort using selection sort
        List<Integer> unsortedIntegerArray = createRandomIntegerArrayList();
        // Backup is created since the unsorted array will have the element removed each loop and assigning this to the original unsorted array
        // would be by reference and would return an empty array on completion
        List<Integer> backupUnsortedArray = new ArrayList<>(unsortedIntegerArray);
        // Creates an array to add to so that the unsorted array becomes a sorted array
        List<Integer> sortedIntegerArray = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        // Need to assign this to a new variable because the array size will change and the loop will end prematurely if we use .size() on the unsorted array
        final int randomIntegerArraySize = unsortedIntegerArray.size();
        for(int i = 0; i < randomIntegerArraySize; i++) {
            // Find the smallest element in the random integer array and return its position
            listOfMessages.add(String.format("Iterating over array; index at %s of %s. Finding smallest element of array to add to sorted array.", i, randomIntegerArraySize));
            int smallestIndex = findSmallestElementIndex(listOfMessages, unsortedIntegerArray);

            // Add value to sorted array using the index retrieved of smallest element while removing it from the unsorted array
            listOfMessages.add(String.format("Smallest element index (%s) retrieved. Grabbing value of element from array and adding to sorted array while removing it from unsorted array.", smallestIndex));
            sortedIntegerArray.add(unsortedIntegerArray.remove(smallestIndex));
            listOfMessages.add(String.format("Smallest value added to sorted array. Iterating."));
            count++;
        }
        listOfMessages.add("Unsorted array has been sorted using selection sort. Returning results.");
        return new AlgoSortResults(SELECTION_SORT, listOfMessages, System.currentTimeMillis() - startTime, count,"Sorted.", backupUnsortedArray, sortedIntegerArray);
    }

    private int findSmallestElementIndex(List<String> listOfMessages, List<Integer> integers) {
        listOfMessages.add(String.format("Finding smallest element value in array: %s", integers));
        // Used to store the smallest value and smallest index
        listOfMessages.add("Creating variables for smallest value which will be the value of the first element and setting smallest index to 0.");
        int smallestValue = integers.getFirst();
        int smallestIndex = 0;

        // Loop through array to find the smallest value and its index in the array
        listOfMessages.add("Starting loop of array to find smallest value and its index.");
        for(int i = 0; i < integers.size(); i++) {
            // Check if the current index value is less than the stored smallest value
            listOfMessages.add(String.format("Checking if %s is less than %s", integers.get(i), smallestValue));
            int indexValue = integers.get(i).intValue();
            if(indexValue < smallestValue) {
                listOfMessages.add(String.format("%s is less than %s. Assigning new values for smallest value and index.", indexValue, smallestValue));
                // Current value is smaller than stored smallest value, assign value and index
                smallestValue = integers.get(i);
                smallestIndex = i;
                listOfMessages.add(String.format("%s is now the smallest value and %s is its index.", smallestValue, smallestIndex));
            }
            listOfMessages.add(String.format("%s is greater than or equal to %s. Iterating.", indexValue, smallestValue));
            // Current value is greater than the stored smallest value; iterating
        }
        // Iteration has finished. Returning results
        listOfMessages.add(String.format("Array has been iterated and smallest value (%s) and its index (%s) have been found. Returning smallest index", smallestValue, smallestIndex));
        return smallestIndex;
    }

    public AlgoResults quickSort() {
        return new AlgoResults(QUICK_SORT, null, 0, 0, "Sorted.");
    }
}
