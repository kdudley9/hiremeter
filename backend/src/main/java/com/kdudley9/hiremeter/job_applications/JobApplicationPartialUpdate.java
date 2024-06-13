package com.kdudley9.hiremeter.job_applications;

import com.kdudley9.hiremeter.job_applications.enums.InterviewStage;
import com.kdudley9.hiremeter.job_applications.enums.JobApplicationResponse;

public class JobApplicationPartialUpdate {
    private JobApplicationResponse applicationResponse;
    private InterviewStage interviewStage;

    public JobApplicationPartialUpdate() {

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
}
