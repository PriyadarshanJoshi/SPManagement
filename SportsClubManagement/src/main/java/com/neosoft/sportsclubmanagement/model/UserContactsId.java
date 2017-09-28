package com.neosoft.sportsclubmanagement.model;
// Generated Sep 22, 2017 12:01:42 PM by Hibernate Tools 5.1.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserContactsId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int userUid;
	private int contactsCid;

	public UserContactsId() {
	}

	public UserContactsId(int userUid, int contactsCid) {
		this.userUid = userUid;
		this.contactsCid = contactsCid;
	}

	@Column(name = "user_uid", nullable = false)
	public int getUserUid() {
		return this.userUid;
	}

	public void setUserUid(int userUid) {
		this.userUid = userUid;
	}

	@Column(name = "contacts_cid", unique = true, nullable = false)
	public int getContactsCid() {
		return this.contactsCid;
	}

	public void setContactsCid(int contactsCid) {
		this.contactsCid = contactsCid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserContactsId))
			return false;
		UserContactsId castOther = (UserContactsId) other;

		return (this.getUserUid() == castOther.getUserUid()) && (this.getContactsCid() == castOther.getContactsCid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUserUid();
		result = 37 * result + this.getContactsCid();
		return result;
	}

}
