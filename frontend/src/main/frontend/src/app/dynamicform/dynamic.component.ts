import { Component, OnInit } from '@angular/core';
import { QuestionService } from './question.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
    template: '<dynamic-form [questions]="questions"></dynamic-form>'
})
export class DynamicComponent implements OnInit {

    questions: any[];

    constructor(service: QuestionService) {
        this.questions = service.getQuestions();
    }

    ngOnInit(): void {

    }


}
