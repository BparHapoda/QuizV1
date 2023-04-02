package com.example.quiz.services;

import com.example.quiz.dtos.QuestionForm;
import com.example.quiz.models.Question;
import com.example.quiz.repositories.QuestionRepository;

public class QuestionServiceImpl implements QuestionService{
    private QuestionRepository repository;
    public QuestionServiceImpl(QuestionRepository repository){
        this.repository=repository;

    }
    @Override
    public void save(QuestionForm questionForm) {
        Question question=Question.builder().
                text(questionForm.getText()).
                answers(questionForm.getAnswers()).
                theme(questionForm.getTheme()).
                build();
        repository.saveQuestion(question);
    }

    @Override
    public Question get(int i) {
QuestionForm questionForm = repository.getQuestion(i);
Question question=Question.builder().
        text(questionForm.getText()).
        theme(questionForm.getTheme()).
        answers(questionForm.getAnswers())
        .build();
        return question;
    }
}
