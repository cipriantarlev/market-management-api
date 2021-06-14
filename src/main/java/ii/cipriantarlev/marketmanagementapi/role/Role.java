package ii.cipriantarlev.marketmanagementapi.role;

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

import ii.cipriantarlev.marketmanagementapi.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "role")
	private String roleName;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
				cascade= {CascadeType.DETACH, CascadeType.MERGE, 
						CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "users-roles",
				joinColumns=@JoinColumn(name="role_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users;

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}
}
