import { Component, OnInit } from '@angular/core';
import { Request } from '../request';
import { LoanService } from '../loan.service';
import { Observable } from 'rxjs';
import { Proposal } from '../proposal';

@Component({
  selector: 'app-loan-edit',
  templateUrl: './loan-edit.component.html',
  styleUrls: ['./loan-edit.component.scss']
})
export class LoanEditComponent implements OnInit {
  data: Request = new Request();
  proposal: Proposal | undefined;

  constructor(private loanService: LoanService) {
  }

  ngOnInit(): void {
  }

  submit(): void {
    this.loanService.request(this.data).subscribe(
      response => {
        this.proposal = response;
      }
    );
  }

}
