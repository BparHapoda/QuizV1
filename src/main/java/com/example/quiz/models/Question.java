package com.example.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @JsonIgnore
    private Integer id;
    private String text;
    private List<Answer> answers;
    private String theme;
}
