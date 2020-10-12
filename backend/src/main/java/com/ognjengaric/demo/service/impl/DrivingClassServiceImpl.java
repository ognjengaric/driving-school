package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.dto.AppointmentDTO;
import com.ognjengaric.demo.dto.ClassTableViewDto;
import com.ognjengaric.demo.enums.ClassStatusType;
import com.ognjengaric.demo.repository.DrivingClassRepository;
import com.ognjengaric.demo.service.DrivingClassService;
import com.ognjengaric.demo.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrivingClassServiceImpl implements DrivingClassService {

    @Autowired
    DrivingClassRepository drivingClassRepository;

    @Autowired
    UserService userService;

    @Override
    public List<DrivingClass> findByInstructor(Instructor instructor) {
        return drivingClassRepository.findByInstructor(instructor);
    }

    @Override
    public Page<DrivingClass> findByInstructor(Instructor instructor, Pageable pageable) {
        return drivingClassRepository.findByInstructor(instructor, pageable);
    }

    @Override
    public List<DrivingClass> findByCandidate(Candidate candidate) {
        return drivingClassRepository.findByCandidate(candidate);
    }

    @Override
    public Page<DrivingClass> findByCandidate(Candidate candidate, Pageable pageable) {
        return drivingClassRepository.findByCandidate(candidate, pageable);
    }

    @Override
    public List<DrivingClass> findAll() {
        return drivingClassRepository.findAll();
    }

    @Override
    public ClassTableViewDto findById(Integer id) {
        return drivingClassRepository.findById(id).map(ClassTableViewDto::new).orElse(null);
    }

    @Override
    public Integer save(AppointmentDTO appointmentDTO, String user_id) {
        User user = userService.findById(user_id);
        Candidate candidate;
        Instructor instructor;

        try {
            candidate = (Candidate) user;
            instructor = candidate.getInstructor();
        } catch (ClassCastException e){
            return null;
        }

        DateTime start = DateTime.parse(appointmentDTO.getStartDate());
        DateTime end =  DateTime.parse(appointmentDTO.getEndDate());

        DrivingClass drivingClass = new DrivingClass(start, end);

        candidate.addDrivingClass(drivingClass);
        instructor.addDrivingClass(drivingClass);
        candidate.getDrivingSchool().addDrivingClass(drivingClass);

        drivingClass = drivingClassRepository.save(drivingClass);

        return drivingClass.getId();
    }

    @Override
    public List<AppointmentDTO> getClassesForUserScheduler(String user_id){
        User user = userService.findById(user_id);
        Instructor instructor;

        try {
            instructor = ((Candidate) user).getInstructor();
        } catch (ClassCastException e){
            return null;
        }

        return findByInstructor(instructor)
                .stream()
                .map(AppointmentDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClassTableViewDto> getMyClasses(String id, int page, int size) {
        User user = userService.findById(id);
        Page<DrivingClass> ret;

        Pageable pageable = PageRequest.of(page, size);

        if(user instanceof Candidate)
            ret = findByCandidate((Candidate) user, pageable);

        else if(user instanceof Instructor)
            ret =  findByInstructor((Instructor) user, pageable);

        else
            return null;

        return ret.map(ClassTableViewDto::new);
    }

    public boolean setStatus(Integer id, ClassStatusType status){
        DrivingClass drivingClass = drivingClassRepository.findById(id).orElse(null);

        if(drivingClass == null)
            return false;

        drivingClass.setStatus(status);
        drivingClassRepository.save(drivingClass);

        return true;
    }

    @Override
    public boolean completeClass(ClassTableViewDto dto) {

        Integer id = Integer.parseInt(dto.getId());

        DrivingClass drivingClass = drivingClassRepository.findById(id).orElse(null);

        if(drivingClass == null)
            return false;

        drivingClass.completeClass(dto);

        drivingClassRepository.save(drivingClass);

        return true;
    }
}
