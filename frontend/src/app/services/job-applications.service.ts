import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IJobApplication } from '../models/IJobApplication';

@Injectable({
  providedIn: 'root'
})
export class JobApplicationsService {
  readonly baseUrl = 'http://localhost:3000/job-applications';

  constructor(private http: HttpClient) { }

  getJobApplications(): Observable<IJobApplication[]> {
    return this.http.get<IJobApplication[]>(`${this.baseUrl}`);
  }

  getJobApplication(id: number): Observable<IJobApplication> {
    return this.http.get<IJobApplication>(`${this.baseUrl}/${id}`);
  }
}
