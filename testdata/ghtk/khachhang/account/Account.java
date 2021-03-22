package ghtk.khachhang.account;

public class Account {
	private String email = "lienltb@ghtk.vn";
	private String password = "shoptét12345";
	private String shopName = "S14791714 - Shop test lien";
	
	public Account() {
	}
	
	public Account(String email, String password, String shopName) {
		this.email = email;
		this.password = password;
		this.shopName = shopName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
}
