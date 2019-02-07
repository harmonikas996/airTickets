import { Flight } from './../../shared/model/aircompany/flight.model';
import { Component, OnInit, Input} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-flights-res-list',
  templateUrl: './flights-res-list.component.html',
  styleUrls: ['./flights-res-list.component.css']
})
export class FlightsResListComponent implements OnInit {

  seatSelFormDep: FormGroup;
  seatSelFormRet: FormGroup;
  alreadySelected: boolean;
  passengers: number;
  selectedSeatsDep: string[] = [];
  selectedSeatsRet: string[] = [];

  constructor(
    private formBuilder: FormBuilder,

  ) { }

  ngOnInit() {
    this.seatSelFormDep = this.formBuilder.group({
      selectedSeat1: ['', Validators.required]
    });

    this.seatSelFormRet = this.formBuilder.group({
      selectedSeat: ['', Validators.required]
    });

    this.alreadySelected = false;
    this.passengers = 2;
  }

  onSubmitDep() {
    this.seatSelFormDep.controls['selectedSeat1'].disable();
  }

  onSubmitRet() {
    this.seatSelFormRet.controls['selectedSeat'].disable();
  }

  public selectSeatDep(value: string){
    if(this.selectedSeatsDep.length < this.passengers) {
      this.selectedSeatsDep.push(value);
    }
    if(this.selectedSeatsDep.length == this.passengers) {
      this.seatSelFormDep.controls['selectedSeat1'].disable();
      console.log(this.seatSelFormDep);
    }
  }

  public selectSeatRet(value: string){
    if(this.selectedSeatsRet.length < this.passengers) {
      this.selectedSeatsRet.push(value);
    }
    if(this.selectedSeatsRet.length == this.passengers) {
      this.seatSelFormRet.controls['selectedSeat'].disable();
      console.log(this.seatSelFormRet);
    }
  }
}
