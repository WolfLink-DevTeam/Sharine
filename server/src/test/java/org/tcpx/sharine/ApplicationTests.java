package org.tcpx.sharine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.repository.UserRepository;

@SpringBootTest
class ApplicationTests {
    @Autowired
    ApplicationContext context;
    @Autowired
    UserRepository userRepository;
    @Test
    void jpaTest() {
        userRepository.findById(0L);
        User user = User.builder()
                .account("3401286177")
                .password("passwd")
                .avatar("http://www.test.com/test.png")
                .content("你好你好")
                .nickname("米可")
                .build();
        userRepository.save(user);
        userRepository.findById(0L);
        userRepository.findById(1L);
        userRepository.findById(2L);
    }
}
