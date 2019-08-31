import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-thank-you',
  templateUrl: './thank-you.component.html',
  styleUrls: ['./thank-you.component.css']
})
export class ThankYouComponent implements OnInit {

  firstName = '';

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit() {

    this.firstName = this.activatedRoute.snapshot.paramMap.get('firstName');
  }

}
