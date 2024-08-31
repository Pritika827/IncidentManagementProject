
package com.transline.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users_table", indexes = { @Index(name = "user_name", columnList = "cmpCd, userName", unique = true) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
//@IdClass(value = UserId.class)
public class User implements UserDetails {

	@Id
	private String userId;	
	
	private String userName;// userName is email id

	private String password;

	private String status;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),

			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") // Changed column name
	)
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("Authorities: " + getRoles());
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
				.collect(Collectors.toList());
	}
	
//	 @Enumerated(EnumType.STRING)
//	  private com.transline.enums.Role role;
//
//	  @Override
//	  public Collection<? extends GrantedAuthority> getAuthorities() {
//	    return role.getAuthorities();
//	  }

//	@ManyToOne
//	@JoinColumn(name = "cmp_cd", nullable = false)
//	private CompanyMst companyMst;


//	private String cmpCd;
	
	@ManyToOne
	@JoinColumn(name = "cmp_cd")    
    private CompanyMst companyMst;
	
//	private String offCd;
	
	@ManyToOne
    @JoinColumn(name = "off_cd")
    private Office office;

	@Override
	public String getUsername() {
		return this.userName;
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

}
