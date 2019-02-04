import { ActivatedRoute } from '@angular/router';
import { HotelService } from './../../../shared/services/hotel/hotel.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Hotel } from './../../../shared/model/hotel/hotel.model';
import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Location } from '@angular/common';
import { User } from 'src/app/shared/model/user/user.model';
import { UserService } from 'src/app/shared/services/user/user.service';

@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

  hotel: Observable<Hotel>;
  hotelModel: Hotel;
  hotelDetailsForm: FormGroup;
  clients: User[];
  addAdminForm: FormGroup;

  constructor(
    private hotelService: HotelService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.hotelDetailsForm = this.formBuilder.group({
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
    this.getHotelById(id);
    this.getUsers();
  }

  getHotelById(id: number): void {
    this.hotel = this.hotelService.getHotelById(id).pipe(
      tap(hotel => {
        this.hotelDetailsForm.patchValue(hotel);
        this.addAdminForm.controls['companyId'].setValue(hotel.id);
      })
    );
  }

  onSubmit() {
    // obavezna provera da li to vozilo pripada rentakaru za koga je korisnik ADMIN
    if (this.hotelDetailsForm.valid) {
      this.hotelService.updateHotel(this.hotelDetailsForm.value).subscribe((response) => {
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

  onRemove(hotel: Hotel) {
    this.hotelService.removeHotel(hotel.id).subscribe(hotel => this.hotelModel = hotel);
    this.location.back();
  }

  getUsers(): void {
    this.userService.getClients().subscribe(data => this.clients = data);
  }

  onAddAdmin() {

    if (this.addAdminForm.valid) {
      this.hotelService.addAdmin(
        this.addAdminForm.controls['companyId'].value, this.addAdminForm.controls['selectedUser'].value
        );
     }
  }
}
