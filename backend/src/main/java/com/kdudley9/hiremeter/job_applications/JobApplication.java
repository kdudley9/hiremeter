package com.kdudley9.hiremeter.job_applications;

import com.kdudley9.hiremeter.job_applications.enums.JobApplicationResponse;
import com.kdudley9.hiremeter.job_applications.enums.InterviewStage;
import com.kdudley9.hiremeter.job_applications.enums.OfferStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

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
    @NotBlank(message = "Link to job post or company website cannot be blank")
    @URL
    @Column(name = "link_to_job_post")
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
    @Enumerated(EnumType.STRING)
    @Column(name = "offer_status")
    private OfferStatus offerStatus;

    public JobApplication() {
        this.applicationResponse = JobApplicationResponse.NO_RESPONSE;
        this.interviewStage = InterviewStage.NO_INTERVIEW;
        this.offerStatus = OfferStatus.NO;
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

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }
}
