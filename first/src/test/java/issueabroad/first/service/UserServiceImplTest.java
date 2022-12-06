package issueabroad.first.service;

import issueabroad.first.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    public void test1() {
        UserDTO userDTO = UserDTO.builder()
                .title("adsf")
                .content("zzzz")
                .build();
        System.out.println(service.write(userDTO));
    }

}