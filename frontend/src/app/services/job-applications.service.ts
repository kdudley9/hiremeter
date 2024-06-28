import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobApplication } from '../models/JobApplication';
import { ApplicationResponse } from '../enums/ApplicationResponse';
import { InterviewStage } from '../enums/InterviewStage';
import { OfferStatus } from '../enums/OfferStatus';

@Injectable({
  providedIn: 'root'
})
export class JobApplicationsService {
  readonly baseUrl = '/api/v1/job-applications';

  constructor(private http: HttpClient) { }

  addJobApplication(jobApplication: JobApplication): Observable<JobApplication> {
    return this.http.post<JobApplication>(`${this.baseUrl}`, jobApplication);
  }

  updateJobApplication(jobApplication: JobApplication, id: number): Observable<JobApplication> {
    return this.http.put<JobApplication>(`${this.baseUrl}/${id}`, jobApplication);
  }

  updateApplicationResponse(response: ApplicationResponse, id: number | undefined): Observable<JobApplication> {
    return this.http.patch<JobApplication>(`${this.baseUrl}/${id}`, { applicationResponse: response });
  }

  updateInterviewStage(stage: InterviewStage, id: number | undefined): Observable<JobApplication> {
    return this.http.patch<JobApplication>(`${this.baseUrl}/${id}`, { interviewStage: stage });
  }

  updateOfferStatus(status: OfferStatus, id: number | undefined): Observable<JobApplication> {
    return this.http.patch<JobApplication>(`${this.baseUrl}/${id}`, { offerStatus: status });
  }

  getAllJobApplications(): Observable<JobApplication[]> {
    return this.http.get<JobApplication[]>(`${this.baseUrl}`);
  }

  getJobApplication(id: number): Observable<JobApplication> {
    return this.http.get<JobApplication>(`${this.baseUrl}/${id}`);
  }

  deleteJobApplication(id: number | undefined): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  deleteAllJobApplications(): Observable<any> {
    return this.http.delete(`${this.baseUrl}`);
  }
}
