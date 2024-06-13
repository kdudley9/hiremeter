package com.kdudley9.hiremeter.job_applications;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-applications")
@Validated
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
    public ResponseEntity<JobApplication> jobApplication(@Valid @RequestBody JobApplication jobApplication) {
        return new ResponseEntity<>(jobApplicationRepository.save(jobApplication), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<JobApplication> updateJobApplication(@Valid @RequestBody JobApplication updateJobApplication, @PathVariable Long id) {
        return jobApplicationRepository.findById(id)
                .map(existingJobApplication -> {
                   existingJobApplication.setApplicationDate(updateJobApplication.getApplicationDate());
                   existingJobApplication.setApplicationResponse(updateJobApplication.getApplicationResponse());
                   existingJobApplication.setCompany(updateJobApplication.getCompany());
                   existingJobApplication.setLinkToJobPost(updateJobApplication.getLinkToJobPost());
                   existingJobApplication.setRole(updateJobApplication.getRole());
                   existingJobApplication.setInterviewStage(updateJobApplication.getInterviewStage());
                   existingJobApplication.setReceivedOffer(updateJobApplication.isReceivedOffer());
                   return new ResponseEntity<>(jobApplicationRepository.save(existingJobApplication), HttpStatus.OK);
                })
                .orElseGet(() -> {
                   updateJobApplication.setApplicationId(id);
                   return new ResponseEntity<>(jobApplicationRepository.save(updateJobApplication), HttpStatus.CREATED);
                });
    }

    @PatchMapping("{id}")
    public ResponseEntity<JobApplication> partialUpdateJobApplication(@RequestBody JobApplicationPartialUpdate updateJobApplication, @PathVariable Long id) {
        return jobApplicationRepository.findById(id)
                .map(existingJobApplication -> {
                    if (updateJobApplication.getApplicationResponse() != null) {
                        existingJobApplication.setApplicationResponse(updateJobApplication.getApplicationResponse());
                    }

                    if (updateJobApplication.getInterviewStage() != null) {
                        existingJobApplication.setInterviewStage(updateJobApplication.getInterviewStage());
                    }
                    return new ResponseEntity<>(jobApplicationRepository.save(existingJobApplication), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping
    public void deleteJobApplications() {
        jobApplicationRepository.deleteAll();
    }

    @DeleteMapping("{id}")
    public void deleteJobApplication(@PathVariable Long id) {
        jobApplicationRepository.deleteById(id);
    }
}
