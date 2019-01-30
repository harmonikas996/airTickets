import { Component, OnInit } from '@angular/core';
import { BranchOffice } from 'src/app/shared/model/rentacar/branchOffice.model';
import { BranchService } from 'src/app/shared/services/rentacar/branch.service';
import { Location } from '@angular/common';

@Component({
  selector: 'branches-list',
  templateUrl: './branches-list.component.html',
  styleUrls: ['./branches-list.component.css']
})
export class BranchesListComponent implements OnInit {

  branches: BranchOffice[];
  branch: BranchOffice;

  constructor(private branchService: BranchService, private location: Location) { }

  ngOnInit() {
    // srediti preuzimanje ID-a tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.getBranchesByRentACarId();
    
  }

  getBranchesByRentACarId(): void {
    // dobaviti samo one filijale koja pripadaju rentacar servisu sa 'id' koji je prosledjen
    this.branchService.getBranches().subscribe(branches => this.branches = branches);
  }

  onRemove(branch: BranchOffice): void {
    this.branches = this.branches.filter(b => b !== branch);
    this.branchService.removeBranch(branch.id).subscribe(branch => this.branch = branch);
  }

}
