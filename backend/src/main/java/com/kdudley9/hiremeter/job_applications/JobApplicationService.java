package com.kdudley9.hiremeter.job_applications;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
