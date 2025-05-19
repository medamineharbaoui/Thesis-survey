package com.harbaoui.thesis_survey.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class SurveyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    private String surveyType;
    private int pieceNumber;
    private String pieceFile;

    @ElementCollection
    private List<Answer> answers;

    @Embeddable
    public static class Answer {
        @Column
        private String question;
        @Column
        private String answer;

        public String getQuestion() { return question; }
        public void setQuestion(String question) { this.question = question; }
        public String getAnswer() { return answer; }
        public void setAnswer(String answer) { this.answer = answer; }
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSurveyType() { return surveyType; }
    public void setSurveyType(String surveyType) { this.surveyType = surveyType; }
    public int getPieceNumber() { return pieceNumber; }
    public void setPieceNumber(int pieceNumber) { this.pieceNumber = pieceNumber; }
    public String getPieceFile() { return pieceFile; }
    public void setPieceFile(String pieceFile) { this.pieceFile = pieceFile; }
    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}
