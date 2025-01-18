package model.address;

public class Address {
    private int addressId;
    private String prefecture;
    private String city;
    private String streetAddress;

    public Address(int addressId, String prefecture, String city, String streetAddress) {
        this.addressId = addressId;
        this.prefecture = prefecture;
        this.city = city;
        this.streetAddress = streetAddress;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
}
