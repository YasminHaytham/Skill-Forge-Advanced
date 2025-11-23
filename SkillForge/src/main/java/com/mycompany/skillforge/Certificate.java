package com.mycompany.skillforge;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.json.JSONObject;

public class Certificate {

    private LocalDate issueDate;
    private String studentId;
    private String courseId;
    private String certificateId;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
    private Random random = new Random();

    public Certificate(String studentId, String courseId) {
        this.certificateId = "CER" + String.format("%04d", random.nextInt(10000));
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
    

    public LocalDate getIssueDate() {

        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public String getFormatedIssueDate()
    {
        return issueDate.format(formatter);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
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
