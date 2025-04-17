package com.talentstream.dto;

import java.util.List;


public class ApplicantDailyTestResultDto {

	private Byte questionNumber;
	private String question;
	private List<String> options;
	private String correctAnswer;
	private String selectedAnswer;
	

	public Byte getQuestionNumber() {
		return questionNumber;
	}


	public void setQuestionNumber(Byte questionNumber) {
		this.questionNumber = questionNumber;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public List<String> getOptions() {
		return options;
	}


	public void setOptions(List<String> options) {
		this.options = options;
	}


	public String getCorrectAnswer() {
		return correctAnswer;
	}


	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}


	public String getSelectedAnswer() {
		return selectedAnswer;
	}


	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	
	
}
