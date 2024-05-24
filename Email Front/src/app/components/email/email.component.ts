import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmailSenderService } from 'src/app/service/email-sender.service';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit{

  data={
    to:"",
    subject:"",
    message:""
  }

  flag:boolean=false;

  constructor(private service:EmailSenderService, private snake:MatSnackBar){

  }

  ngOnInit(): void {

  }

  doSubmit(){
    console.log("Submitting Form");
    console.log("Data:",this.data);

    this.flag=true;

    if(this.data.to==''||this.data.subject==''||this.data.message==''){
      this.snake.open("Can't be empty!!","OK")
      this.flag=false;
      return;
    }

    this.service.sendMail(this.data).subscribe(
      response=>{
        console.log(response);
        this.snake.open("Email has been Send Successfully","OK")
        this.flag=false;
      },error=>{
        console.log(error);
        this.snake.open("Internal Server Error","OK")
        this.flag=false;
      }

    )
  }
}
