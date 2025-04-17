package com.talentstream.dto;

import java.time.LocalDate;

public class ApplicantDailyTestSummaryDto {

	private LocalDate testDate;
	private int score;
	
	public ApplicantDailyTestSummaryDto(LocalDate testDate, int score) {
		super();
		this.testDate = testDate;
		this.score = score;
	}
	public LocalDate getTestDate() {
		return testDate;
	}
	public void setTestDate(LocalDate testDate) {
		this.testDate = testDate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(Byte score) {
		this.score = score;
	}
	
	
	
}
