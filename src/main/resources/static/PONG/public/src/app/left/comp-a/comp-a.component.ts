import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comp-a',
  templateUrl: './comp-a.component.html',
  styleUrls: ['./comp-a.component.css']
})
export class CompAComponent implements OnInit {

  users: any[] = []

  constructor() { }

  ngOnInit() {
    this.users = [{"id":1, "title":"ONE"}, {"id":1, "title":"ONE"}, {"id":1, "title":"ONE"}, {"id":1, "title":"ONE"}]
  }

}
