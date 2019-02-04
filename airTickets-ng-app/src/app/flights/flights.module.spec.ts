import { FlightsModule } from './flights.module';

describe('FlightsModule', () => {
  let flightsModule: FlightsModule;

  beforeEach(() => {
    flightsModule = new FlightsModule();
  });

  it('should create an instance', () => {
    expect(flightsModule).toBeTruthy();
  });
});
