import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Parametre } from './index';


@Injectable()
export class ParametrageService {
    private urlParametrage = 'http://localhost:8080/v1/parametrage';  // URL to web api

    constructor(private http: Http) { }


    // Mise à jour du paramètre
    save(parametre: Parametre): Promise<Parametre> {
        return this.put(parametre);
    }

    private put(parametre: Parametre): Promise<Parametre> {
        let headers = new Headers({
            'Content-Type': 'application/json',
            'userId': 'userDeveloppement',
        });

        let url = `${this.urlParametrage}`;

        return this.http
            .put(url, JSON.stringify(parametre), { headers: headers })
            .toPromise()
            .then(response => response.json() as Parametre)
            .catch(this.handleError);
    }

    private post(parametre: Parametre): Promise<Parametre> {
        let headers = new Headers({
            'Content-Type': 'application/json',
            'userId': 'userDeveloppement',
        });

        let url = `${this.urlParametrage}`;

        return this.http
            .post(url, JSON.stringify(parametre), { headers: headers })
            .toPromise()
            .then(res => res.json().data)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }

    delete(parametre: Parametre): Promise<Response> {
        let headers = new Headers({
            'Content-Type': 'application/json',
            'userId': 'userDeveloppement',
        });

        let url = `${this.urlParametrage}/${parametre.id}`;

        return this.http
            .delete(url, { headers: headers })
            .toPromise()
            .catch(this.handleError);
    }

    /*getHeroes(): Promise<Technicien[]> {
   return this.http
     .get(this.heroesUrl)
     .toPromise()
     .then(response => response.json().content as Technicien[])
     .catch(this.handleError);
 }
 
 getHero(id: number): Promise<Technicien> {
   return this.getHeroes()
     .then(heroes => heroes.find(hero => hero.id === id));
 }
 
 
*/
}
