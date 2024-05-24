import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailSenderService {

  // URL set where your email api has running like http://.............:port
   private baseUrl:String ="http://192.168.1.10:8087"

  constructor(private http:HttpClient) {}

    sendMail(data:any){
     return this.http.post(`${this.baseUrl}/send`,data)
    }


}
