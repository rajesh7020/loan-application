package com.brainerhub.loan.service;

import com.brainerhub.loan.entity.User;
import com.brainerhub.loan.responseDto.GenericResponse;

public interface UserService {

    GenericResponse createUser(User user);

}
