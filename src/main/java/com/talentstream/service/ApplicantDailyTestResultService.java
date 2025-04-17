package com.talentstream.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentstream.dto.ApplicantDailyTestResultDto;
import com.talentstream.dto.ApplicantDailyTestSubmitDto;
import com.talentstream.dto.ApplicantDailyTestSummaryDto;
import com.talentstream.entity.Applicant;
import com.talentstream.entity.ApplicantDailyTestData;
import com.talentstream.entity.ApplicantDailyTestResult;
import com.talentstream.repository.ApplicantDailyTestResultRepository;
import com.talentstream.repository.ApplicantRepository;

@Service
public class ApplicantDailyTestResultService {

	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private ApplicantDailyTestResultRepository dailyTestResultRepo;
	
	//get test data submited from front end
	public void submitDailyTest(ApplicantDailyTestSubmitDto dto) {
		
		try {
			
			
			System.out.println("Applicant ID: " + dto.getApplicantId());

			
			Applicant applicant = applicantRepo.findById(dto.getApplicantId())
					.orElseThrow(() -> new RuntimeException("applicant not found"));
			
			ApplicantDailyTestResult result = new ApplicantDailyTestResult();
	        result.setTestDate(dto.getTestDate());
	        result.setScore(dto.getScore());
	        result.setApplicant(applicant);
	        
	        List<ApplicantDailyTestData> questions = dto.getTestResult().stream().map(qdto -> {
	            ApplicantDailyTestData q = new ApplicantDailyTestData();
	            q.setQuestionNumber(qdto.getQuestionNumber());
	            q.setQuestion(qdto.getQuestion());
	            q.setOptions(qdto.getOptions());
	            q.setCorrectAnswer(qdto.getCorrectAnswer());
	            q.setSelectedAnswer(qdto.getSelectedAnswer());
	            q.setTestResult(result);
	            return q;
	        }).collect(Collectors.toList());
	        
	        
	        result.setTestData(questions);
	        dailyTestResultRepo.save(result);
	        applicant.getTestResult().add(result);

	        applicantRepo.save(applicant);
	        
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public List<ApplicantDailyTestSummaryDto> getTestSummaries(Long applicantId) {
        return dailyTestResultRepo.findByApplicantId(applicantId).stream()
                .map(r -> new ApplicantDailyTestSummaryDto(r.getTestDate(), r.getScore()))
                .collect(Collectors.toList());
    }

    public List<ApplicantDailyTestResultDto> getTestDetails(Long applicantId, LocalDate date) {
        ApplicantDailyTestResult result = dailyTestResultRepo.findByApplicantIdAndTestDate(applicantId, date)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        return result.getTestData().stream().map(q -> {
            ApplicantDailyTestResultDto dto = new ApplicantDailyTestResultDto();
            dto.setQuestionNumber(q.getQuestionNumber());
            dto.setQuestion(q.getQuestion());
            dto.setOptions(q.getOptions());
            dto.setCorrectAnswer(q.getCorrectAnswer());
            dto.setSelectedAnswer(q.getSelectedAnswer());
            return dto;
        }).collect(Collectors.toList());
    }
	
	
}
