package shop.mtcoding.servicebank.controller;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.servicebank.core.advice.MyValidAdvice;
import shop.mtcoding.servicebank.dto.user.UserRequest;
import shop.mtcoding.servicebank.dto.user.UserResponse;
import shop.mtcoding.servicebank.model.user.User;
import shop.mtcoding.servicebank.service.UserService;

// filter, ds(exh, interceptor, viewresolver, messageconverter), controller 레이어를 띄운다.
@WebMvcTest(UserController.class)
@EnableAspectJAutoProxy // AOP 작동 활성화
@Import(MyValidAdvice.class) // Aspect 클래스 로드
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    private ObjectMapper om = new ObjectMapper();

    // @Spy 진짜로 넣는 것
    @MockBean
    private UserService userService;

    @Test
    public void join_test() throws Exception {
        // given
        UserRequest.JoinInDTO joinInDTO = new UserRequest.JoinInDTO();
        joinInDTO.setUsername("a");
        joinInDTO.setPassword("");
        joinInDTO.setFullName("쌀만고");
        joinInDTO.setEmail("ssar@nate.com");
        String requestBody = om.writeValueAsString(joinInDTO);

        // stub (가정)
        User user = User.builder()
                .id(1L)
                .username("a")
                .password("")
                .email("ssar@nate.com")
                .fullName("쌀만고")
                .createdAt(LocalDateTime.now())
                .build();

        UserResponse.JoinOutDTO joinOutDTO = new UserResponse.JoinOutDTO(user);
        Mockito.when(userService.회원가입(any())).thenReturn(joinOutDTO);

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/join").content(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
        String resp = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(resp);

        // then
        resultActions.andExpect(MockMvcResultMatchers
                .jsonPath("$.status")
                .value(200));
        resultActions.andExpect(MockMvcResultMatchers
                .jsonPath("$.data.id")
                .value(1));
        resultActions.andExpect(MockMvcResultMatchers
                .jsonPath("$.data.username")
                .value("ssar"));
        resultActions.andExpect(MockMvcResultMatchers
                .jsonPath("$.data.fullName")
                .value("쌀만고"));

    }
}
