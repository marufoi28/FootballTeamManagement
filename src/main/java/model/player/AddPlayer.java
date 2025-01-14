package model.player;

import java.time.LocalDate;

public class AddPlayer {
	private Integer playerId;
	private String firstName;
	private String lastName;
	private String firstNameKana;
	private String lastNameKana;
	private LocalDate birthDate;
	private Integer positionId;
	private Integer uniformNumber;
	private Boolean hasLicense;
	private Boolean isStudent;
	
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstNameKana() {
		return firstNameKana;
	}
	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}
	public String getLastNameKana() {
		return lastNameKana;
	}
	public void setLastNameKana(String lastNameKana) {
		this.lastNameKana = lastNameKana;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public Integer getUniformNumber() {
		return uniformNumber;
	}
	public void setUniformNumber(Integer uniformNumber) {
		this.uniformNumber = uniformNumber;
	}
	public Boolean getHasLicense() {
		return hasLicense;
	}
	public void setHasLicense(Boolean hasLicense) {
		this.hasLicense = hasLicense;
	}
	public Boolean getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}

}
