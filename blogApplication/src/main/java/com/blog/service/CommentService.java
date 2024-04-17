package com.blog.service;

import com.blog.entity.Comment;
import com.blog.exception.CommentException;

public interface CommentService {
  public Comment createComment(Comment comment,long postId) throws CommentException;
  public Comment editComment(Comment comment) throws CommentException;
  public Comment deleteComment(long id) throws CommentException;
}
