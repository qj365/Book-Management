package com.book.management.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookUtil {
    public static final List<String> listOfBooksMessages = new ArrayList<>();
    public static final List<String> listOfBooksSelectionMessages = new ArrayList<>();
    public static final List<String> listOfBookDetailsMessages = new ArrayList<>();
    public static final String NOT_FOUND_MESSAGE = "I didn't found requested book. Could you please try again?";
    public static final String BOOK_NOT_FOUND_MESSAGE = "No book found based on request. Please try again.";

    static {
        listOfBooksMessages.add("Sure, I got this books in my list: ");
        listOfBooksMessages.add("Ok, I found some books for you: ");
        listOfBooksMessages.add("I found the following books: ");

        listOfBooksSelectionMessages.add("Please select the book you want to know more details about.");
        listOfBooksSelectionMessages.add("Choose one book to continue.");
        listOfBooksSelectionMessages.add("I can provide book details if you select one of them.");

        listOfBookDetailsMessages.add("Here are some facts: ");
        listOfBookDetailsMessages.add("Good choice. Will provide you with some info on this book. ");
        listOfBookDetailsMessages.add("Ok, here are the book details: ");

    }

    public static String getRandomListOfBooksMessage() {
        int listOfBooksValue = new Random().nextInt(listOfBooksMessages.size());
        return listOfBooksMessages.get(listOfBooksValue);
    }

    public static String getRandomListOfBooksSelectionMessages() {
        int listOfBooksSelectionValue = new Random().nextInt(listOfBooksSelectionMessages.size());
        return listOfBooksSelectionMessages.get(listOfBooksSelectionValue);
    }

    public static String getRandomListOfBookDetailsMessages(){
        int rand = new Random().nextInt(listOfBookDetailsMessages.size());
        return listOfBookDetailsMessages.get(rand);
    }
}