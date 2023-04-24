package shop.mtcoding.servicebank.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.servicebank.core.session.SessionUser;
import shop.mtcoding.servicebank.dto.user.UserRequest;
import shop.mtcoding.servicebank.dto.user.UserResponse;
import shop.mtcoding.servicebank.model.user.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponse.JoinOutDTO 회원가입(@Valid UserRequest.JoinInDTO joinInDTO) {
        return null;
    }

    public SessionUser 로그인(@Valid UserRequest.LoginInDTO loginInDTO) {
        return null;
    }
}
