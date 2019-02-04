import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { Observable } from 'rxjs';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import { tap } from 'rxjs/operators';
import { Location } from '@angular/common';
import { User } from 'src/app/shared/model/user/user.model';
import { UserService } from 'src/app/shared/services/user/user.service';

@Component({
  selector: 'app-rentacar-details',
  templateUrl: './rentacar-details.component.html',
  styleUrls: ['./rentacar-details.component.css']
})
export class RentacarDetailsComponent implements OnInit {

  rentacar: Observable<RentACar>;
  rentacarModel: RentACar;
  rentacarDetailsForm: FormGroup;
  clients: User[];
  addAdminForm: FormGroup;

  constructor(
    private rentacarService: RentacarService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.rentacarDetailsForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });

    this.addAdminForm = this.formBuilder.group({
      selectedUser: ['', Validators.required],
      companyId: ['']
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getRentacarById(id);
    this.getUsers();
  }

  getRentacarById(id: number): void {
    this.rentacar = this.rentacarService.getRentacarById(id).pipe(
      tap(rentacar => {
        this.rentacarDetailsForm.patchValue(rentacar);
        this.addAdminForm.controls['companyId'].setValue(rentacar.id);
      })
    );
  }

  onSubmit() {
    // obavezna provera da li to vozilo pripada rentakaru za koga je korisnik ADMIN
    if (this.rentacarDetailsForm.valid) {
      this.rentacarService.updateRentacar(this.rentacarDetailsForm.value).subscribe((response) => {
        console.log('Response is: ', response);
        this.location.back();
     },
     (error) => {
        // catch the error
        console.error('An error occurred, ', error);
     });
     }
    }

  onCancel() {
    this.location.back();
  }

  onRemove(rent: RentACar) {
    this.rentacarService.removeRentacar(rent.id).subscribe(rentacar => this.rentacarModel = rentacar);
    this.location.back();
  }

  getUsers(): void {
    this.userService.getClients().subscribe(data => this.clients = data);
  }

  onAddAdmin() {

    if (this.addAdminForm.valid) {
      this.rentacarService.addAdmin(
        this.addAdminForm.controls['companyId'].value, this.addAdminForm.controls['selectedUser'].value
        );
     }
  }
}
