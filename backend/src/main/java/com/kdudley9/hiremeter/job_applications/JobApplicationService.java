package com.kdudley9.hiremeter.job_applications;

import com.kdudley9.hiremeter.job_applications.dtos.DashboardDto;
import com.kdudley9.hiremeter.job_applications.dtos.JobApplicationDto;
import com.kdudley9.hiremeter.job_applications.dtos.JobApplicationPartialUpdate;
import com.kdudley9.hiremeter.job_applications.enums.InterviewStage;
import com.kdudley9.hiremeter.job_applications.enums.JobApplicationResponse;
import com.kdudley9.hiremeter.job_applications.enums.OfferStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {
  final JobApplicationRepository jobApplicationRepository;
  final ModelMapper modelMapper;

  public JobApplicationService(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
    this.jobApplicationRepository = jobApplicationRepository;
    this.modelMapper = modelMapper;
  }

  public JobApplicationDto getJobApplication(Long id) {
    JobApplication jobApplication = jobApplicationRepository.findById(id)
        .orElseThrow(IllegalArgumentException::new);
    return modelMapper.map(jobApplication, JobApplicationDto.class);
  }

  public List<JobApplicationDto> getAllJobApplications() {
    List<JobApplication> jobApplications = jobApplicationRepository.findAllByOrderByApplicationIdDesc();
    return jobApplications.stream()
        .map(jobApplication -> modelMapper.map(jobApplication, JobApplicationDto.class))
        .collect(Collectors.toList());
  }

  public DashboardDto getDashboardAnalytics() {
    DashboardDto dashboard = new DashboardDto();
    dashboard.setApplicationCount(jobApplicationRepository.count());
    dashboard.setAppsSentToday(jobApplicationRepository.countByApplicationDate(LocalDate.now()));
    dashboard.setOfferCount(jobApplicationRepository.countByOfferStatusEquals(OfferStatus.YES));
    dashboard.setSuccessRate(calculateSuccessRate());
    dashboard.setApplicationResponseCount(calculateApplicationResponseCount());
    dashboard.setInterviewStageCount(calculateInterviewStageCount());
    return dashboard;
  }
  public JobApplicationDto createJobApplication(JobApplicationDto jobApplicationDto) {
    JobApplication jobApplicationRequest = modelMapper.map(jobApplicationDto, JobApplication.class);
    JobApplication jobApplication = jobApplicationRepository.save(jobApplicationRequest);
    return modelMapper.map(jobApplication, JobApplicationDto.class);
  }

  public JobApplicationDto updateJobApplication(JobApplicationDto jobApplicationDto, Long id) {
    JobApplication jobApplicationRequest = modelMapper.map(jobApplicationDto, JobApplication.class);
    JobApplication existingJobApplication = jobApplicationRepository.findById(id)
        .orElseThrow(IllegalArgumentException::new);
    existingJobApplication.setCompany(jobApplicationRequest.getCompany());
    existingJobApplication.setRole(jobApplicationRequest.getRole());
    existingJobApplication.setLinkToJobPost(jobApplicationRequest.getLinkToJobPost());
    existingJobApplication.setApplicationDate(jobApplicationRequest.getApplicationDate());
    existingJobApplication.setApplicationResponse(jobApplicationRequest.getApplicationResponse());
    existingJobApplication.setInterviewStage(jobApplicationRequest.getInterviewStage());
    existingJobApplication.setOfferStatus(jobApplicationRequest.getOfferStatus());
    JobApplication updatedJobApplication = jobApplicationRepository.save(existingJobApplication);
    return modelMapper.map(updatedJobApplication, JobApplicationDto.class);
  }

  public JobApplicationDto partialUpdateJobApplication(JobApplicationPartialUpdate jobApplication, Long id) {
    JobApplication existingJobApplication = jobApplicationRepository.findById(id)
        .orElseThrow(IllegalArgumentException::new);
    if (jobApplication.getApplicationResponse() != null) {
      existingJobApplication.setApplicationResponse(jobApplication.getApplicationResponse());
    }

    if (jobApplication.getInterviewStage() != null) {
      existingJobApplication.setInterviewStage(jobApplication.getInterviewStage());
    }

    if (jobApplication.getOfferStatus() != null) {
      existingJobApplication.setOfferStatus(jobApplication.getOfferStatus());
    }
    JobApplication updatedJobApplication = jobApplicationRepository.save(existingJobApplication);
    return modelMapper.map(updatedJobApplication, JobApplicationDto.class);
  }

  public void deleteAllJobApplications() {
    jobApplicationRepository.deleteAll();
  }

  public void deleteJobApplication(Long id) {
    jobApplicationRepository.deleteById(id);
  }

  private Map<JobApplicationResponse, Long> calculateApplicationResponseCount() {
    Map<JobApplicationResponse, Long> applicationResponseCount = new HashMap<>();
    for (JobApplicationResponse response : JobApplicationResponse.values()) {
      applicationResponseCount.put(response, jobApplicationRepository.countByApplicationResponse(response));
    }
    return applicationResponseCount;
  }

  private Map<InterviewStage, Long> calculateInterviewStageCount() {
    Map<InterviewStage, Long> interviewStageCount = new HashMap<>();
    for (InterviewStage stage : InterviewStage.values()) {
      interviewStageCount.put(stage, jobApplicationRepository.countByInterviewStage(stage));
    }
    return interviewStageCount;
  }

  private double calculateSuccessRate() {
    DecimalFormat df = new DecimalFormat("0.0");
    double applicationCount = (double) jobApplicationRepository.count();
    long acceptedCount = jobApplicationRepository.countByApplicationResponse(JobApplicationResponse.ACCEPTED);
    double successRate = (acceptedCount / applicationCount) * 100;
    return Double.parseDouble(df.format(successRate));
  }
}
