import { FlightsRoutingModule } from './flights-routing.module';

describe('FlightsRoutingModule', () => {
  let flightsRoutingModule: FlightsRoutingModule;

  beforeEach(() => {
    flightsRoutingModule = new FlightsRoutingModule();
  });

  it('should create an instance', () => {
    expect(flightsRoutingModule).toBeTruthy();
  });
});
