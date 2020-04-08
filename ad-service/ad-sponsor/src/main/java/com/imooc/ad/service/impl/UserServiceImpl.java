package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IUserService;
import com.imooc.ad.vo.CreateUserRequest;
import com.imooc.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private AdUserRepository userRepository;

    @Override
    public CreateUserResponse ceateteUser(CreateUserRequest request) throws AdException {
        if(request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser oldUsername = userRepository.findByUsername(request.getUsername());
        if(oldUsername != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        return null;
    }
}
