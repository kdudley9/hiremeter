import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobApplication } from '../models/JobApplication';

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

  getAllJobApplications(): Observable<JobApplication[]> {
    return this.http.get<JobApplication[]>(`${this.baseUrl}`);
  }

  getJobApplication(id: number): Observable<JobApplication> {
    return this.http.get<JobApplication>(`${this.baseUrl}/${id}`);
  }

  deleteJobApplication(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  deleteAllJobApplications(): Observable<any> {
    return this.http.delete(`${this.baseUrl}`);
  }
}
