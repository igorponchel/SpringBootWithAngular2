import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: 'dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  title: String = 'Bienvenue sur MOBI';

  constructor( ) { }

  color: string;
  isSpecial: boolean;

  ngOnInit(): void {
    this.isSpecial = true;
  }

  afficherSaisie(saisie: String) {
    alert(saisie);
  }
}
