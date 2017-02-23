import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Technicien } from '../technicien/index';
import 'rxjs/add/operator/map'

import { tokenNotExpired } from 'angular2-jwt';
import { myConfig } from '../auth/auth.config';

// Avoid name not found warnings
/*declare var Auth0Lock: any;*/

@Injectable()
export class AuthenticationService {

    // Configure Auth0
  /*  lock = new Auth0Lock(myConfig.clientID, myConfig.domain, {});*/

    constructor(private http: Http) {

    /*    this.lock.on('authenticated', (authResult) => {
            localStorage.setItem('currentUser', authResult.idToken);
        });*/
    }

    private urlAuthentification = 'http://localhost:8080/v1'

    login(username: string, password: string) {

        let headers = new Headers({
            'Content-Type': 'application/json',
            'userId': 'userDeveloppement',
        });

        let urlLogin = `${this.urlAuthentification}/login`;

        return this.http.post(urlLogin, JSON.stringify({ userName: username, password: password }), { headers: headers })
            .map((response: Response) => {

                /*this.lock.show();*/

                // login successful if there's a jwt token in the response
                let user = response.json();
                if (user && user.id) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
            });

    }

    register(technicien: Technicien) {

        let headers = new Headers({
            'Content-Type': 'application/json',
            'userId': 'userDeveloppement',
        });

        let urlRegister = `${this.urlAuthentification}/technicien`;
        alert(JSON.stringify(technicien));
        return this.http.put(urlRegister, JSON.stringify(technicien), { headers: headers })
            .map((response: Response) => {
                
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }

    public authenticated() {
        // Check if there's an unexpired JWT
        // It searches for an item in localStorage with key == 'id_token'
        return localStorage.getItem('currentUser');

       /* return tokenNotExpired();*/
    };
}