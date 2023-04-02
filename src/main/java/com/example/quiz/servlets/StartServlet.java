package com.example.quiz.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.quiz.models.Question;
import com.example.quiz.repositories.QuestionRepository;
import com.example.quiz.repositories.QuestionRepositoryImpl;
import com.example.quiz.services.QuestionService;
import com.example.quiz.services.QuestionServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet("/start")
public class StartServlet extends HttpServlet {
private QuestionService questionService;
private QuestionRepository questionRepository;


    public void init(ServletConfig config) throws ServletException {
        ServletContext context=config.getServletContext();
        DataSource dataSource= (DataSource) context.getAttribute("dataSource");
        questionRepository=new QuestionRepositoryImpl(dataSource);
        questionService=new QuestionServiceImpl(questionRepository);


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
     String item=request.getParameter("item");
     String theme;
     switch (item){
         case "История":{theme=item;break;}
         case "Поп-культура":{theme=item;break;}
         case "Космос":{theme=item;break;}
         case "Спорт":{theme=item;break;}
         default:{
             try {
                 request.getRequestDispatcher("index.jsp").forward(request,response);
             } catch (ServletException e) {
                 throw new RuntimeException(e);
             }
         }
     }

     List<Question> questionList=new ArrayList<>();
     for(int i = 1;i<4;i++){
         questionList.add(questionService.get(i));
     }
request.setAttribute("questionList",questionList);
        try {
            request.getRequestDispatcher("body.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}