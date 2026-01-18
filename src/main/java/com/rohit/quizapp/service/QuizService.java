package com.rohit.quizapp.service;

import com.rohit.quizapp.dao.QuestionDao;
import com.rohit.quizapp.dao.QuizDao;
import com.rohit.quizapp.model.Question;
import com.rohit.quizapp.model.Quiz;
import com.rohit.quizapp.model.QuestionWrapper;
import com.rohit.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQue, String title) {
        try {
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQue);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("Quiz created",HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        try {
            Optional <Quiz> quiz=quizDao.findById(id);
            List<Question> questionsFromDB=quiz.get().getQuestions();
            List<QuestionWrapper> questionsForuser =new ArrayList<>();
            for(Question question:questionsFromDB){
                QuestionWrapper qw= new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
                questionsForuser.add(qw);
            }
            return new ResponseEntity<>(questionsForuser,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i= 0;
        for(Response response:responses){
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                right++;
                i++;

            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
