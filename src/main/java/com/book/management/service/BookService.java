package com.book.management.service;

import com.book.management.domain.Author;
import com.book.management.domain.Book;
import com.book.management.repository.AuthorRepository;
import com.book.management.repository.BookRepository;
import com.book.management.util.AuthorUtil;
import com.book.management.util.BookUtil;
import com.book.management.util.IntentUtil;
import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.StringJoiner;

@Service
@Transactional
public class BookService extends DialogflowApp {
    private Logger logger = LoggerFactory.getLogger((BookService.class));

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @ForIntent(IntentUtil.LIST_BOOK_BY_AUTHORS)
    public ActionResponse findAllBookByAuthor(ActionRequest request) {
        logger.info("begin intent: " + IntentUtil.LIST_BOOK_BY_AUTHORS);
        StringBuilder response = new StringBuilder();
        //retrieve given-name from request
        String givenName = request.getParameter("given-name").toString();
        String lastName = request.getParameter("last-name").toString();

        //retrieve author from DB
        Author author = authorRepository.findAuthorByGivenNameAndLastName(givenName, lastName);

        if (author != null) {
            List<Book> bookList = bookRepository.findBookByAuthor(author);

            //building response
            response.append(BookUtil.getRandomListOfBooksMessage());
            StringJoiner sj = new StringJoiner(", ");
            bookList.forEach(book -> sj.add(book.toString()));

            response.append(sj);
            response.append(". ");

            //add random message - ask user to select a book
            response.append(BookUtil.getRandomListOfBooksSelectionMessages());

        } else {
            response.append(AuthorUtil.NOT_FOUND_MESSAGE);
        }
        ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
        ActionResponse actionResponse = responseBuilder.build();
        return actionResponse;
    }

    @ForIntent(IntentUtil.LIST_BOOKS_BY_GENRE)
    public ActionResponse getBooksByGenre(ActionRequest request) {
        logger.info("begin intent: " + IntentUtil.GET_BOOK_DETAILS);
        StringBuilder response = new StringBuilder();
        String genre = request.getParameter("genre").toString();
        List<Book> bookList = bookRepository.findBooksByGenre(genre);
        if (bookList != null && bookList.size() > 0) {
            // start to build the response that will be sent back to user
            response = new StringBuilder(BookUtil.getRandomListOfBooksMessage());
            // use StringJoiner to add comma delimited values
            StringJoiner sj = new StringJoiner(", ");
            bookList.forEach(book -> sj.add(book.toString()));
            // add list of books to the response
            response.append(sj);
            response.append(". ");
            // add a custom message to ask user to continue the process
            response.append(BookUtil.getRandomListOfBooksSelectionMessages());

        }
        else {
            response.append(BookUtil.BOOK_NOT_FOUND_MESSAGE);
        }
        ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
        ActionResponse actionResponse = responseBuilder.build();
        return actionResponse;
    }


    @ForIntent(IntentUtil.GET_BOOK_DETAILS)
    public ActionResponse getBookDetails(ActionRequest request) {
        logger.info("begin intent: " + IntentUtil.GET_BOOK_DETAILS);
        StringBuilder response = new StringBuilder();
        ResponseBuilder responseBuilder = null;

        String bookTitle = request.getParameter("bookTitle").toString();

        Book book = bookRepository.findBookByTitle(bookTitle);
        if (book != null) {
            // start to populate the response that will be sent to user // (will contain book details)
            response.append(BookUtil.getRandomListOfBookDetailsMessages());
            response.append(book.getTitle());
            response.append(" was published in ");
            response.append(book.getPublicationYear());
            response.append(" and can be categorized as a ");
            response.append(book.getGenre());
            response.append(". Here is a short summary: ");
            response.append(book.getSummary());

            // create the responseBuilder object with the response (and end the conversation)
            responseBuilder = getResponseBuilder(request).add(response.toString()).endConversation();

        } else {
            response.append(BookUtil.NOT_FOUND_MESSAGE);
            responseBuilder = getResponseBuilder(request).add(response.toString());
        }
        ActionResponse actionResponse = responseBuilder.build();
        return actionResponse;
    }


    @ForIntent(IntentUtil.GET_BOOK_DETAILS_BY_GENRE)
    public ActionResponse getBookDetailsByGenre(ActionRequest request){
        logger.info("begin intent: " + IntentUtil.LIST_BOOKS_BY_GENRE);
        return getBookDetails(request);
    }

}
