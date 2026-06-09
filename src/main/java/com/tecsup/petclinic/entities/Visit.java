package com.tecsup.petclinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Entity(name = "visits")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "visit_date")
	private LocalDate visitDate;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pet_id")
	@JsonIgnoreProperties({
			"visits",
			"hibernateLazyInitializer",
			"handler"
	})
	private Pet pet;
}