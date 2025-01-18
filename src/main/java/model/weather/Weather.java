package model.weather;

public class Weather {
	private String condition;
	private Double temperature;
	private Integer humidity;
	
	public Weather(String condition, Double temperature, Integer humidity) {
		this.condition = condition;
		this.temperature = temperature;
		this.humidity = humidity;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
}
