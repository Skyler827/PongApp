import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: Http) { 
    
  }

  getUsers(cb) {
    return this.http.get('/api/books')
      .subscribe(data => cb(data.json()));

  }

}
