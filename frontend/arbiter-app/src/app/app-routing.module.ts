import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoanEditComponent } from './loan/loan-edit/loan-edit.component';

const routes: Routes = [
  {
    path: '',
    component: LoanEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
