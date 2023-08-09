package com.cydeo.service;

import com.cydeo.config.AppConfigData;
import com.cydeo.config.DBConfigData;
import com.cydeo.model.Comment;
import com.cydeo.proxy.CommentNotificationProxy;
import com.cydeo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CommentService {


    private final CommentRepository commentRepository;  // we write commentRepository if we write DBCommentRepository it is tightly couple.
    private final CommentNotificationProxy commentNotificationProxy;
    private final AppConfigData appConfigData;
    private final DBConfigData dbConfigData;

    public CommentService(CommentRepository commentRepository, @Qualifier("EMAIL") CommentNotificationProxy commentNotificationProxy, AppConfigData appConfigData, DBConfigData dbConfigData) {
        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
        this.appConfigData = appConfigData;
        this.dbConfigData = dbConfigData;
    }

    public void publishComment(Comment comment){
        //save in the DB
        commentRepository.storeComment(comment);
        //send email
        commentNotificationProxy.sendComment(comment);
    }

    public void printConfigData(){
        //print ozzy
        System.out.println(appConfigData.getUserName());

        //print abc123
        System.out.println(appConfigData.getPassword());

        //print url
        System.out.println(appConfigData.getUrl());
    }

    public void printDbConfigData(){
        //print username
        System.out.println(dbConfigData.getUsername());

        //print password
        System.out.println(dbConfigData.getPassword());

        //print type
        System.out.println(dbConfigData.getType());

    }


}
