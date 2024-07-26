package com.kdudley9.hiremeter.job_applications;

import com.kdudley9.hiremeter.job_applications.dtos.DashboardDto;
import com.kdudley9.hiremeter.job_applications.dtos.JobApplicationDto;
import com.kdudley9.hiremeter.job_applications.dtos.JobApplicationPartialUpdate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-applications")
@Validated
public class JobApplicationController {
  private final JobApplicationService jobApplicationService;

  public JobApplicationController(JobApplicationService jobApplicationService) {
    this.jobApplicationService = jobApplicationService;
  }

  @GetMapping
  public ResponseEntity<List<JobApplicationDto>> getAllJobApplications() {
    return new ResponseEntity<>(jobApplicationService.getAllJobApplications(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<JobApplicationDto> getJobApplication(@PathVariable Long id) {
    return new ResponseEntity<>(jobApplicationService.getJobApplication(id), HttpStatus.OK);
  }

  @GetMapping("/dashboard")
  public ResponseEntity<DashboardDto> getDashboardAnalytics() {
    return new ResponseEntity<>(jobApplicationService.getDashboardAnalytics(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<JobApplicationDto> createJobApplication(@Valid @RequestBody JobApplicationDto jobApplication) {
    return new ResponseEntity<>(jobApplicationService.createJobApplication(jobApplication), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<JobApplicationDto> updateJobApplication(@Valid @RequestBody JobApplicationDto jobApplication, @PathVariable Long id) {
    return new ResponseEntity<>(jobApplicationService.updateJobApplication(jobApplication, id), HttpStatus.OK);
  }

  @PatchMapping("{id}")
  public ResponseEntity<JobApplicationDto> partialUpdateJobApplication(@RequestBody JobApplicationPartialUpdate jobApplication, @PathVariable Long id) {
    return new ResponseEntity<>(jobApplicationService.partialUpdateJobApplication(jobApplication, id), HttpStatus.OK);
  }

  @DeleteMapping
  public void deleteJobApplications() {
    jobApplicationService.deleteAllJobApplications();
  }

  @DeleteMapping("{id}")
  public void deleteJobApplication(@PathVariable Long id) {
    jobApplicationService.deleteJobApplication(id);
  }
}
