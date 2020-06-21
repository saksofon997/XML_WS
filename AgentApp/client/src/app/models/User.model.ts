import { Role } from './Role.model';

export class User {
    id: number;
    email: string;
    name: string;
    surname: string;
    address: string;
    city: string;
    state: string;
    phoneNumber: string;
    enabled: boolean;
    company: any;
    roles: Role[];
}
