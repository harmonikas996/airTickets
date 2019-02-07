import { Flight } from './../../shared/model/aircompany/flight.model';
import { Component, OnInit, Input} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-flights-res-list',
  templateUrl: './flights-res-list.component.html',
  styleUrls: ['./flights-res-list.component.css']
})
export class FlightsResListComponent implements OnInit {

  seatSelForm: FormGroup;
  alreadySelected: boolean;
  passengers: number;
  selectedSeats: string[] = [];

  constructor(
    private formBuilder: FormBuilder,

  ) { }

  ngOnInit() {
    this.seatSelForm = this.formBuilder.group({
      selectedSeat: [null, Validators.required]
    });

    this.alreadySelected = false;
    this.passengers = 2;
  }

  onSubmit() {
    this.seatSelForm.controls['selectedSeat'].enable();
  }

  public selectSeat(value: string){
    if(this.selectedSeats.length < this.passengers) {
      this.selectedSeats.push(value);
    }
    if(this.selectedSeats.length == this.passengers) {
      this.seatSelForm.controls['selectedSeat'].disable();
      console.log(this.selectedSeats);
    }
  }
}
