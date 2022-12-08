package com.example.demo2.Controller;

import com.example.demo2.Models.Student;
import com.example.demo2.Models.StudentException;
import com.example.demo2.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    List<Student> students = new ArrayList();


    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){

        studentService.addStudent(student);

        return student;
    }

    @GetMapping("/students")
    public List getAllStudents(){

        students = studentService.getAllStudents();

        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) throws StudentException {
        Student student = null;

        try{
              student = studentService.findStudentById(id);

        }catch(Exception ex){
            throw new StudentException("The student does not exist");
        }

        return student;
    }

    @PutMapping("/update")
    public Student editStudent( @RequestBody Student updatedStudent) throws StudentException {
        Student student = null;

        try{
              student = studentService.findStudentById(updatedStudent.getId());

            student.setAddress(updatedStudent.getAddress());
            student.setAge(updatedStudent.getAge());
            student.setPhoneNumber(updatedStudent.getPhoneNumber());
            student.setName(updatedStudent.getName());

        }catch(Exception ex){
            throw new StudentException("The student does not exist");
        }

        return student;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        Student student = null;
        String message = "";

            student = studentService.findStudentById(id);

            if(student != null){
                studentService.deleteStudent(student);
                message = "Student successfully deleted";
            } else {
                message = "Student not found !!";
            }

        return message;
    }


//    @PostMapping("/add")
//    public Integer addMethod(@RequestParam Integer num1, @RequestParam Integer num2){
//
//        Integer sum = num1+num2;
//
//        return sum;
//    }
//
//    @PostMapping("/multiply")
//    public Integer multiplyMethod(@RequestParam Integer num1, @RequestParam Integer num2){
//
//        Integer product = num1*num2;
//
//        return product;
//    }


}
