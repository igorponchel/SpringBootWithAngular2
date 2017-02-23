import { Component } from '@angular/core';
import { AuthenticationService } from './auth/index';

@Component({
    selector: 'body',
    templateUrl: './app.component.html',
    providers: [ AuthenticationService ]
})
export class AppComponent {

    constructor(private auth: AuthenticationService) {}

}
