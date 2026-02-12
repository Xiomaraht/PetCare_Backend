package com.edu.sena.Petcare.service.Impl;

import com.edu.sena.Petcare.dto.ExamsRegistrationDTO; 
import com.edu.sena.Petcare.dto.ExamsDTO;
import com.edu.sena.Petcare.Exceptions.ResourceNotFoundException; 
import com.edu.sena.Petcare.mapper.ExamMapper;
import com.edu.sena.Petcare.models.Exams;
import com.edu.sena.Petcare.repository.ExamsRepository;
import com.edu.sena.Petcare.service.ExamsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors; 

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamsService {

    private final ExamsRepository examsRepository;

    @Override
    public ExamsDTO create(ExamsRegistrationDTO dto) { 
        Exams exam = ExamMapper.toEntity(dto);
        return ExamMapper.toDTO(examsRepository.save(exam));
    }

    @Override
    public ExamsDTO update(Long id, ExamsRegistrationDTO dto) {
        Exams exam = examsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + id)); 

        exam.setName(dto.getName());
        exam.setDescription(dto.getDescription());

        return ExamMapper.toDTO(examsRepository.save(exam));
    }

    @Override
    public List<ExamsDTO> findAll() {
        return examsRepository.findAll()
                .stream()
                .map(ExamMapper::toDTO)
                .collect(Collectors.toList()); 
    }

    @Override
    public List<ExamsDTO> findAllActive() {
        return examsRepository.findByStatusTrue()
                .stream()
                .map(ExamMapper::toDTO)
                .collect(Collectors.toList()); 
    }

    @Override
    public ExamsDTO findById(Long id) {
        Exams exam = examsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + id)); 
        return ExamMapper.toDTO(exam);
    }

    @Override
    public void deactivate(Long id) {
        Exams exam = examsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + id + " for deactivation")); 
        exam.setStatus(false); 
        examsRepository.save(exam);
    }

    @Override
    public void activate(Long id) {
        Exams exam = examsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with ID: " + id + " for activation")); 
        exam.setStatus(true); 
        examsRepository.save(exam);
    }
}
