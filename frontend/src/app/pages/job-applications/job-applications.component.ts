import { Component, OnInit } from '@angular/core';
import { JobApplication } from '../../models/JobApplication'
import { JobApplicationsService } from '../../services/job-applications.service';
import { CommonModule } from '@angular/common';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-job-applications',
  standalone: true,
  imports: [
    CommonModule,
    MatIcon,
    MatIconModule,
    MatButtonModule,
    RouterModule
  ],
  templateUrl: './job-applications.component.html',
  styleUrl: './job-applications.component.css'
})
export class JobApplicationsComponent implements OnInit {
  jobApplications: JobApplication[];

  constructor(private jobApplicationsService: JobApplicationsService) {
    this.jobApplications = [];
  }

  ngOnInit(): void {
    this.jobApplicationsService.getAllJobApplications().subscribe(data => {
      this.jobApplications = data;
    });
  }

  deleteJobApplication(id: number): void {
    this.jobApplicationsService.deleteJobApplication(id).subscribe();
  }

  navigateToJobPost(url: string): void {
    if (url) {
      window.open(url);
    }
  }
}
