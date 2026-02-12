package com.edu.sena.Petcare.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edu.sena.Petcare.dto.DocumentTypeDTO;
import com.edu.sena.Petcare.models.DocumentType;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DocumentTypeMapper {
    private final ModelMapper modelMapper;

    public DocumentType toEntity(DocumentTypeDTO DocumentTypeDTO){
        return modelMapper.map(DocumentTypeDTO, DocumentType.class);
    }

    public void toEntity(DocumentTypeDTO DocumentTypeDTO, DocumentType documentType){
        modelMapper.map(DocumentTypeDTO, documentType);
    }

    public DocumentTypeDTO toDTO(DocumentType documentType){
        return modelMapper.map(documentType, DocumentTypeDTO.class);
    }
}
