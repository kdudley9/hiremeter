import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobApplication } from '../models/JobApplication';

@Injectable({
  providedIn: 'root'
})
export class JobApplicationsService {
  readonly baseUrl = 'http://localhost:3000/job-applications';

  constructor(private http: HttpClient) { }

  getJobApplications(): Observable<JobApplication[]> {
    return this.http.get<JobApplication[]>(`${this.baseUrl}`);
  }

  getJobApplication(id: number): Observable<JobApplication> {
    return this.http.get<JobApplication>(`${this.baseUrl}/${id}`);
  }
}
