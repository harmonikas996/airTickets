import { DashboardsRoutingModule } from './dashboards-routing.module';

describe('DashboardsRoutingModule', () => {
  let dashboardsRoutingModule: DashboardsRoutingModule;

  beforeEach(() => {
    dashboardsRoutingModule = new DashboardsRoutingModule();
  });

  it('should create an instance', () => {
    expect(dashboardsRoutingModule).toBeTruthy();
  });
});
