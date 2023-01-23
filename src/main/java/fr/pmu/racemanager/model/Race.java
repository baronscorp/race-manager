package fr.pmu.racemanager.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a race which occurs on a specific date.
 *
 * @author bertrand
 *
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "DATE", "NUMBER" }))
public class Race {

	@Id
	@GeneratedValue
	private Long id;
	/**
	 * Date when the race will occur.
	 */
	@JsonProperty("date")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "The date of the race cannot be null")
	private Date date;

	/**
	 * Name of the race.
	 */
	@JsonProperty("name")
	@NotBlank(message = "The race name cannot be null or empty string")
	private String name;

	/**
	 * Unique number of the race for the specified date.
	 */
	@JsonProperty("number")
	@NotNull(message = "the race number cannot be null")
	private Integer number;

	/**
	 * List of runners for this race.<br />
	 * A race has at least 3 runners.
	 */
	@JsonProperty("runners")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "RACE_ID", referencedColumnName = "ID")
	@Size(min = 3, message = "A race must have at least 3 runners")
	private List<Runner> runners;
}