package com.example.springai.entity;
//@Data -> Lombok
public record Answer(String answer){
}
/*
public class Answer {
    private String answer;
    public Answer(){  }

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                '}';
    }
}
*/
