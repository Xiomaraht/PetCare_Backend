package com.edu.sena.Petcare.mapper;

import com.edu.sena.Petcare.dto.ExamsDTO;
import com.edu.sena.Petcare.dto.ExamsRegistrationDTO;
import com.edu.sena.Petcare.models.Exams;


public class ExamMapper {

    public static Exams toEntity(ExamsRegistrationDTO dto) {
        Exams exam = new Exams();
        exam.setName(dto.getName());

        return exam;
    }
    public static ExamsDTO toDTO(Exams exam) {
        ExamsDTO dto = new ExamsDTO();
        dto.setId(exam.getId());
        dto.setName(exam.getName());
        dto.setDescription(exam.getDescription());
        dto.setStatus(exam.getStatus());
        return dto;
    }
}
