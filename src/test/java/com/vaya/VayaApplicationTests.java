package com.vaya;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VayaApplicationTests {

	@Test
	public void contextLoads() {
		assertEquals("ABC","ABC");
	}

}
