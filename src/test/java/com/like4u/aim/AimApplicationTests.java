package com.like4u.aim;

import com.like4u.aim.common.utils.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class AimApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd号-hh:mm:ss");
        String format = dtf.format(now);
        System.out.println(format);
    }

}
