import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddJobApplicationFormComponent } from './add-job-application-form.component';

describe('AddJobApplicationFormComponent', () => {
  let component: AddJobApplicationFormComponent;
  let fixture: ComponentFixture<AddJobApplicationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddJobApplicationFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddJobApplicationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
