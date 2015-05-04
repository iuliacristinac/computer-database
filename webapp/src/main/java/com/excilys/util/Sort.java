package com.excilys.util;

import java.util.HashSet;
import java.util.Set;

public enum Sort {
	ASC, DESC;

	private static final Set<String> VALUES;
	
	static {
		VALUES = new HashSet<>();
		for (Sort s : Sort.values()) {
			VALUES.add(s.toString());
		}
	}
	
	public static boolean isValid(String sort) {
		return VALUES.contains(sort);
	}
}