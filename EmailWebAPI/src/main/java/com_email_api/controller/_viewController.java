package com_email_api.controller;

import com_email_api.entity.MailEntity;
import com_email_api.helper.Flag;
import com_email_api.servies.MailServies;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Controller
public class _viewController {

    @Autowired
    private MailServies servies;

    @GetMapping("/")
    public String compose(Model model){
        System.out.println("/========");
        model.addAttribute("title","Email API | Compose Mail");

        return "email";
    }

    @PostMapping("/sending")
    public String sendAtt(@ModelAttribute("MailEntity")MailEntity entity, Model model, @RequestParam("att")MultipartFile multipartFile) throws IOException {

        model.addAttribute("title","Email API | Success");

        if(multipartFile.isEmpty()){
            this.servies.mail_Text(entity.getTo(),entity.getSubject(),entity.getMessage());
        }else {
            File convFile = new File( multipartFile.getOriginalFilename() );
            FileOutputStream fos = new FileOutputStream( convFile );
            fos.write( multipartFile.getBytes() );
            fos.close();


            byte[] fileContent = FileUtils.readFileToByteArray(convFile);

            String encodedString = Base64.getEncoder().encodeToString(fileContent);

            //System.out.println("============"+encodedString);

            entity.setAttachment(convFile);

            this.servies.mail_Multipart(entity.getTo(),entity.getSubject(),entity.getMessage(),entity.getAttachment());
        }

        return "success";
    }
}
