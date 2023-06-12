package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

}
@RestController
class StudentController {
    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student("Pham Trang", "1", 16, 1,"10","10A" ));
        students.add(new Student("Ngoc Nga", "2", 16,  2, "10","10A"));
        students.add(new Student("Anh Ba", "3", 17, 3,"11","11b" ));
        students.add(new Student("Ngoc Linh", "4", 16, 4,"11","11B"));
    }

    @GetMapping("/students/{class}")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable("class") String className) {
        List<Student> studentsByClass = new ArrayList<>();
        for (Student student : students) {
            if (student.getClassName().equalsIgnoreCase(className)) {
                studentsByClass.add(student);
            }
        }

        if (studentsByClass.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentsByClass, HttpStatus.OK);
    }
}



