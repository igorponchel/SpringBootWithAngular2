import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute, Params } from '@angular/router';
import { Technicien } from './technicien';
import { TechnicienService } from './technicien.service';


@Component({
  selector: 'my-techniciens',
  templateUrl: 'technicien.component.html',
  styleUrls: ['technicien.component.css']
})

export class TechnicienComponent implements OnInit {
  techniciens: Technicien[];
  selectedTechnicien: Technicien;
  addingTechnicien = false;
  error: any;

  constructor(
    private router: Router,
    private technicienService: TechnicienService) { }

  getTechniciens(): void {
    this.technicienService
      .getTechniciens()
      .then(techniciens => this.techniciens = techniciens)
      .catch(error => this.error = error);
  }

  addTechnicien(): void {
    this.addingTechnicien = true;
    this.selectedTechnicien = null;
  }

  close(savedTechnicien: Technicien): void {
    this.addingTechnicien = false;
    if (savedTechnicien) { this.getTechniciens(); }
  }

  deleteTechnicien(technicien: Technicien, event: any): void {
    event.stopPropagation();
    this.technicienService
      .delete(technicien)
      .then(res => {
        this.techniciens = this.techniciens.filter(h => h !== technicien);
        if (this.selectedTechnicien === technicien) { this.selectedTechnicien = null; }
      })
      .catch(error => this.error = error);
  }

  ngOnInit(): void {
    this.getTechniciens();
  }

  onSelect(technicien: Technicien): void {
    if (this.selectedTechnicien) {
      this.selectedTechnicien = null;
    } else {
      this.selectedTechnicien = technicien;
    }
  }

  displayDetails(): void {
    /*this.router.navigate(['/detailTechnicien', this.selectedTechnicien.id]);*/

  }
}
