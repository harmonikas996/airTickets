import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { BranchOffice } from 'src/app/shared/model/rentacar/branchOffice.model';
import { BranchService } from 'src/app/shared/services/rentacar/branch.service';

@Component({
  selector: 'branch-details',
  templateUrl: './branch-details.component.html',
  styleUrls: ['./branch-details.component.css']
})
export class BranchDetailsComponent implements OnInit {

  branch: Observable<BranchOffice>;
  branchModel: BranchOffice;
  branchDetailsForm: FormGroup;

  constructor(
    private branchService: BranchService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.branchDetailsForm = this.formBuilder.group({
      id: [''],
      rentACarId: [''],
      address: ['', Validators.required],
      city: ['', Validators.required]
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getBranchById(id);

    
  }
  
  getBranchById(id: number): void {
    this.branch = this.branchService.getBranchById(id).pipe(
      tap(branch => this.branchDetailsForm.patchValue(branch))
    );
  }

  onSubmit() {
    // obavezna provera da li ta filijala pripada rentakaru za koga je korisnik ADMIN
    if (this.branchDetailsForm.valid) {
      this.branchService.updateBranch(this.branchDetailsForm.value).subscribe((response) => {
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

  onRemove(branch: BranchOffice) {
    this.branchService.removeBranch(branch.id).subscribe(branch => this.branchModel = branch);
    this.location.back();
  }

}
