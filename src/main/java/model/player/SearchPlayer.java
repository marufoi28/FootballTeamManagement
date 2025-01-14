package model.player;

import java.util.List;

public class SearchPlayer {
	
	private Integer playerId;
	private String searchName;
	private List<Integer> positionIdList;
	private Integer ageAbove;
	private Integer ageBelow;
	private Boolean hasLicense;
	private Boolean isStudent;
	private String sortColumn;
	private String sortOrder;
	private Integer currentPage;
	
	public SearchPlayer() {
		
	}
	public SearchPlayer(Integer playerId,String searchName, List<Integer> positionIdList,Integer ageAbove, Integer ageBelow, Boolean hasLicense,Boolean isStudent, String sortColumn, String sortOrder, Integer currentPage) {
		this.playerId = playerId;
		this.searchName = searchName;
		this.positionIdList = positionIdList;
		this.ageAbove = ageAbove;
		this.ageBelow = ageBelow;
		this.hasLicense = hasLicense;
		this.isStudent = isStudent;
		this.sortColumn = sortColumn;
		this.sortOrder = sortOrder;
		this.currentPage = currentPage;
	}
	
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String playerName) {
		this.searchName = playerName;
	}
	public List<Integer> getPositionIdList() {
		return positionIdList;
	}
	public void setPositionIdList(List<Integer> positionIdList) {
		this.positionIdList = positionIdList;
	}
	public Boolean isHasLicense() {
		return hasLicense;
	}
	public void setHasLicense(Boolean hasLicence) {
		this.hasLicense = hasLicence;
	}
	public Boolean isStudent() {
		return isStudent;
	}
	public void setStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getAgeAbove() {
		return ageAbove;
	}

	public void setAgeAbove(Integer ageAbove) {
		this.ageAbove = ageAbove;
	}

	public Integer getAgeBelow() {
		return ageBelow;
	}

	public void setAgeBelow(Integer ageBelow) {
		this.ageBelow = ageBelow;
	}
}
