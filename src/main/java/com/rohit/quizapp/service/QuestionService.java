package com.rohit.quizapp.service;
import com.rohit.quizapp.model.Question;
import com.rohit.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> q = questionDao.findAll();
            return new ResponseEntity<>(q, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
        try {
            List<Question> q = questionDao.findByCategory(category);
            return new ResponseEntity<>(q, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
       try {
           questionDao.deleteById(id);
           return new ResponseEntity<>("Question deleted successfully",HttpStatus.OK);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
