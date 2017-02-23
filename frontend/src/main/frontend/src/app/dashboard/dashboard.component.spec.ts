import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement, NO_ERRORS_SCHEMA } from '@angular/core';

import { DashboardComponent } from './dashboard.component';
import { HighlightDirective } from '../shared/highlight.directive';
import { UnlessDirective } from '../shared/unless.directive';

import { async } from '@angular/core/testing';

describe('DashboardComponent ', () => {

    let comp: DashboardComponent;
    let fixture: ComponentFixture<DashboardComponent>;
    let de: DebugElement;
    let el: HTMLElement;


    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [DashboardComponent, HighlightDirective, UnlessDirective],
            schemas: [NO_ERRORS_SCHEMA]
        });
    });

    beforeEach(async(() => {
        TestBed.compileComponents();

        fixture = TestBed.createComponent(DashboardComponent);
        comp = fixture.componentInstance; // DashboardComponent test instance
        // query for the title <h1> by CSS element selector
        de = fixture.debugElement.query(By.css('h1'));
        el = de.nativeElement;
    }));

    it('should display a different test title', () => {

        comp.title = 'Test';
        fixture.detectChanges();
        expect(el.textContent).toContain('Test');
    });
})

