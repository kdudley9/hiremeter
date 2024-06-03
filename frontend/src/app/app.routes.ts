import { Routes } from '@angular/router';
import { JobApplicationsComponent } from './components/job-applications/job-applications.component';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';

export const routes: Routes = [
    { path: 'applications', component: JobApplicationsComponent, title: 'Applications - Hiremeter' },
    { path: 'home', component: LandingPageComponent, title: 'Home - Hiremeter' },
    { path: '', redirectTo: '/home', pathMatch: 'full' }
];
