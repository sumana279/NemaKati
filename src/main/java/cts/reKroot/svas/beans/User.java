package cts.reKroot.svas.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cts.reKroot.svas.model.Utility;

@Entity
@Table(name = "User_master")
public class User {
	@Id
	@Column(name = "CTS_ID", nullable = false)
	private long ctsId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String phoneNo;
	private String email;
	private String password;
	private String securityQ;
	private String securityAns;
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"))
	private List<RoleMaster> roles = new ArrayList<RoleMaster>();
	private Date createDate;
	private Date modifiedDate;
	private final String modifiedBy = "frontEndUser";

	public long getCtsId() {
		return ctsId;
	}

	public void setCtsId(long ctsId) {
		this.ctsId = ctsId;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() throws Exception {
		return Utility.decryptPassword(password);
	}

	public void setPassword(String password) throws Exception {
		this.password = Utility.encryptPassword(password);
	}

	public String getSecurityQ() {
		return securityQ;
	}

	public void setSecurityQ(String securityQ) {
		this.securityQ = securityQ;
	}

	public String getSecurityAns() {
		return securityAns;
	}

	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}

	public List<RoleMaster> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleMaster> roles) {
		this.roles = roles;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

}
