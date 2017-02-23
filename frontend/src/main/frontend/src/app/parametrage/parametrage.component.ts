import {
  Component,
  OnInit
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServerDataSource, LocalDataSource } from 'ng2-smart-table';
import { Http } from '@angular/http/src/http';

import { ParametrageService } from './parametrage.service';
import {  Parametre } from './parametre';


@Component({
  selector: 'about',
  providers: [ParametrageService],
  styleUrls: ['parametrage.component.css'],
  templateUrl: 'parametrage.component.html'
})
export class ParametrageComponent {
  source: ServerDataSource;

  settings = {
    columns: {
      id: {
        title: 'ID'
      },
      libelle: {
        title: 'Libelle'
      },
      valeur: {
        title: 'Valeur'
      },
      commentaire: {
        title: 'Commentaire'
      }
    },
    action: null
  };


  constructor(http: Http, private parametrageService: ParametrageService) {
    this.source = new ServerDataSource(http, { endPoint: 'http://localhost:8080/v1/parametrage' });
  }

  afficherDivModifier = false;
  afficherDivCreer = false;

  selectedParam: any = {};
  creationParam: any = {};

  test: any = {};

  onRowSelect(event): void {

    this.afficherDivModifier = false;
    this.afficherDivCreer = false;
    this.selectedParam = event.data;
  }

  creerParametre(): void {

    this.parametrageService.save(this.creationParam)
      .then(returnedParam => this.source.append(returnedParam));
  }

  enregistrerModification(): void {

    let result = this.parametrageService.save(this.selectedParam)
      .then(() => this.source.refresh());
  }

  supprimerParametre(): void {

    this.parametrageService.delete(this.selectedParam)
      .then(() => this.source.remove(this.selectedParam));
  }

  modifierActive(): void {
    this.afficherDivModifier = true;
    this.afficherDivCreer = false;
  }

  creerActive(): void {
    this.afficherDivModifier = false;
    this.afficherDivCreer = true;
  }
}
