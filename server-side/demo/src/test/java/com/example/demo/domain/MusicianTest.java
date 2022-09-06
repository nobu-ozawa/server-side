package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class MusicianTest {
	private Musician musician;
	@BeforeEach
	void setup() {
		musician = new Musician();
	}
	@Test
	void testGetName() {
		musician.setName("Jim Hall");
		if (!musician.getName().equals("Jim Hall")) {
			fail("get name");
		}
	}

	@Test
	void testSetName() {
		musician.setName("Jim Hall");
		if (!musician.getName().equals("Jim Hall")) {
			fail("get name");
		}
	}
}
