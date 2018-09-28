import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comp-b',
  templateUrl: './comp-b.component.html',
  styleUrls: ['./comp-b.component.css']
})
export class CompBComponent implements OnInit {

  users: any[] = []

  constructor() { }

  ngOnInit() {
    this.users = [{"id":1, "title":"TWO"}, {"id":1, "title":"TWO"}, {"id":1, "title":"TWO"}, {"id":1, "title":"TWO"}]
  }

}
