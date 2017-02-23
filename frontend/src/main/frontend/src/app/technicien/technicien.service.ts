import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';


import { Technicien } from './index';

@Injectable()
export class TechnicienService {
  private urlTechnicien = 'http://localhost:8080/v1/technicien';  // URL to web api

  headers = new Headers({
    'Content-Type': 'application/json',
    'userId': 'userDeveloppement',
  });

  constructor(private http: Http) { }

  getTechniciens(): Promise<Technicien[]> {
    return this.http
      .get(this.urlTechnicien)
      .toPromise()
      .then(response => response.json().content as Technicien[])
      .catch(this.handleError);
  }

  getTechnicien(id: number): Promise<Technicien> {
    return this.getTechniciens()
      .then(techniciens => techniciens.find(technicien => technicien.id === id));
  }

  save(technicien: Technicien): Promise<Technicien> {
    if (technicien.id) {
      return this.put(technicien);
    }
    return this.post(technicien);
  }

  delete(technicien: Technicien): Promise<Response> {

    let url = `${this.urlTechnicien}/${technicien.id}`;

    return this.http
      .delete(url, { headers: this.headers })
      .toPromise()
      .catch(this.handleError);
  }

  // Add new Technicien
  private post(technicien: Technicien): Promise<Technicien> {

    return this.http
      .post(this.urlTechnicien, JSON.stringify(technicien), { headers: this.headers })
      .toPromise()
      .then(res => res.json().data)
      .catch(this.handleError);
  }

  // Update existing Technicien
  private put(technicien: Technicien): Promise<Technicien> {

    let url = `${this.urlTechnicien}/${technicien.id}`;

    return this.http
      .put(url, JSON.stringify(technicien), { headers: this.headers })
      .toPromise()
      .then(() => technicien)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
