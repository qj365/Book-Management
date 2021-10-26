package com.book.management.service;

import com.book.management.domain.Author;
import com.book.management.repository.AuthorRepository;
import com.book.management.util.AuthorUtil;
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
public class AuthorService extends DialogflowApp {
    private Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    private AuthorRepository authorRepository;

    @ForIntent(IntentUtil.LIST_AUTHORS)
    public ActionResponse findAll(ActionRequest request){
        logger.info("begin intent list authors");

        //find list author from DB
        List<Author> authorList = authorRepository.findAll();

        //creating response
        StringBuilder response = new StringBuilder(AuthorUtil.getRandomList0fAuthorsMessage());

        StringJoiner sj = new StringJoiner(", ");
        authorList.forEach(author -> sj.add(author.toString()));

        //add list of author to response
        response.append(sj);
        response.append(". ");

        response.append(AuthorUtil.getRandomAuthorSelectionMessage());

        //build the action request and add response to it
        ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
        ActionResponse actionResponse = responseBuilder.build();
        logger.info(actionResponse.toJson());
        return actionResponse;
    }
}
