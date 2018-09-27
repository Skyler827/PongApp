import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute) { }

  go(path) {
    console.log(path);
    this.router.navigate([{outlets: {primary: path, leftContent:path}}], 
                         {relativeTo: this.route});
}

  ngOnInit() {
  }

}
