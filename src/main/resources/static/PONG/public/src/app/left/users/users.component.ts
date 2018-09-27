import { Component, OnInit } from '@angular/core';
import { User } from "../../user.model";
import { UsersService} from "../../users.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: any[] = []

  constructor(private uS: UsersService) { }

  ngOnInit() {
    this.users = [{"id":1, "title":"USER1"}, {"id":1, "title":"USER2"}, {"id":1, "title":"USER3"}, {"id":1, "title":"USER4"}]
    // this.uS.getUsers(data => {
    //   this.users = data;
    // })
  } 

}
