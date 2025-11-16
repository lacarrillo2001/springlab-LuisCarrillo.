package edu.espe.springlab.web.controller;

import edu.espe.springlab.domain.Student;
import edu.espe.springlab.dto.StudentRequestData;
import edu.espe.springlab.dto.StudentResponse;
import edu.espe.springlab.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) { this.studentService = studentService; }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequestData request){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(request));
    }


    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAll(){
        return ResponseEntity.ok(studentService.list());
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getById(id));
    }


    //servicio para obtener estadisticas de estudiantes
    @GetMapping("/stat")
    public ResponseEntity<String> getStats(){
        long totalStudents = studentService.list().size();
        long activeStudents = studentService.list().stream().filter(StudentResponse::isActive).count();
        long inactiveStudents = totalStudents - activeStudents;

        String stats = String.format("Total Students: %d, Active Students: %d, Inactive Students: %d",
                totalStudents, activeStudents, inactiveStudents);

        return ResponseEntity.ok(stats);
    }

    //Desactivar estudiante por ID
    //unitaria
    //Luis Carrillo
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<StudentResponse> deactivate(@PathVariable Long id){
        return ResponseEntity.ok(studentService.deactivate(id));
    }
}
