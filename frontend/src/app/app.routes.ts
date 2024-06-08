import { Routes } from '@angular/router';
import { JobApplicationsComponent } from './pages/job-applications/job-applications.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { LayoutComponent } from './pages/layout/layout.component';

export const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            { path: 'applications', component: JobApplicationsComponent, title: 'Applications - Hiremeter' },
            { path: '', redirectTo: '/home', pathMatch: 'full' }
        ]
    },
    { path: 'home', component: LandingPageComponent, title: 'Home - Hiremeter' },
];
