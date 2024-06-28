import { Component, OnInit, inject } from '@angular/core';
import { FormGroup, FormControl, FormsModule, ReactiveFormsModule, Validators, FormBuilder } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { JobApplicationsService } from '../../services/job-applications.service';

@Component({
  selector: 'app-add-job-application-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
  ],
  templateUrl: './add-job-application-form.component.html',
  styleUrl: './add-job-application-form.component.css',
})
export class AddJobApplicationFormComponent implements OnInit {
  readonly dialogRef = inject(MatDialogRef<AddJobApplicationFormComponent>);
  addJobApplicationForm: FormGroup = new FormGroup({});
  linkValidation = '^(http|https):\/\/[^\s/$.?#].[^\s]*$';

  constructor(private jobApplicationsService: JobApplicationsService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.addJobApplicationForm = this.fb.group({
      company: ['', [Validators.required, this.noWhitespaceValidator]],
      role: ['', [Validators.required, this.noWhitespaceValidator]],
      linkToJobPost: ['', [
        Validators.required,
        this.noWhitespaceValidator,
        Validators.pattern(this.linkValidation)]
      ],
      applicationDate: ['', [Validators.required, this.noWhitespaceValidator]]
    });
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

  addJobApplication(): void {
    this.jobApplicationsService.addJobApplication(this.addJobApplicationForm.value).subscribe();
  }

  noWhitespaceValidator(control: FormControl) {
    const isWhiteSpace = (control && control.value && control.value.toString() || '').trim().length === 0;
    const isValid = !isWhiteSpace;
    return isValid ? null : { 'whitespace': true };
  }

  get company() {
    return this.addJobApplicationForm?.get('company');
  }

  get role() {
    return this.addJobApplicationForm?.get('role');
  }

  get linkToJobPost() {
    return this.addJobApplicationForm?.get('linkToJobPost');
  }

  get applicationDate() {
    return this.addJobApplicationForm?.get('applicationDate');
  }
}
