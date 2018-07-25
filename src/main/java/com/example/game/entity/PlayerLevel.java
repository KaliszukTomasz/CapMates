package com.example.game.entity;

public class PlayerLevel {

	private Integer level;
	private Integer currentExp;
	private static final Integer EXP_TO_NEXT_LVL = 1000;

	public PlayerLevel(){}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCurrentExp() {
		return currentExp;
	}

	public void setCurrentExp(Integer currentExp) {
		this.currentExp = currentExp;
	}

	public static Integer getExpToNextLvl() {
		return EXP_TO_NEXT_LVL;
	}
}
