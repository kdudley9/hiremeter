import { ChangeDetectionStrategy, Component, OnInit, inject, ViewChild } from '@angular/core';
import { JobApplication } from '../../models/JobApplication'
import { JobApplicationsService } from '../../services/job-applications.service';
import { CommonModule } from '@angular/common';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button'
import { MatSelectModule } from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { MatTable, MatTableModule } from '@angular/material/table';
import { Observable } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { ApplicationResponse } from '../../enums/ApplicationResponse';
import { InterviewStage } from '../../enums/InterviewStage';
import { OfferStatus } from '../../enums/OfferStatus';
import { MatDialog } from '@angular/material/dialog';
import { AddJobApplicationFormComponent } from '../../components/add-job-application-form/add-job-application-form.component';

@Component({
  selector: 'app-job-applications',
  standalone: true,
  imports: [
    CommonModule,
    MatIcon,
    MatIconModule,
    MatButtonModule,
    RouterModule,
    FormsModule,
    MatSelectModule,
    MatTableModule
  ],
  templateUrl: './job-applications.component.html',
  styleUrl: './job-applications.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class JobApplicationsComponent implements OnInit {
  readonly dialog = inject(MatDialog)
  jobApplications: JobApplication[] = [];
  applicationResponse = ApplicationResponse;
  interviewStage = InterviewStage;
  offerStatus = OfferStatus;
  displayedColumns: string[] = ['job-post', 'company', 'role', 'response', 'stage', 'offer', 'date', 'edit', 'delete'];

  constructor(private jobApplicationsService: JobApplicationsService) { }

  ngOnInit(): void {
    this.getAllJobapplications();
  }

  getAllJobapplications(): void {
    this.jobApplicationsService.getAllJobApplications().subscribe((data) => {
      this.jobApplications = data;
    });
  }

  onResponseChange(application: JobApplication, newResponse: ApplicationResponse): void {
    this.jobApplicationsService.updateApplicationResponse(newResponse, application.applicationId).subscribe();
  }

  onInterviewStageChange(application: JobApplication, newInterviewStage: InterviewStage): void {
    this.jobApplicationsService.updateInterviewStage(newInterviewStage, application.applicationId).subscribe();
  }

  onOfferStatusChange(application: JobApplication, newOfferStatus: OfferStatus): void {
    this.jobApplicationsService.updateOfferStatus(newOfferStatus, application.applicationId).subscribe();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AddJobApplicationFormComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  deleteJobApplication(id: number | undefined): void {
    this.jobApplicationsService.deleteJobApplication(id).subscribe();
  }

  deleteAllJobApplications(): void {
    this.jobApplicationsService.deleteAllJobApplications().subscribe();
  }

  navigateToJobPost(url: string): void {
    if (url) {
      window.open(url);
    }
  }
}
