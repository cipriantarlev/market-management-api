/*******************************************************************************
 * © 2021 II Ciprian Tarlev. All Rights Reserved.
 *******************************************************************************/
package ii.cipriantarlev.marketmanagementapi.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * A parent entity class that holds the fields
 * available for all subclasses.
 */
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuperEntity {

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
}
