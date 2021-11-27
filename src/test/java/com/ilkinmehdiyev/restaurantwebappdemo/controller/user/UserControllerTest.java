package com.ilkinmehdiyev.restaurantwebappdemo.controller.user;

import com.ilkinmehdiyev.restaurantwebappdemo.TestsConfiguration;
import com.ilkinmehdiyev.restaurantwebappdemo.dto.user.ApplicationUserDTO;
import com.ilkinmehdiyev.restaurantwebappdemo.models.User.ApplicationUser;
import com.ilkinmehdiyev.restaurantwebappdemo.service.interfaces.user.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

class UserControllerTest extends TestsConfiguration {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private static UserService userService;

    private static ApplicationUser requestObject;
    private static ApplicationUserDTO requestObjectDTO;
    private static String requestBody;
    private static String requestBodyDTO;

    private static ApplicationUser responseObject;
    private static ApplicationUserDTO responseObjectDTO;
    private static String responseBody;
    private static String responseBodyDTO;


    @BeforeAll
    public static void init() {

        ModelMapper mapper = new ModelMapper();

        requestObject = new ApplicationUser();
        requestObject.setId(1L);
        requestObjectDTO = mapper.map(requestObject, ApplicationUserDTO.class);

    }
}