import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RoomService } from 'src/app/shared/services/hotel/room/room.service';
import { Room } from 'src/app/shared/model/hotel/room.model';
import * as moment from 'moment';
import { RoomReservationService } from 'src/app/shared/services/hotel/room-reservation/room-reservation.service';
import { RoomReservation } from 'src/app/shared/model/hotel/room-reservation.model';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { Hotel } from 'src/app/shared/model/hotel/hotel.model';
import { HotelService } from 'src/app/shared/services/hotel/hotel.service';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { RoomPriceService } from 'src/app/shared/services/hotel/room-price/room-price.service';
import { AmenityService } from 'src/app/shared/services/hotel/amenity/amenity.service';


@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

  hotel = [];
  hotelRoomForm: FormGroup;
  numberOfrooms: number[];
  id: number;
  rooms = [];
  roomReservations = [];
  amenities = [];
  selectedAmenities = [];
  selectedAmenitiesObj = [];
  selectedRoomsObj = [];
  userLoggedIn: boolean;
  amenitySum = 0;
  roomsSum = 0;


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private roomService: RoomService,
    private roomPriceService: RoomPriceService,
    private roomReservationService: RoomReservationService,
    private tokenStorage: TokenStorageService,
    private hotelService: HotelService,
    private http: HttpClient,
    private amenityService: AmenityService
  ) { }

  ngOnInit() {

    this.hotelRoomForm = this.formBuilder.group({
      numberOfrooms1: [null],
      numberOfrooms2: [null],
      numberOfrooms3: [null],
      numberOfrooms4: [null],
      minprice: [null],
      maxprice: [null],
      guest: [null],
      datePeriod: [null, Validators.required]
    });

    const ajDi = +this.route.snapshot.paramMap.get('id');
    this.id = ajDi;
    this.numberOfrooms = [1, 2, 3, 4, 5, 6, 7, 8, 9];

    this.getAmenitiesByHotel(ajDi);
    this.getHotelById(ajDi);
    if (this.tokenStorage.getToken() !== '') {
      this.userLoggedIn = true;
      this.getQuickReservation(ajDi);
    } else {
      this.userLoggedIn = false;
    }
  }

  onSubmit() {

      this.selectedAmenities = [];
      this.selectedAmenitiesObj = [];
      this.selectedRoomsObj = [];
      this.amenitySum = 0;
      this.roomsSum = 0;

      const timeBegin: string = moment(this.hotelRoomForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
      const timeEnd: string = moment(this.hotelRoomForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');

      this.roomService.searchRoomsByDate2(timeBegin, timeEnd, this.id).subscribe(
        rooms => {
          this.rooms = [];
          for (const room of rooms) {
            this.roomPriceService.searchRoomPriceForDateRange(room.id, timeBegin, timeEnd).subscribe(
              roomPrice => {
                const start = moment(timeBegin);
                const end = moment(timeEnd);

                if (roomPrice.id !== 0) {

                  this.rooms.push(
                    {
                      id: room.id,
                      floor: room.floor,
                      number: room.number,
                      noOfBeds: room.noOfBeds,
                      type: room.type,
                      hotel: room.hotel,
                      image: room.image,
                      pricePerDay: roomPrice.price,
                      price: +roomPrice.price * (moment.duration(start.diff(end)).asDays() - 1) * (-1)
                    }
                  );
                }
              }
            );
          }
        }
      );
      // this.searchRooms();
  }

  getAmenitiesByHotel(id: number) {
    this.amenityService.getAmenitiesByHotel(id).subscribe(
      amenities => this.amenities = amenities
    );
  }

  getHotelById(id: number) {
    this.hotelService.getHotelById(id).subscribe(hotel => {
      this.http.jsonp('http://dev.virtualearth.net/REST/v1/Locations/' + hotel.address +
        '?jsonp=JSONP_CALLBACK&key=' + environment.bingMapCredentials, 'JSONP_CALLBACK')
        .subscribe(
          (response: any) => {
            hotel.address = response.resourceSets[0].resources[0].address.formattedAddress;
          }
        );
      this.hotel = hotel as any;
    });
  }

  getQuickReservation(id: number): void {
    this.roomReservationService.getQuickRoomReservationsByCompanyId(id)
    .subscribe(roomReservations => {
      for (let roomReservation of roomReservations) {

        this.roomService.getRoomById(roomReservation.roomId).subscribe(
          room => {

            this.roomReservations.push({
                id: roomReservation.id,
                roomId: roomReservation.roomId,
                hotelReservationId: roomReservation.hotelReservationId,
                floor: room.floor,
                number: room.number,
                noOfBeds: room.noOfBeds,
                type: room.type,
                image: room.image
              }
            );
          }
        );
      }
    });
  }

  onAddAmenity(event: any) {
    this.amenitySum += event.price;
    this.selectedAmenitiesObj.push(event);
  }

  onRemoveAmenity(event: any) {
    this.amenitySum -= event.value.price;
    this.selectedAmenitiesObj.splice(this.selectedAmenitiesObj.findIndex(x => x.id === event.value.id), 1);
  }

  onClearAmenities() {
    this.amenitySum = 0;
    this.selectedAmenitiesObj = [];
  }

  onSelectRoom(room: any) {
    if (this.selectedRoomsObj.findIndex(x => x.id === room.id) === -1) {
      this.selectedRoomsObj.push(room);
      this.roomsSum += room.price;
    } else {
      this.selectedRoomsObj.splice(this.selectedRoomsObj.findIndex(x => x.id === room.id), 1);
      this.roomsSum -= room.price;
    }
  }

  isRoomSelected(room:any) {
    return (this.selectedRoomsObj.findIndex(x => x.id === room.id) !== -1) ? true : false;
  }


}
