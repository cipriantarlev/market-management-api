package ii.cipriantarlev.marketmanagementapi.vat;

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
@Table(name = "vat")
@NoArgsConstructor
@Getter
@Setter
public class Vat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "value")
	private Integer value;

	@Column(name = "name")
	private String name;

	public Vat(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
}
