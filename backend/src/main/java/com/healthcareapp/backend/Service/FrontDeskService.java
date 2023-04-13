package com.healthcareapp.backend.Service;

import com.healthcareapp.backend.Exception.ResourceNotFoundException;
import com.healthcareapp.backend.Model.Admin;
import com.healthcareapp.backend.Model.FrontDesk;
import com.healthcareapp.backend.Model.Hospital;
import com.healthcareapp.backend.Repository.FrontDeskRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FrontDeskService {
    private FrontDeskRepository frontDeskRepository;
    private AuthorizationService authorizationService;
//    private HospitalService hospitalService;
    private AdminService adminService;

    public FrontDeskService(FrontDeskRepository frontDeskRepository, AdminService adminService) {
        this.frontDeskRepository = frontDeskRepository;
        this.adminService = adminService;
    }

    public FrontDesk addFrontDesk(FrontDesk frontDesk, String userId) throws RuntimeException{
        authorizationService.checkIfUserIdExists(userId);

        Admin admin = adminService.getAdminByUserId(userId);

        Hospital hospital = admin.getHospital();

        frontDesk.setHospital(hospital);
        frontDesk.setUserType("Front Desk");

        frontDeskRepository.save(frontDesk);
        return frontDesk;
    }

//    public List<FrontDesk> getAllFrontDeskByHospital(Hospital hospital){
//        List<FrontDesk> frontDeskList= frontDeskRepository.findByHospital(hospital);
//        return frontDeskList;
//    }

    public FrontDesk updateFrontDesk(FrontDesk frontDesk) throws RuntimeException{
        Optional<FrontDesk> updatedFrontDesk = frontDeskRepository.findById(frontDesk.getAuthId());

        if(updatedFrontDesk.isEmpty()){
            throw new ResourceNotFoundException("Front Desk with id: "+ frontDesk.getAuthId()+ " not found");
        }

        updatedFrontDesk.get().setName(frontDesk.getName());
        updatedFrontDesk.get().setUserId(frontDesk.getUserId());
        updatedFrontDesk.get().setPassword(frontDesk.getPassword());

        frontDeskRepository.save(updatedFrontDesk.get());

        return updatedFrontDesk.get();
    }

    public FrontDesk getFrontDeskByUserId(String userId){
        Optional<FrontDesk> frontDesk = frontDeskRepository.findByUserId(userId);
        if(frontDesk.isEmpty()){
            throw new ResourceNotFoundException("Front Desk with userId: "+ userId+ " not found");
        }
        return frontDesk.get();
    }
}
