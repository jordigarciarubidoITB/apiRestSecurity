package com.itb.apirestsecurity.controllers;

import com.itb.apirestsecurity.model.entities.ClassStudent;
import com.itb.apirestsecurity.model.services.ClassStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/* CRUD ClassStudent *******************************
- 1) List all students
- 2) Add student
- 3) Consult student by id
- 4) Modify student
- 5) Delete student by id
 ***********************************************/

@RestController
@RequiredArgsConstructor
public class ClassStudentController {
    private final ClassStudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<?> consultStudents() {
        List<ClassStudent> res = studentService.classStudentList();
        if (res != null) return ResponseEntity.ok(res);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody ClassStudent cs) {
        try {
            studentService.addClassStudent(cs);
            return new ResponseEntity<ClassStudent>(cs, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> consultStudentById(@PathVariable long id) {
        ClassStudent v = studentService.consultById(id);
        if (v != null) {
            return ResponseEntity.ok(v);
        } else return ResponseEntity.notFound().build();
    }

    @PutMapping("/students")
    public ResponseEntity<?> modifyStudent(@RequestBody ClassStudent cs){
        ClassStudent res = studentService.modifyClassStudent(cs);
        if(res!=null) return ResponseEntity.ok(res);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable long id){
        ClassStudent res = studentService.deleteClassStudent(id);
        if(res!=null){
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

}