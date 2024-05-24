package com_email_api.controller;

import com_email_api.entity.MailEntity;
import com_email_api.response.EmailResponse;
import com_email_api.servies.MailServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class _RestMailController {

    @Autowired
    private MailServies servies;

    @GetMapping("/test")
    public String testAPI(){

        return "Its Working....";
    }

    @PostMapping("/send")
    public ResponseEntity<?> mailSender(@RequestBody MailEntity mailEntity){

        System.out.println("********************: "+mailEntity);

        boolean b = this.servies.mail_Text(mailEntity.getTo(), mailEntity.getSubject(), mailEntity.getMessage());

        if(b){
            return ResponseEntity.ok(new EmailResponse("Email Send Successfully...."));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("INTERNAL SERVER ERROR !!! ... Mail not send"));
        }
    }
    @PostMapping("/sendAttachment")
    public ResponseEntity<?> mailSenderWithAttechment(@RequestBody MailEntity mailEntity){

        System.out.println(mailEntity);

        boolean b = this.servies.mail_Multipart(mailEntity.getTo(), mailEntity.getSubject(), mailEntity.getMessage(),mailEntity.getAttachment());

        if(b){
            return ResponseEntity.ok(new EmailResponse("Mail has been sant successfully..."));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("INTERNAL SERVER ERROR !!! ... Mail not send"));
        }
    }
}
