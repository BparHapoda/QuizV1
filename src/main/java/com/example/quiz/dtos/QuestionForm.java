package com.example.quiz.dtos;

import com.example.quiz.models.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionForm {
    private String text;
    private List<Answer> answers;
    private String theme;

}
