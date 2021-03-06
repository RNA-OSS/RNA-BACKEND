package com.dna.rna.service;

import com.dna.rna.domain.school.School;
import com.dna.rna.domain.school.SchoolRepository;
import com.dna.rna.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

/**
 * School에서 제공하는 service
 *
 * SchoolService.java
 * created 2020.4.1
 * @author Hyounjun kim <4whomtbts@gmail.com>
 *
 */

@Service
public class SchoolService {

    private static final Logger logger= LoggerFactory.getLogger(SchoolService.class);

    private SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }


    public School createSchool(String schoolName) {
        requireNonNull(schoolName, "schoolName is null");

        School existingSchool = schoolRepository.findSchoolBySchoolName(schoolName);
        if(existingSchool != null) {
            throw new AlreadyExistsException("schoolName", schoolName);
        }

        School newSchool = School.of(schoolName);
        schoolRepository.save(newSchool);
        return newSchool;
    }
}
