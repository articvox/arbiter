import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoanEditComponent } from './loan-edit/loan-edit.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [LoanEditComponent],
  imports: [
    CommonModule,
    FormsModule
  ]
})
export class LoanModule {
}
