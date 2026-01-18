package com.rohit.quizapp.controller;

import com.rohit.quizapp.model.Question;
import com.rohit.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class questionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> allQuestions() {
            return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) {return questionService.getAllQuestionsByCategory(category);}

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {return questionService.addQuestion(question);}

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        return questionService.deleteQuestionById(id);
    }

}
