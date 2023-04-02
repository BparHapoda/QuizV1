package com.example.quiz.services;

import com.example.quiz.dtos.QuestionForm;
import com.example.quiz.models.Question;

public interface QuestionService {
    void save(QuestionForm questionForm);
    Question get(int i);
}
