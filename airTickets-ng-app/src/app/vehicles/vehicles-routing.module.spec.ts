import { VehiclesRoutingModule } from './vehicles-routing.module';

describe('VehiclesRoutingModule', () => {
  let vehiclesRoutingModule: VehiclesRoutingModule;

  beforeEach(() => {
    vehiclesRoutingModule = new VehiclesRoutingModule();
  });

  it('should create an instance', () => {
    expect(vehiclesRoutingModule).toBeTruthy();
  });
});
