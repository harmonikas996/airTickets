import { HotelsModule } from './hotels.module';

describe('HotelsModule', () => {
  let hotelsModule: HotelsModule;

  beforeEach(() => {
    hotelsModule = new HotelsModule();
  });

  it('should create an instance', () => {
    expect(hotelsModule).toBeTruthy();
  });
});
