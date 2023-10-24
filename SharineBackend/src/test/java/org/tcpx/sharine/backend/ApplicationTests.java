package org.tcpx.sharine.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tcpx.sharine.backend.entity.User;
import org.tcpx.sharine.backend.repository.UserRepository;

import java.util.Calendar;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void testJPA() {
	}

}
