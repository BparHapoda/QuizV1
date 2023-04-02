package com.example.quiz.repositories;

import com.example.quiz.dtos.QuestionForm;
import com.example.quiz.models.Question;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class QuestionRepositoryImpl implements QuestionRepository {
    private static String GET_QUESTION_BY_ID = "SELECT * FROM questions WHERE id=?";
    private static String INSERT_QUESTION = "INSERT INTO questions (text,theme) values (?,?)";

    private ObjectMapper objectMapper;
    private DataSource dataSource;

    public QuestionRepositoryImpl(DataSource dataSource) {
        this.objectMapper = new ObjectMapper();
        this.dataSource = dataSource;
    }

    @Override
    public QuestionForm getQuestion(int number) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_QUESTION_BY_ID);
        ) {
            statement.setInt(1, number);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                try {
                    Optional<Question> question = Optional.of(objectMapper.readValue(rs.getString
                            ("text"), Question.class));
                   Question question1;
                    if (question.isPresent()) {
                        question1=question.get();
                    }
                    else{question1=new Question();}
                    QuestionForm questionForm=QuestionForm.builder().
                            text(question1.getText()).
                            theme(question1.getTheme()).
                            answers(question1.getAnswers()).build();
                    return questionForm;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    return     new QuestionForm();
    }

    @Override
    public void saveQuestion(Question question) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUESTION, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, objectMapper.writeValueAsString(question));
            statement.setString(2, question.getTheme());
            int result = statement.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Запись вопроса не удалась");
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                question.setId(resultSet.getInt("id"));

            } else {
                throw new SQLException("Невозможно получить ID из БД");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
