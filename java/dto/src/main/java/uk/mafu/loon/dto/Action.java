package uk.mafu.loon.dto;

public enum Action {
	ADD_TO_PARENT("ADD_TO_PARENT"),
	ADD("ADD");
	private String val;

	private Action(String val) {
		this.val = val;
	}

	public static boolean isOption(String val) {
		for (Action a : values()) {
			if (a.val == val) {
				return true;
			}
		}
		return false;
	}
}
