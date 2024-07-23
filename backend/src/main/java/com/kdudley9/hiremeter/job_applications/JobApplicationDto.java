package com.kdudley9.hiremeter.job_applications;

import com.kdudley9.hiremeter.job_applications.enums.InterviewStage;
import com.kdudley9.hiremeter.job_applications.enums.JobApplicationResponse;
import com.kdudley9.hiremeter.job_applications.enums.OfferStatus;

public class JobApplicationDto {
  private long applicationId;
  private String company;
  private String role;
  private String linkToJobPost;
  private String applicationDate;
  private JobApplicationResponse applicationResponse;
  private InterviewStage interviewStage;
  private OfferStatus offerStatus;

  public JobApplicationDto() {
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
