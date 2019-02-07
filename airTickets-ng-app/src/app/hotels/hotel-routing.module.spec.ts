import { HotelRoutingModule } from './hotel-routing.module';

describe('HotelRoutingModule', () => {
  let hotelRoutingModule: HotelRoutingModule;

  beforeEach(() => {
    hotelRoutingModule = new HotelRoutingModule();
  });

  it('should create an instance', () => {
    expect(hotelRoutingModule).toBeTruthy();
  });
});
