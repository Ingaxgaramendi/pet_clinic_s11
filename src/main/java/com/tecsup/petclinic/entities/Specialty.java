package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 *
 * @author jgomezm
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "specialties")
public class Specialty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "specialties", fetch = FetchType.LAZY)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Vet> vets;
}