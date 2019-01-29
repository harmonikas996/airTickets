export class JwtResponse {
    accessToken: string;
    type: string;
    username: string;
    authorities: string[];
    expiresIn: string;
}
