package com.example.demo2.Services;

import com.example.demo2.Models.Student;
import com.example.demo2.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    // store student in DB
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    // get single student
    public Student findStudentById(Integer id){
        Optional<Student> student = studentRepository.findById(id);

        return student.get();
    }

    // get students
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<>();
        Iterator<Student> studentIterator = studentRepository.findAll().iterator();

        while(studentIterator.hasNext()){
            Student student = studentIterator.next();
            students.add(student);
        }

        return students;
    }

    // delete student
    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }

}
