export interface User {
  id?: number;
  email: string;
  company: number;
  firstName: string;
  lastName: string;
  city?: any;
  phone?: any;
  activated?: boolean;
  bonusPoints?: number;
  lastPasswordResetDate?: any;
}
