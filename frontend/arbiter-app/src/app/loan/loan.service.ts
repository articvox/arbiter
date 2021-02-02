import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Request } from './request';
import { Observable } from 'rxjs';
import { Proposal } from './proposal';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  constructor(private httpClient: HttpClient) {
  }

  public request(request: Request): Observable<Proposal> {
    return this.httpClient.post<Proposal>('http://localhost:8080/arbitrator/negotiate', request);
  }
}
