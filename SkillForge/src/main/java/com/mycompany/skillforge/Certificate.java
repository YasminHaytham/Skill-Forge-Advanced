package com.mycompany.skillforge;

import java.time.LocalDate;
import java.util.Random;

public class Certificate {
    private LocalDate issueDate;
   private String studentId;
    private String courseId;
    private String certificateId;
     private Random random = new Random();


    
    public Certificate(String studentId, String courseId) {
        this.certificateId= "Cer" + String.format("%04d", random.nextInt(10000))  ;
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate= LocalDate.now();
    }



    public Certificate(String certificateId, String studentId, String courseId , LocalDate issueDate) {
        this.issueDate = issueDate;
        this.studentId = studentId;
        this.courseId = courseId;
        this.certificateId = certificateId;
    }

    
    

    



}
