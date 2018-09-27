import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-comp-c',
  templateUrl: './comp-c.component.html',
  styleUrls: ['./comp-c.component.css']
})
export class CompCComponent implements OnInit {

  users: any[] = []

  constructor() { }

  ngOnInit() {
    this.users = [{"id":1, "title":"THREE"}, {"id":1, "title":"THREE"}, {"id":1, "title":"THREE"}, {"id":1, "title":"THREE"}]
  }

}
