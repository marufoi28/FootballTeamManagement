package model.opponent;

public class Opponent {
	private int opponentId;
	private String opponentName;
	
	public Opponent(int opponentId) {
		this.opponentId = opponentId;
	}
	
	public Opponent(int opponentId, String opponentName) {
		this.opponentId = opponentId;
		this.opponentName = opponentName;
	}
	
	public Opponent() {
	}

	public int getOpponentId() {
		return opponentId;
	}
	public void setOpponentId(int opponentId) {
		this.opponentId = opponentId;
	}
	public String getOpponentName() {
		return opponentName;
	}
	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}
}
