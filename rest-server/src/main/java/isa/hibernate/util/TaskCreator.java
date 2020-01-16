package isa.hibernate.util;

import isa.hibernate.domain.Task;

import java.util.Random;

public class TaskCreator {

	private static String[] first = new String[]{"add", "remove", "clean", "refactor", "update", "create"};
	private static String[] second = new String[]{"important", "main", "additional", "minor", "major", "second", "first", "third", "projected", "missing", "proper", "new"};
	private static String[] third = new String[]{"user", "admin", "book", "phone", "keyboard", "input", "form", "computer", "day", "auction", "stock", "shop", "item", "project", "bid", "currency", "withdraw", "deposit"};
	private static String[] fourth = new String[]{"window", "button", "label", "screen", "flow", "list", "group", "title", "table", "view"};
	private static Random rnd = new Random();

	public static Task generate() {
		Task task = new Task();
		task.setTitle(getTitle());
		task.setClosed(false);
		return task;
	}

	public static String getTitle() {
		return first[rnd.nextInt(first.length)] + " " + second[rnd.nextInt(second.length)] + " " + third[rnd.nextInt(third.length)] + " " + fourth[rnd.nextInt(fourth.length)];
	}

}
