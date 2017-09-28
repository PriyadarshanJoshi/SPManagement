package com.neosoft.sportsclubmanagement.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contact_details")
public class ContactDetails implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer cdid;
	private String type;
	private Set<UserContact> userContacts = new HashSet<UserContact>(0);
	

	public ContactDetails() {
	}

	public ContactDetails(String type, Set<UserContact> userContacts) {
		this.type = type;
		this.userContacts = userContacts;
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CDID", unique = true, nullable = false)
	public Integer getCdid() {
		return this.cdid;
	}

	public void setCdid(Integer cdid) {
		this.cdid = cdid;
	}

	@Column(name = "TYPE", length = 40)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contactDetails")
	public Set<UserContact> getUserContacts() {
		return this.userContacts;
	}

	public void setUserContacts(Set<UserContact> userContacts) {
		this.userContacts = userContacts;
	}

}
