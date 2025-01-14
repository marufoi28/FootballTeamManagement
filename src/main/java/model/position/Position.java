package model.position;

public class Position {
	private int positionId;
	private String positionName;
	private String positionClassification;
	
	public Position() {
		
	}
	public Position(int positionId) {
		this.positionId = positionId;
	}
	public Position(int positionId, String positionName, String positionClassification) {
		this.positionId = positionId;
		this.positionName = positionName;
		this.positionClassification = positionClassification;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionClassification() {
		return positionClassification;
	}
	public void setPositionClassification(String positionClassification) {
		this.positionClassification = positionClassification;
	}
	
	/* TODO equals(), toString(), hashCode()のオーバーライド */
}
