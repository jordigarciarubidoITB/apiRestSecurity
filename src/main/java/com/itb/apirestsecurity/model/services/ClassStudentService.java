package com.itb.apirestsecurity.model.services;

import com.itb.apirestsecurity.model.entities.ClassStudent;
import com.itb.apirestsecurity.model.repositories.ClassSudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassStudentService {
    private final ClassSudentRepository classSudentRepository;

    public List<ClassStudent> classStudentList(){
        return classSudentRepository.findAll();
    }

    public ClassStudent consultById(Long id){
        return classSudentRepository.findById(id).orElse(null);
    }

    public ClassStudent deleteClassStudent(Long id){
        ClassStudent res= classSudentRepository.findById(id).orElse(null);
        if(res!=null) classSudentRepository.deleteById(id);
        return res;
    }

    public ClassStudent addClassStudent(ClassStudent cs){
        return classSudentRepository.save(cs);
    }

    public ClassStudent modifyClassStudent(ClassStudent cs){
        ClassStudent res = null;
        if(classSudentRepository.existsById(cs.getId())) res = classSudentRepository.save(cs);
        return res;
    }


}