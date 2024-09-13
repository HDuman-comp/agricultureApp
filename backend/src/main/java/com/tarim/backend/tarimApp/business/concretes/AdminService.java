package com.tarim.backend.tarimApp.business.concretes;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IAdminService;
import com.tarim.backend.tarimApp.business.dto.response.admin.GetAllUserEmailandName;
import com.tarim.backend.tarimApp.business.dto.response.admin.GetUserAllDetailResponse;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.core.entities.User;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService implements IAdminService {

   private IUserRepository userRepository;
   private ModelMapperService modelMapperService;
    @Override
    public List<GetAllUserEmailandName> getAllUserEmailandName() {
        List<User> users = userRepository.findAll();
        List<GetAllUserEmailandName> getAllUserEmailandNameResponses = users.stream()
                .map(user -> this.modelMapperService.forResponse()
                        .map(user, GetAllUserEmailandName.class))
                .collect(Collectors.toList());

        return getAllUserEmailandNameResponses;
    }

    @Override
    public GetUserAllDetailResponse getUserAllDetail(int id) {
        Optional<User> user = userRepository.findById(id);
        return this.modelMapperService.forResponse()
                .map(user, GetUserAllDetailResponse.class);
    }
}
