package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class TuneTest {
	private Tune tune;
	
	@BeforeEach
	void setup() {
		tune = new Tune();
	}
	@Test
	void testGetTitle() {
		tune.setTitle("Street Dance");
		if (!tune.getTitle().equals("Street Dance")) {
			fail("title");
		}
	}

	@Test
	void testSetTitle() {
		tune.setTitle("Street Dance");
		if (!tune.getTitle().equals("Street Dance")) {
			fail("title");
		}
	}

	@Test
	void testGetComposer() {
		Musician composer = new Musician();
		composer.setName("Jim Hall");
		tune.setComposer(composer);
		if (!tune.getComposer().getName().equals("Jim Hall")) {
			fail("composer");
		}
	}

	@Test
	void testSetComposer() {
		Musician composer = new Musician();
		composer.setName("Jim Hall");
		tune.setComposer(composer);
		if (!tune.getComposer().getName().equals("Jim Hall")) {
			fail("composer");
		}
	}
}
