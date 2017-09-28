package com.neosoft.sportsclubmanagement.model;

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

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "USER")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer uid;
	private UserRoles userRoles;
	private String userName;
	private String password;
	private Date createdOn;
	private Date updatedOn;
	private Date lastLogin;
	private Boolean status;
	private Set<UserDetails> userDetailses = new HashSet<UserDetails>(0);
	private Set<UserContacts> userContactses = new HashSet<UserContacts>(0);
	private Set<UserContact> userContacts = new HashSet<UserContact>(0);

	public User() {
	}

	public User(UserRoles userRoles, String userName, String password, Date createdOn, Date updatedOn, Date lastLogin,
			Boolean status, Set<UserDetails> userDetailses, Set<UserContacts> userContactses,
			 Set<UserContact> userContacts) {
		this.userRoles = userRoles;
		this.userName = userName;
		this.password = password;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.lastLogin = lastLogin;
		this.status = status;
		this.userDetailses = userDetailses;
		this.userContactses = userContactses;
		this.userContacts = userContacts;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "UID", unique = true, nullable = false)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLES_RID")
	public UserRoles getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	@Column(name = "USER_NAME", length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", length = 19)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON", length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN", length = 19)
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "STATUS")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserDetails> getUserDetailses() {
		return this.userDetailses;
	}

	public void setUserDetailses(Set<UserDetails> userDetailses) {
		this.userDetailses = userDetailses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserContacts> getUserContactses() {
		return userContactses;
	}

	public void setUserContactses(Set<UserContacts> userContactses) {
		this.userContactses = userContactses;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserContact> getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(Set<UserContact> userContacts) {
		this.userContacts = userContacts;
	}
}
