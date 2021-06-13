package ii.cipriantarlev.marketmanagementapi.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ii.cipriantarlev.marketmanagementapi.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER,
				cascade= {CascadeType.DETACH, CascadeType.MERGE, 
						CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "users-roles",
				joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	public User(String username, String password, String email, List<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}
}
