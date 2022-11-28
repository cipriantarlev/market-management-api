/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.history;

import ii.cipriantarlev.marketmanagementapi.product.Product;
import ii.cipriantarlev.marketmanagementapi.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class to hold history information of any modification for
 * {@link Product} attributes but not for {@link Product} itself.
 */
@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public class History {

    /**
     * History's id in database.
     */
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    /**
     * {@link User} username that made the modification.
     */
    @Column(name = "username")
    private String username;

    /**
     * {@link HistoryAction} made by user.
     */
    @Column(name = "action")
    private String action;

    /**
     * Date and time when the history row has been added
     * to the database.
     */
    @Column(name = "created")
    private LocalDateTime created;
}
