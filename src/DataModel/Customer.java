package DataModel;

public class Customer {
	private int custId;
	private String custFullName;
	private String custAddress;
	private String custAge;
	private String custGender;
	private String contact;
	private String creditCardNo;
	private String organizationName;
	private String website;
	private String accountValidTill;
	private String nextBillingDate;
	private String discountPercent;
	private String customerType;
	private String userName;

	public Customer() {
		this.custId = 0;
		this.custFullName = "";
		this.custAddress = "";
		this.custAge = "";
		this.custGender = "";
		this.contact = "";
		this.creditCardNo = "";
		this.organizationName = "";
		this.website = "";
		this.accountValidTill = "";
		this.nextBillingDate = "";
		this.discountPercent = "";
		this.customerType = "";
		this.userName = "";
	}

	public int getCustId() {
		return custId;
	}

	public String getCustFullName() {
		return custFullName;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public String getCustAge() {
		return custAge;
	}

	public String getCustGender() {
		return custGender;
	}

	public String getContact() {
		return contact;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public String getWebsite() {
		return website;
	}

	public String getAccountValidTill() {
		return accountValidTill;
	}

	public String getNextBillingDate() {
		return nextBillingDate;
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public String getCustomerType() {
		return customerType;
	}

	public String getUserName() {
		return userName;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public void setCustFullName(String custFullName) {
		this.custFullName = custFullName;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public void setCustAge(String custAge) {
		this.custAge = custAge;
	}

	public void setCustGender(String custGender) {
		this.custGender = custGender;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setAccountValidTill(String accountValidTill) {
		this.accountValidTill = accountValidTill;
	}

	public void setNextBillingDate(String nextBillingDate) {
		this.nextBillingDate = nextBillingDate;
	}

	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
