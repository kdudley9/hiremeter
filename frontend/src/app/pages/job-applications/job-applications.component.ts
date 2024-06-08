import { Component, OnInit } from '@angular/core';
import { IJobApplication } from '../../models/IJobApplication'
import { JobApplicationsService } from '../../services/job-applications.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-job-applications',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './job-applications.component.html',
  styleUrl: './job-applications.component.css'
})
export class JobApplicationsComponent implements OnInit {
  jobApplications: IJobApplication[];

  constructor(private jobApplicationsService: JobApplicationsService) {
    this.jobApplications = [];
  }

  ngOnInit(): void {
    this.jobApplicationsService.getJobApplications().subscribe(data => {
      this.jobApplications = data;
    });
  }
}