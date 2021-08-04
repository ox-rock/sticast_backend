package com.sticast.service;

import com.sticast.configurations.springboot.SpringBootConfig;
import com.sticast.entity.User;
import com.sticast.entity.UserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringBootConfig.class)
public class UserServiceIT {

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Nested
    class findByUsername {
        @Test
        public void findByUsername_TC0() {
            user = userService.findByUsername("pippo");
            assertAll(() -> assertNotEquals(null, user),
                    () -> assertEquals("pippo", user.getUsername()),
                    () -> assertEquals("test@test.it", user.getEmail()),
                    () -> assertEquals(0, user.getBudget()));
        }

        @Test
        public void findByUsername_TC1() {
            user = userService.findByUsername("plutozzo");
            assertTrue(user == null);
        }
    }

    @Nested
    class save {
        @Test
        public void save_TC0() {
            user.setUsername("test");
            user.setPassword("$2a$10$T.EN9X.UXoB6pqPAHY8Kb./5KjC2Jr4VvCWjOZ3yvQpsvXNq0XVwO");
            user.setEmail("aaadassa@dsadsa.it");
            user.setStatus("ACTIVE");
            UserDetails ud = new UserDetails();
            ud.setUser(user);
            user.setUserDetails(ud);
            userService.save(user);
        }

        @Test
        public void save_TC1() {
            user.setUsername("pippo");
            user.setPassword("$2a$10$T.EN9X.UXoB6pqPAHY8Kb./5KjC2Jr4VvCWjOZ3yvQpsvXNq0XVwO");
            user.setEmail("test@tesdt.it");
            user.setStatus("ACTIVE");
            assertThrows(DataIntegrityViolationException.class, () -> userService.save(user));
        }

        @Test
        public void save_TC2() {
            user.setEmail("test@test.it");
            assertThrows(DataIntegrityViolationException.class, () -> userService.save(user));
        }

        @Test
        public void save_TC3() {
            user.setUsername("dda2dasdsafagey5f3");
            user.setPassword("$2a$10$T.EN9X.UXoB6pqPAHY8Kb./5KjC2Jr4VvCWjOZ3yvQpsvXNq0XVwO");
            assertThrows(DataIntegrityViolationException.class, () -> userService.save(user));
        }

        @Test
        public void save_TC4() {
            user.setUsername("dda2dasdsafagey5f3");
            user.setEmail("test2@test.it");
            assertThrows(DataIntegrityViolationException.class, () -> userService.save(user));
        }

        @Test
        public void save_TC5() {
            user.setPassword("$2a$10$T.EN9X.UXoB6pqPAHY8Kb./5KjC2Jr4VvCWjOZ3yvQpsvXNq0XVwO");
            user.setEmail("test2@test.it");
            assertThrows(DataIntegrityViolationException.class, () -> userService.save(user));
        }

        @Test
        public void save_TC6() {
            user.setUsername("dda2dasdsafagey5f3dda2dasdsafagey5f3dda2dasdsafagey5f3dda2dasdsafagey5f3dda2dasdsafagey5f3dda2dasdsafagey5f3");
            user.setPassword("$2a$10$T.EN9X.UXoB6pqPAHY8Kb./5KjC2Jr4VvCWjOZ3yvQpsvXNq0XVwO");
            user.setEmail("test2@test.it");
            assertThrows(DataIntegrityViolationException.class, () -> userService.save(user));
        }

        @Test
        public void save_TC7() {
            user.setUsername("ddda2dasdsafagey5f3");
            user.setPassword("$2a$10$T.EN9X.UXoB6pqPAHY8Kb./5KjC2Jr4VvCWjOZ3yvQpsvXNq0XVwO");
            user.setEmail("test2@testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest.it");
            assertThrows(DataIntegrityViolationException.class, () -> userService.save(user));
        }
    }

    @Nested
    class delete {
        @Test
        public void delete_TC0() {
            userService.deleteByUsername("test");
        }
    }
}