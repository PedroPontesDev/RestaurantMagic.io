package com.restarauntHelper.io.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

import com.restarauntHelper.io.model.entities.enums.RoleUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_permissao")
public class Permission implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(mappedBy = "permissions")
	private List<User> users = new ArrayList<>();

	private RoleUser roleUser;

	@Override
	public String getAuthority() {
		return roleUser.name();
	}

	public Permission() {
	}

	public Permission(Long id, List<User> users, RoleUser roleUser) {
		this.id = id;
		this.users = users;
		this.roleUser = roleUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public RoleUser getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(RoleUser roleUser) {
		this.roleUser = roleUser;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", users=" + users + ", roleUser=" + roleUser + "]";
	}
	
	

}
