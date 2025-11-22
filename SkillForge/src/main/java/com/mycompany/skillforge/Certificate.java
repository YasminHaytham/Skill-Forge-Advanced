package com.mycompany.skillforge;

import java.time.LocalDate;
import java.util.Random;

import org.json.JSONObject;

public class Certificate {

    private LocalDate issueDate;
    private String studentId;
    private String courseId;
    private String certificateId;
    private Random random = new Random();

    public Certificate(String studentId, String courseId) {
        this.certificateId = "Cer" + String.format("%04d", random.nextInt(10000));
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = LocalDate.now();
    }

    public Certificate(String certificateId, String studentId, String courseId, LocalDate issueDate) {
        this.issueDate = issueDate;
        this.studentId = studentId;
        this.courseId = courseId;
        this.certificateId = certificateId;
    }

    public static Certificate fromJsonObject(JSONObject jsonObject) {
        String certificateId = jsonObject.getString("certificateId");
        String studentId = jsonObject.getString("studentId");
        String courseId = jsonObject.getString("courseId");
        LocalDate issueDate = LocalDate.parse(jsonObject.getString("issueDate"));

        return new Certificate(certificateId, studentId, courseId, issueDate);
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("certificateId", this.certificateId);
        jsonObject.put("studentId", this.studentId);
        jsonObject.put("courseId", this.courseId);
        jsonObject.put("issueDate", this.issueDate.toString()); 

        return jsonObject;

    }

}
