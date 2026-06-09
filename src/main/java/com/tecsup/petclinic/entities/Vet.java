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
@ToString(exclude = "specialties")
@EqualsAndHashCode(exclude = "specialties")
@Entity(name = "vets")
public class Vet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "vet_specialties",
			joinColumns = @JoinColumn(name = "vet_id"),
			inverseJoinColumns = @JoinColumn(name = "specialty_id")
	)
	private Set<Specialty> specialties;

}