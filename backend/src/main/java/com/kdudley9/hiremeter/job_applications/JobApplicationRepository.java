package com.kdudley9.hiremeter.job_applications;

import com.kdudley9.hiremeter.job_applications.enums.InterviewStage;
import com.kdudley9.hiremeter.job_applications.enums.JobApplicationResponse;
import com.kdudley9.hiremeter.job_applications.enums.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
  List<JobApplication> findAllByOrderByApplicationIdDesc();
  long countByApplicationResponse(JobApplicationResponse applicationResponse);
  long countByInterviewStage(InterviewStage interviewStage);
  long countByApplicationDate(LocalDate date);
  long countByOfferStatusEquals(OfferStatus status);
}
