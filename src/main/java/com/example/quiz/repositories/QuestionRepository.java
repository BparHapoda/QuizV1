package com.example.quiz.repositories;

import com.example.quiz.dtos.QuestionForm;
import com.example.quiz.models.Question;

import java.util.Optional;

public interface QuestionRepository {
    QuestionForm getQuestion(int number);
    void saveQuestion(Question question);
}
