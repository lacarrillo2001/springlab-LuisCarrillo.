package edu.espe.springlab.repository;

import edu.espe.springlab.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    //Buscar estudiante por email y devolver un Optional
    Optional<Student> findByEmail(String email);
    //Responder si existe el estudiante con ese email y devolver un boolean
    boolean existsByEmail(String email);
}

