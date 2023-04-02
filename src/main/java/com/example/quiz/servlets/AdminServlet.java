package com.example.quiz.servlets;

import com.example.quiz.dtos.QuestionForm;
import com.example.quiz.models.Answer;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.repositories.QuestionRepositoryImpl;
import com.example.quiz.services.QuestionService;
import com.example.quiz.services.QuestionServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private QuestionService questionService;
    private QuestionRepository questionRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        DataSource dataSource = (DataSource) context.getAttribute("dataSource");
        questionRepository = new QuestionRepositoryImpl(dataSource);
        questionService = new QuestionServiceImpl(questionRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String theme = req.getParameter("theme");
        String question = req.getParameter("question");
        ArrayList<Answer> answers = new ArrayList<>();

        String answer1s = req.getParameter("answer1");
        String answer2s = req.getParameter("answer2");
        String answer3s = req.getParameter("answer3");

        if (answer1s != "" & answer2s != "" & answer3s != "") {
            boolean a1 = false;
            boolean a2 = false;
            boolean a3 = false;
            switch (req.getParameter("tr")) {
                case "a": {
                    a1 = true;
                    break;
                }
                case "b": {
                    a2 = true;
                    break;
                }
                case "c": {
                    a3 = true;
                    break;
                }
            }


            Answer answer1 = new Answer(answer1s, a1);
            Answer answer2 = new Answer(answer2s, a2);
            Answer answer3 = new Answer(answer3s, a3);
            answers.add(answer1);
            answers.add(answer2);
            answers.add(answer3);
        }


        QuestionForm questionForm = QuestionForm.builder().
                text(question).
                answers(answers).
                theme(theme).
                build();
        questionService.save(questionForm);
    }
}
