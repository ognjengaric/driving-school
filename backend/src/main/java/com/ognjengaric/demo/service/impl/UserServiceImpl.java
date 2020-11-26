package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.*;
import com.ognjengaric.demo.dto.CourseProgressDTO;
import com.ognjengaric.demo.dto.NewUserDTO;
import com.ognjengaric.demo.enums.ClassStatusType;
import com.ognjengaric.demo.enums.LicenceCategory;
import com.ognjengaric.demo.enums.RoadType;
import com.ognjengaric.demo.enums.TrafficIntensityType;
import com.ognjengaric.demo.repository.UserRepository;
import com.ognjengaric.demo.service.DrivingSchoolService;
import com.ognjengaric.demo.service.UserService;
import com.ognjengaric.demo.util.ClassesCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassesCalculator classesCalculator;

    @Autowired
    DrivingSchoolService drivingSchoolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static String DEFAULT_SORT_COLUMN = "id";

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User save(NewUserDTO userDTO) throws ClassCastException {

        User user;
        DrivingSchool school = drivingSchoolService.findById(userDTO.getSchoolId());

        if(userDTO.getRole().equals("Candidate")) {
            Instructor instructor = (Instructor) findById(Integer.parseInt(userDTO.getInstructorId()));
            user = new Candidate(userDTO, instructor);
            school.addCandidate((Candidate) user);
            user.getRoles().add(new Role("ROLE_CANDIDATE"));
            return save(user);
        } else if(userDTO.getRole().equals("Instructor")){
            user = new Instructor(userDTO, school);
            school.addInstructor((Instructor) user);
            user.getRoles().add(new Role("ROLE_INSTRUCTOR"));
            return save(user);
        }

        return null;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(DEFAULT_SORT_COLUMN));
        return userRepository.findAll(pageable);
    }

    @Override
    public CourseProgressDTO getCourseProgress(String id) {

        User user = userRepository.findById(Integer.parseInt(id)).get();
        Candidate candidate;
        CourseProgressDTO dto;

        if(user instanceof Candidate){
            candidate = (Candidate) user;
            dto = calculateProgress(candidate);
            return dto;
        }

        return null;
    }

    private CourseProgressDTO calculateProgress(Candidate candidate){
        LicenceCategory curr = candidate.getCurrentLicence();
        List<LicenceCategory> owned = candidate.getOwnedLicences();


        int total = candidate.getTotalClassesFinished();
        int totalNeeded = classesCalculator.getTotal(curr, owned);

        long drivingRange = candidate.getDrivingRangeFinished();
        int drivingRangeNeeded = classesCalculator.getDrivingRange(curr, owned);

        List<Long> intTotal = new ArrayList<>();
        List<Integer> intNeeded = new ArrayList<>();

        Arrays.stream(TrafficIntensityType.values()).forEach(i ->{
            if(!i.equals(TrafficIntensityType.UNDEFINED)) {
                intTotal.add(candidate.getIntensityFinished(i));
                intNeeded.add(classesCalculator.getIntensity(i, curr, owned));
            }
        });

        long rural = candidate.getRoadFinished(RoadType.RURAL);
        int ruralNeeded = classesCalculator.getRural(curr, owned);

        long load = candidate.getWithLoadFinished();
        int loadNeeded = classesCalculator.getWithLoad(curr);

        int night = candidate.getNightFinished(classesCalculator);
        int nightNeeded = classesCalculator.getNight(curr, owned);

        return new CourseProgressDTO(total+"/"+totalNeeded, drivingRange+"/"+drivingRangeNeeded, intTotal.get(0)+"/"+intNeeded.get(0),
                intTotal.get(1)+"/"+intNeeded.get(1), intTotal.get(2)+"/"+intNeeded.get(2), rural+"/"+ruralNeeded, load+"/"+loadNeeded, night+"/"+nightNeeded);
    };
}
