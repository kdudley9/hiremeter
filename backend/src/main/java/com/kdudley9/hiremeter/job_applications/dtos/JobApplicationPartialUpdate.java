package com.kdudley9.hiremeter.job_applications.dtos;

import com.kdudley9.hiremeter.job_applications.enums.InterviewStage;
import com.kdudley9.hiremeter.job_applications.enums.JobApplicationResponse;
import com.kdudley9.hiremeter.job_applications.enums.OfferStatus;

public class JobApplicationPartialUpdate {
    private JobApplicationResponse applicationResponse;
    private InterviewStage interviewStage;
    private OfferStatus offerStatus;

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

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }
}
