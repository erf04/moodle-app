package com.example.moodle.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    private CoursePlan coursePlan;

    @ManyToMany
    @JoinTable(
        name = "exam_questions",
        joinColumns = @JoinColumn(name = "exam_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    @ManyToOne
    private Teacher creator;

    @ManyToMany
    @JoinTable(
            name = "exam_participant",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> participants;

    public List<Account> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Account> participants) {
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CoursePlan getCoursePlan() {
        return coursePlan;
    }

    public void setCoursePlan(CoursePlan coursePlan) {
        this.coursePlan = coursePlan;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Teacher getCreator() {
        return this.getCoursePlan().getCreator();
    }

    public void setCreator(Teacher creator) {
        this.creator = creator;
    }
}
