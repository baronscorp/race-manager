package fr.pmu.racemanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represent a runner who can participate to a race.
 *
 * @author bertrand
 *
 */
@Entity
public class Runner {

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Runner 's name.
	 */
	@JsonProperty("name")

	@NotBlank(message = "A runner's name cannot be null or empty string")
	private String name;

	/**
	 * Runner's number.
	 */
	@JsonProperty("number")

	@NotNull(message = "The runner's number cannot be null")
	private Integer number;
}