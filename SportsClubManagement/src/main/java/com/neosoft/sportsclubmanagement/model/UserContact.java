package com.neosoft.sportsclubmanagement.model;
// Generated Sep 22, 2017 12:01:42 PM by Hibernate Tools 5.1.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserContact generated by hbm2java
 */
@Entity
@Table(name = "user_contact")
public class UserContact implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer cid;
	private ContactDetails contactDetails;
	private User user;
	private String description;
	private Date createdOn;
	private Date updatedOn;
	private boolean status;
	private Set<UserContacts> userContactses = new HashSet<UserContacts>(0);

	public UserContact() {
	}

	public UserContact(ContactDetails contactDetails, User user, String description, Date createdOn, Date updatedOn,
			boolean status) {
		this.contactDetails = contactDetails;
		this.user = user;
		this.description = description;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.status = status;
	}

	public UserContact(ContactDetails contactDetails, User user, String description, Date createdOn, Date updatedOn,
			boolean status, Set<UserContacts> userContactses) {
		this.contactDetails = contactDetails;
		this.user = user;
		this.description = description;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.status = status;
		this.userContactses = userContactses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CID", unique = true, nullable = false)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTACT_DETAILS_CDID", nullable = false)
	public ContactDetails getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_UID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "DESCRIPTION", nullable = false, length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", nullable = false, length = 19)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON", nullable = false, length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "STATUS", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userContact")
	public Set<UserContacts> getUserContactses() {
		return this.userContactses;
	}

	public void setUserContactses(Set<UserContacts> userContactses) {
		this.userContactses = userContactses;
	}

}
