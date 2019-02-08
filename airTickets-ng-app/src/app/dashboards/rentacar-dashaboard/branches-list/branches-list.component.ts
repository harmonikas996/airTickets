import { Component, OnInit } from '@angular/core';
import { BranchOffice } from 'src/app/shared/model/rentacar/branchOffice.model';
import { BranchService } from 'src/app/shared/services/rentacar/branch.service';
import { Location } from '@angular/common';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';

@Component({
  selector: 'branches-list',
  templateUrl: './branches-list.component.html',
  styleUrls: ['./branches-list.component.css']
})
export class BranchesListComponent implements OnInit {

  branches: BranchOffice[];
  branch: BranchOffice;
  rentacar: RentACar;

  constructor(private branchService: BranchService, private location: Location, private token: TokenStorageService, private rentacarService: RentacarService) { }

  ngOnInit() {
    // srediti preuzimanje ID-a tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.getCompanyId();
    
  }

  getCompanyId() {

    this.rentacarService.getRentacarByAdminUsername(this.token.getUsername()).subscribe(
      data => this.rentacar = data,
      error => console.log('Error: ', error),
      () => {
        this.getBranchesByRentACarId();
      }
    );
  }

  getBranchesByRentACarId(): void {
    // dobaviti samo ona vozila koja pripadaju rentacar servisu sa 'id' koji je prosledjen
    this.branchService.getBranchesByRentACarId(this.rentacar.id).subscribe(
      data => this.branches = data
    );
  }

  onRemove(branch: BranchOffice): void {
    this.branches = this.branches.filter(b => b !== branch);
    this.branchService.removeBranch(branch.id).subscribe(branch => this.branch = branch);
  }

}
