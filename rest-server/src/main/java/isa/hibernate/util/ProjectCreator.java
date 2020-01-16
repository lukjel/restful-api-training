package isa.hibernate.util;

import isa.hibernate.domain.Project;

import java.util.Random;

public class ProjectCreator {

	private static String[] first = new String[]{"red", "blue", "white", "fast", "furious", "slow", "big", "small", "huge", "green", "yellow", "easy", "hard", "soft", "quick", "short", "long", "massive", "light", "heavy", "freezed", "hot", "cold", "mellow"};
	private static String[] second = new String[]{"rabbit", "john", "pirate", "cat", "dog", "horse", "house", "jack", "jim", "jacob", "king", "queen", "music", "ring", "book", "pencil", "fire", "stranger", "tv", "pc", "os", "phone", "music"};
	private static Random rnd = new Random();

	public static Project generate() {
		Project proj = new Project();
		proj.setName(getName());
		return proj;
	}

	public static String getName() {
		return first[rnd.nextInt(first.length)] + "-" + second[rnd.nextInt(second.length)];
	}

}
