package com.kdudley9.hiremeter.job_applications.dtos;

import com.kdudley9.hiremeter.job_applications.enums.InterviewStage;
import com.kdudley9.hiremeter.job_applications.enums.JobApplicationResponse;

import java.util.HashMap;
import java.util.Map;

public class DashboardDto {
  private long applicationCount;
  private long appsSentToday;
  private long offerCount;
  private double successRate;
  private Map<JobApplicationResponse, Long> applicationResponseCount;
  private Map<InterviewStage, Long> interviewStageCount;

  public DashboardDto() {
    this.applicationResponseCount = new HashMap<>();
    this.interviewStageCount = new HashMap<>();
  }

  public long getApplicationCount() {
    return applicationCount;
  }

  public void setApplicationCount(long applicationCount) {
    this.applicationCount = applicationCount;
  }

  public long getAppsSentToday() {
    return appsSentToday;
  }

  public void setAppsSentToday(long appsSentToday) {
    this.appsSentToday = appsSentToday;
  }

  public long getOfferCount() {
    return offerCount;
  }

  public void setOfferCount(long offerCount) {
    this.offerCount = offerCount;
  }

  public double getSuccessRate() {
    return successRate;
  }

  public void setSuccessRate(double successRate) {
    this.successRate = successRate;
  }

  public Map<JobApplicationResponse, Long> getApplicationResponseCount() {
    return applicationResponseCount;
  }

  public void setApplicationResponseCount(Map<JobApplicationResponse, Long> applicationResponseCount) {
    this.applicationResponseCount = applicationResponseCount;
  }

  public Map<InterviewStage, Long> getInterviewStageCount() {
    return interviewStageCount;
  }

  public void setInterviewStageCount(Map<InterviewStage, Long> interviewStageCount) {
    this.interviewStageCount = interviewStageCount;
  }
}
