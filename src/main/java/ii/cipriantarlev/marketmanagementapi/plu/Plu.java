package ii.cipriantarlev.marketmanagementapi.plu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plu")
@NoArgsConstructor
@Getter
@Setter
public class Plu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "value")
	private Integer value;

	public Plu(Integer value) {
		this.value = value;
	}
}
