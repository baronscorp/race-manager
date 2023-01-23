package fr.pmu.racemanager.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.pmu.racemanager.kafka.RaceKafkaProducer;
import fr.pmu.racemanager.model.Race;

/**
 * Rest controller to manage race creation.
 *
 * @author bertrand
 *
 */
@RestController
@RequestMapping("/api/v1/race-manager")
public class RaceManagerController {

	/**
	 * Object Mapper to map JSON string from http request to a Race object.
	 */
	private static final ObjectMapper JSON_OBJECT_MAPPER = new ObjectMapper();
	/**
	 * Producer used to send message on kafka bus when a race is created.
	 */
	private RaceKafkaProducer kafkaProducer;

	@Autowired
	private SessionFactory sessionFactory;

	public RaceManagerController(RaceKafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	/**
	 * @return
	 */
	@PostMapping("/createRace")
	public ResponseEntity<String> publish(@RequestBody String raceData) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Race race = JSON_OBJECT_MAPPER.readValue(raceData, Race.class);
			session.persist(race);
			// kafkaProducer.sendMessage(race);
			session.getTransaction().commit();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("An error occured : race was not created.");
		}
		return ResponseEntity.ok("Race info inserted in DB and sent to brokers");
	}
}
