package com.thebo.ichat.system;

import com.thebo.framework.constants.PermissionResult;
import com.thebo.framework.dto.LoginResponse;
import com.thebo.framework.dto.LoginUserDto;
import com.thebo.framework.exception.DataCommitException;
import com.thebo.framework.service.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    public LoginUserDto userLoginSuccess(LoginUserDto user, String token,
                                         String loginIp, HttpServletRequest request,
                                         HttpServletResponse response) throws DataCommitException {
        return null;
    }

    public boolean userLoginSuccess(LoginUserDto user,
                                    HttpServletRequest request, HttpServletResponse response) {
        return false;
    }

    public PermissionResult checkPermission(HttpServletRequest request) {
        return null;
    }

    public LoginResponse login(String loginName, String password,
                               String loginIp, HttpServletRequest request,
                               HttpServletResponse response) throws DataCommitException {
        return null;
    }

    public void logout(String userId, String loginIp)
            throws DataCommitException {

    }

}
