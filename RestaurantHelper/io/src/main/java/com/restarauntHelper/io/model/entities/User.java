package com.restarauntHelper.io.model.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario")
@Entity
@Table(name = "tb_usuario")
public abstract class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false, unique = true, length = 50)
	private String username;

	@Column(name = "nome", nullable = false, length = 50)
	private String name;

	@Column(name = "sobrenome", length = 50)
	private String lastName;

	private String password;
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usario_permissoes", joinColumns = @JoinColumn(name = "usuario_id"), 
	inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private List<Permission> permissions = new ArrayList<>();

	public User() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions.stream()
							   .map(permissao -> new SimpleGrantedAuthority(permissao.getRoleUser().name()))
							   .toList();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// Getters e setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + ", permissions=" + permissions + "]";
	}
}
