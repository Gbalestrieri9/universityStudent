package com.br.universityStudent.service;

import com.br.universityStudent.model.Endereco;
import com.br.universityStudent.model.Student;
import com.br.universityStudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ConsumeApi consumeApi;

    @Autowired
    private IConverteDados converteDados;

    public Student processStudentAddress(Student student) {
        String url = "https://viacep.com.br/ws/" + student.getCep() + "/json/";
        String json = consumeApi.obterDados(url);
        Endereco endereco = converteDados.obterDados(json, Endereco.class);

        if (endereco != null) {
            student.setCidade(endereco.getLocalidade());
        } else {
            throw new RuntimeException("Endereço não encontrado para o CEP informado.");
        }
        return student;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
