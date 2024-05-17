package com.kdudley9.hiremeter.job_applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-applications")
public class JobApplicationController {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @GetMapping
    public ResponseEntity<List<JobApplication>> getJobApplications() {
        return new ResponseEntity<>(jobApplicationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public JobApplication getJobApplication(@PathVariable Long id) {
        return jobApplicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @PostMapping
    public ResponseEntity<JobApplication> jobApplication(@RequestBody JobApplication jobApplication) {
        return new ResponseEntity<>(jobApplicationRepository.save(jobApplication), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<JobApplication> updateJobApplication(@RequestBody JobApplication updateJobApplication, @PathVariable Long id) {
        return jobApplicationRepository.findById(id)
                .map(jobApplication -> {
                   jobApplication.setApplicationDate(updateJobApplication.getApplicationDate());
                   jobApplication.setApplicationResponse(updateJobApplication.getApplicationResponse());
                   jobApplication.setCompany(updateJobApplication.getCompany());
                   jobApplication.setLinkToJobPost(updateJobApplication.getLinkToJobPost());
                   jobApplication.setPayRate(updateJobApplication.getPayRate());
                   jobApplication.setRole(updateJobApplication.getRole());
                   jobApplication.setInterviewStage(updateJobApplication.getInterviewStage());
                   jobApplication.setReceivedOffer(updateJobApplication.hasReceivedOffer());
                   return new ResponseEntity<>(jobApplicationRepository.save(updateJobApplication), HttpStatus.OK);
                })
                .orElseGet(() -> {
                   updateJobApplication.setApplicationId(id);
                   return new ResponseEntity<>(jobApplicationRepository.save(updateJobApplication), HttpStatus.CREATED);
                });
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteJobApplications() {
        jobApplicationRepository.deleteAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteJobApplication(@PathVariable Long id) {
        jobApplicationRepository.deleteById(id);
    }
}
