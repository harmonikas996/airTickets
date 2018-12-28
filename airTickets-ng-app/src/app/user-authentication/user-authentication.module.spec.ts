import { UserAuthenticationModule } from './user-authentication.module';

describe('UserAuthenticationModule', () => {
  let userAuthenticationModule: UserAuthenticationModule;

  beforeEach(() => {
    userAuthenticationModule = new UserAuthenticationModule();
  });

  it('should create an instance', () => {
    expect(userAuthenticationModule).toBeTruthy();
  });
});
