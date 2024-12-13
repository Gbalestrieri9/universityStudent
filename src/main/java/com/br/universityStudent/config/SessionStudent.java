package com.br.universityStudent.config;

import com.br.universityStudent.model.Student;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionStudent {

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
