import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { BranchOffice } from '../../model/rentacar/branchOffice.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  private branchesUrl = 'http://localhost:8080/branchoffices';

  constructor(
    private http: HttpClient
  ) { }

  getBranches(): Observable<BranchOffice[]> {
    return this.http.get<BranchOffice[]>(this.branchesUrl + '/all');
  }

  getBranchById(id: number): Observable<BranchOffice> {
    return this.http.get<BranchOffice>(this.branchesUrl + '/' + id);
  }
  
  // TO DO
  getBranchesByRentACarId(id: number): Observable<BranchOffice[]> {
    return this.http.get<BranchOffice[]>(this.branchesUrl + '/branchOfficesFromRentacar?id=' + id);
  }

  addBranch(branch: BranchOffice): Observable<Object> {
    return this.http.post<BranchOffice>(this.branchesUrl + '/new', branch, httpOptions);
  }

  updateBranch(branch: BranchOffice): Observable<Object> {
    return this.http.put<BranchOffice>(this.branchesUrl + '/' + branch.id + '/update', branch, httpOptions);
  }

  removeBranch(branch: BranchOffice | number): Observable<BranchOffice> {
    const id = typeof branch === 'number' ? branch : branch.id;
    return this.http.delete<BranchOffice>(this.branchesUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted branch id=${id}`))
    );
  }
}