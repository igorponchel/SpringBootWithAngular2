import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute, Params } from '@angular/router';
import { Technicien } from './technicien';
import { TechnicienService } from './technicien.service';
import { NgForm } from '@angular/forms';


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
    if (this.selectedTechnicien && this.selectedTechnicien === technicien) {
      this.selectedTechnicien = null;
    } else {
      this.selectedTechnicien = technicien;
    }
  }

  displayDetails(): void {
    /*this.router.navigate(['/detailTechnicien', this.selectedTechnicien.id]);*/

  }

  /****************************/
  /* DEBUT Validation via component */
  /****************************/
  detailForm: NgForm;
  @ViewChild('detailForm') currentForm: NgForm;

  ngAfterViewChecked() {
    this.formChanged();
  }

  formChanged() {
    if (this.currentForm === this.detailForm) {
      return;
    }
    this.detailForm = this.currentForm;
    if (this.detailForm) {
      this.detailForm.valueChanges
        .subscribe(data => this.onValueChanged(data));
    }
  }

  onValueChanged(data?: any) {
    if (!this.detailForm) { return; }
    const form = this.detailForm.form;

    for (const field in this.formErrors) {
      // clear previous error message (if any)
      this.formErrors[field] = '';
      const control = form.get(field);

      if (control && control.dirty && !control.valid) {
        const messages = this.validationMessages[field];
        for (const key in control.errors) {
          this.formErrors[field] += messages[key] + ' ';
        }
      }
    }
  }

  formErrors = {
    'nomInput': '',
    'power': ''
  };

  validationMessages = {
    'nomInput': {
      'required': 'Le nom est requis',
      'minlength': '4 caractères minimum',
      'maxlength': '24 caractères maximum'
    },
    'power': {
      'required': 'Power is required.'
    }
  };

  /****************************/
  /* FIN Validation via component */
  /****************************/


}
