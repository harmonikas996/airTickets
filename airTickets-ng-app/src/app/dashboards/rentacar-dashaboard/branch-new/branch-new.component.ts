import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { BranchOffice } from 'src/app/shared/model/rentacar/branchOffice.model';
import { BranchService } from 'src/app/shared/services/rentacar/branch.service';

@Component({
  selector: 'branch-new',
  templateUrl: './branch-new.component.html',
  styleUrls: ['./branch-new.component.css']
})
export class BranchNewComponent implements OnInit {

  branch: Observable<BranchOffice>;
  //rentacarModel: RentACar;
  newBranchForm: FormGroup;
  

  constructor(
    private branchService: BranchService, 
    private formBuilder: FormBuilder, 
    private location: Location,
  ) { }

  ngOnInit() {
    // srediti preuzimanje ID-a tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.newBranchForm = this.formBuilder.group({
      id: [''],
      rentACarId: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required]
    });
    
  }

  onSubmit() {
    if (this.newBranchForm.valid) {
      this.branchService.addBranch(this.newBranchForm.value).subscribe((response) => {
        console.log("Response is: ", response);
        this.location.back();
     },
     (error) => {
        //catch the error
        console.error("An error occurred, ", error);
     });
     };
    }

  onCancel() {
    this.location.back();
  }

}
