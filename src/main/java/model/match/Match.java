package model.match;

import java.time.LocalDate;
import java.time.LocalTime;

import model.field.Field;
import model.opponent.Opponent;
import model.weather.Weather;

public class Match {
	private Integer matchId;
	private Field field;
	private LocalDate eventDate;
	private LocalTime eventStartTime;
	private Weather weather; 
	private Opponent opponent;
	private LocalDate registerDate;
	public Integer getMatchId() {
		return matchId;
	}
	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public LocalDate getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
	public LocalTime getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(LocalTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public LocalDate getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	public Opponent getOpponent() {
		return opponent;
	}
	public void setOpponent(Opponent opponent) {
		this.opponent = opponent;
	}
}
