import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { Observable } from 'rxjs';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';

@Component({
  selector: 'app-rentacar-details',
  templateUrl: './rentacar-details.component.html',
  styleUrls: ['./rentacar-details.component.css']
})
export class RentacarDetailsComponent implements OnInit {

  rentacar: Observable<RentACar>;
  rentacarModel: RentACar;
  rentacarDetailsForm: FormGroup;

  constructor(
    private rentacarService: RentacarService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.rentacarDetailsForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getRentacarById(id);
  }

  getRentacarById(id: number): void {
    this.rentacar = this.rentacarService.getRentacarById(id).pipe(
      tap(rentacar => this.rentacarService.patchValue(rentacar))
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
}
