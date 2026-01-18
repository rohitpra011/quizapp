package com.rohit.quizapp.controller;

import com.rohit.quizapp.model.QuestionWrapper;
import com.rohit.quizapp.model.Response;
import com.rohit.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class quizController {
    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQue, @RequestParam String title){
        return quizService.createQuiz(category,numQue,title);
    }

    @GetMapping("get")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id,@RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }
}
