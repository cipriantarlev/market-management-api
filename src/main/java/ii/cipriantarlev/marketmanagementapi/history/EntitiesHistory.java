package ii.cipriantarlev.marketmanagementapi.history;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "entities_history")
@NoArgsConstructor
@Getter
@Setter
public class EntitiesHistory extends History {
}
