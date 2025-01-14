package model.player;

import java.time.LocalDate;

import model.position.Position;

public class Player {
	private Integer playerId;
	private String firstName;
	private String lastName;
	private String firstNameKana;
	private String lastNameKana;
	private LocalDate birthDate;
	private Position position;
	private Integer uniformNumber;
	private Boolean hasLicense;
	private Boolean isStudent;
	private LocalDate registerDate;
	
	public Player() {
		
	}
	
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
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	public Integer getUniformNumber() {
		return uniformNumber;
	}
	public void setUniformNumber(Integer uniformNumber) {
		this.uniformNumber = uniformNumber;
	}
	public Boolean isHasLicense() {
		return hasLicense;
	}
	public void setHasLicense(Boolean hasLicense) {
		this.hasLicense = hasLicense;
	}
	public Boolean isStudent() {
		return isStudent;
	}
	public void setStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}
	
	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}
	
	@Override
	public String toString() {
	    return "Player{" +
	            "playerId=" + playerId +
	            ", firstName='" + firstName + '\'' +
	            ", lastName='" + lastName + '\'' +
	            ", firstNameKana='" + firstNameKana + '\'' +
	            ", lastNameKana='" + lastNameKana + '\'' +
	            ", birthDate=" + birthDate +
	            ", positionId=" + position.getPositionId() +
	            ", uniformNumber=" + uniformNumber +
	            ", hasLicense=" + hasLicense +
	            ", registerDate=" + registerDate +
	            '}';
	}
	
	@Override
	public boolean equals(Object o) {
	    if(this == o) return true;
	    if(o == null) return false;
	    if(!(o instanceof Player)) return false;
	    Player player = (Player) o;
	    return playerId == player.playerId;
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(playerId);
	}
}
