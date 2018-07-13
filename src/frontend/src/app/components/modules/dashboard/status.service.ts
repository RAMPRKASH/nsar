import { Injectable } from '@angular/core';
import {Http, Headers, Response } from '@angular/http';

@Injectable()
export class  StatusService {
constructor(private http: Http) {}
    
    getStatusOverview() {
       return this.http.get('http://localhost:8080/network/status/summary');
    }

    getStatusDetails(){
        return this.http.get('http://localhost:8080/network/status/details/');
    }
}
