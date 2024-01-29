package com.example.moodle.controller;

import com.example.moodle.model.*;
import com.example.moodle.repository.ChoiceRepository;
import com.example.moodle.repository.CoursePlanRepository;
import com.example.moodle.repository.PersonRepository;
import com.example.moodle.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ExamController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private  ChoiceService choiceService;


    @GetMapping("/show-exam/{user_id}/{exam_id}/")
    public String showExam(@PathVariable("user_id") Long user_id, @PathVariable("exam_id")Long exam_id,
                            Model model){

        Account account=accountService.findByID(user_id);
        List<Question> questions=questionService.findByExamID(exam_id);
        model.addAttribute("questions",questions);
        model.addAttribute("user",account);
        return "exam";

    }

    @PostMapping("/submit-exam/{user_id}/{question_id}")
    public String submitExam(@PathVariable("user_id") Long user_id,
                                 @PathVariable("question_id") Long question_id,
                                 @RequestParam("choice_ids") Long choice_id,
                                 @RequestParam("index") Integer index,
                                 Model model){

        Question question=questionService.findById(question_id);
        Choice choice= choiceService.findById(choice_id);

        SubmittedAnswer submittedAnswer=new SubmittedAnswer();
        submittedAnswer.setQuestion(question);
        submittedAnswer.setSubmitter(accountService.findByID(user_id));
        submittedAnswer.setUserChoice(choice);
        submittedAnswer.

    }


}