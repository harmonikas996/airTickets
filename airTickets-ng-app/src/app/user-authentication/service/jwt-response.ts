export class JwtResponse {
    accessToken: string;
    type: string;
    username: string;
    userId: number;
    authorities: string[];
    expiresIn: string;
}
