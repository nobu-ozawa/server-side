package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class PlayingTest {
	private Playing playing;
	
	@BeforeEach
	void setup() {
		playing = new Playing();
	}
	@Test
	void testGetPlayer() {
		Musician musician = new Musician();
		musician.setName("Nobutoshi Ozawa");
		playing.setPlayer(musician);
		playing.setPart(Part.GT);
		if (!playing.getPlayer().getName().equals("Nobutoshi Ozawa")) {
			fail("get player");
		}
	}

	@Test
	void testSetPlayer() {
		Musician musician = new Musician();
		musician.setName("Nobutoshi Ozawa");
		playing.setPlayer(musician);
		playing.setPart(Part.GT);
		if (!playing.getPlayer().getName().equals("Nobutoshi Ozawa")) {
			fail("get player");
		}
	}

	@Test
	void testGetPart() {
		Musician musician = new Musician();
		musician.setName("Nobutoshi Ozawa");
		playing.setPlayer(musician);
		playing.setPart(Part.GT);
		if (playing.getPart() != Part.GT) {
			fail("get part");
		}
	}

	@Test
	void testSetPart() {
		Musician musician = new Musician();
		musician.setName("Nobutoshi Ozawa");
		playing.setPlayer(musician);
		playing.setPart(Part.GT);
		if (playing.getPart() != Part.GT) {
			fail("get part");
		}
	}
}
