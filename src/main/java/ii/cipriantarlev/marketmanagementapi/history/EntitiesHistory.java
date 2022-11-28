/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

import com.vladmihalcea.hibernate.type.json.JsonType;
import ii.cipriantarlev.marketmanagementapi.core.SuperEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that holds additional to {@link History} attributes.
 */
@Entity
@Table(name = "entities_history")
@NoArgsConstructor
@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonType.class)
public class EntitiesHistory extends History {

    /**
     * Entity's name
     */
    @Column(name = "entity_name")
    private String entityName;

    /**
     * The updated entity in json format, saved as jsonb in database.
     */
    @Type(type = "json")
    @Column(name = "new_entity", columnDefinition = "jsonb")
    private SuperEntity newEntity;

    /**
     * The old entity in json format, saved as jsonb in database.
     */
    @Type(type = "json")
    @Column(name = "old_entity", columnDefinition = "jsonb")
    private SuperEntity oldEntity;
}
