package isa.hibernate.util;

import isa.hibernate.domain.Developer;

import java.util.Random;

public class DeveloperCreator {

	private static final String[] cities = new String[]{"Gdańsk", "Warszawa", "Kraków", "Pozań", "Toruń", "Szczecin", "Wrocław", "Elbląg", "Olsztyn",
		"Katowice", "Lublin", "Łódź", "Bydgoszcz", "Białystok"};
	private static final String[] firstnames = new String[]{"Jan", "Tomasz", "Piotr", "Marcin", "Robert", "Michał", "Zbigniew", "Krzysztof", "Paweł",
		"Arkadiusz", "Andrzej", "Bartosz", "Grzegorz", "Kamil", "Łukasz", "Jakub", "Adam"};
	private static final String[] lastnames = new String[]{"Kowlaski", "Malinowski", "Piotrowski", "Nowak", "Wiśniewski", "Kowalczyk", "Krawczyk", "Wróbel",
		"Kaczmarek", "Wilk", "Zalewski", "Adamski", "Michalski", "Suchowicz", "Jaworski", "Kołodziejczyk", "Potralski", "Szyman", "Portol", "Kartacz",
		"Walkol", "Mordas", "Kisiel", "Walicki", "Modrzejewski", "Sarna", "Czech", "Podolski", "Miedzian", "Kosztorz"};
	private static Random rnd = new Random();

	public static Developer generate() {
		Developer dev = new Developer();
		dev.setFirstName(firstnames[rnd.nextInt(firstnames.length)]);
		dev.setLastName(lastnames[rnd.nextInt(lastnames.length)]);
		dev.setCity(cities[rnd.nextInt(cities.length)]);
		return dev;
	}

}
