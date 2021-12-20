package ii.cipriantarlev.marketmanagementapi.history;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public class History {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "action")
    private String action;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "new_entity")
    private String newEntity;

    @Column(name = "old_entity")
    private String oldEntity;

    @Column(name = "created")
    private LocalDateTime created;
}
