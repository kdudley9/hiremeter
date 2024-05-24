package com.kdudley9.hiremeter.job_applications;

import com.kdudley9.hiremeter.job_applications.enums.JobApplicationResponse;
import com.kdudley9.hiremeter.job_applications.enums.InterviewStage;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private long applicationId;
    @NotBlank(message = "Company name cannot be blank")
    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    @Column(length = 100)
    private String company;
    @NotBlank(message = "Role cannot be blank")
    @Size(max = 75, message = "Role cannot exceed 75 characters")
    private String role;
    @Min(value = 0, message = "Pay Rate should not be less than zero")
    @Max(value = 999999, message = "Pay Rate should not be greater than 999999")
    @Column(name = "pay_rate")
    private int payRate;
    @NotBlank(message = "Link to job post or company website cannot be blank")
    @Size(max = 300, message = "Link cannot exceed 300 characters")
    @Column(name = "link_to_job_post", length = 300)
    private String linkToJobPost;
    @NotBlank(message = "Application date cannot be blank")
    @Column(name = "application_date")
    private String applicationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "application_response")
    private JobApplicationResponse applicationResponse;
    @Enumerated(EnumType.STRING)
    @Column(name = "interview_stage")
    private InterviewStage interviewStage;
    @Column(name = "received_offer")
    private boolean receivedOffer;

    public JobApplication() {
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPayRate() {
        return payRate;
    }

    public void setPayRate(int payRate) {
        this.payRate = payRate;
    }

    public String getLinkToJobPost() {
        return linkToJobPost;
    }

    public void setLinkToJobPost(String linkToJobPost) {
        this.linkToJobPost = linkToJobPost;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public JobApplicationResponse getApplicationResponse() {
        return applicationResponse;
    }

    public void setApplicationResponse(JobApplicationResponse applicationResponse) {
        this.applicationResponse = applicationResponse;
    }

    public InterviewStage getInterviewStage() {
        return interviewStage;
    }

    public void setInterviewStage(InterviewStage interviewStage) {
        this.interviewStage = interviewStage;
    }

    public boolean isReceivedOffer() {
        return receivedOffer;
    }

    public void setReceivedOffer(boolean receivedOffer) {
        this.receivedOffer = receivedOffer;
    }
}
