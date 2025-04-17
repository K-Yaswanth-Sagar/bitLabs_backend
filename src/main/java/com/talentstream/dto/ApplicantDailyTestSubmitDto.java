package com.talentstream.dto;

import java.time.LocalDate;
import java.util.List;

public class ApplicantDailyTestSubmitDto {

	private Long applicantId;
	private int score;
	private LocalDate testDate;
	private List<ApplicantDailyTestResultDto> testResult;
	public ApplicantDailyTestSubmitDto(Long applicantId, int score, LocalDate testDate,
			List<ApplicantDailyTestResultDto> testResult) {
		super();
		this.applicantId = applicantId;
		this.score = score;
		this.testDate = testDate;
		this.testResult = testResult;
	}
	public ApplicantDailyTestSubmitDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public LocalDate getTestDate() {
		return testDate;
	}
	public void setTestDate(LocalDate testDate) {
		this.testDate = testDate;
	}
	public List<ApplicantDailyTestResultDto> getTestResult() {
		return testResult;
	}
	public void setTestResult(List<ApplicantDailyTestResultDto> testResult) {
		this.testResult = testResult;
	}
	@Override
	public String toString() {
		return "ApplicantDailyTestSubmitDto [applicantId=" + applicantId + ", score=" + score + ", testDate=" + testDate
				+ ", testResult=" + testResult + "]";
	}
	
	
	
	
	
	
	
	
	
}
