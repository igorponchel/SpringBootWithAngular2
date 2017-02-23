import { Zone } from './index';

export class Technicien {
  id: number;
  firstName: string;
  lastName: string;
  userName: string;
  password: string;
  mail: string;
  sexe: string;
  zones: Zone[];
  qualification: string;
  adressePostale: string;
  mobile: boolean;
}