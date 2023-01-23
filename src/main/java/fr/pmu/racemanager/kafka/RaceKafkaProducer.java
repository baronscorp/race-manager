package fr.pmu.racemanager.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import fr.pmu.racemanager.model.Race;

/**
 * Kafka Producer to send messages about new create races.
 *
 * @author bertrand
 *
 */
@Service
public class RaceKafkaProducer {

	private KafkaTemplate<String, Race> template;

	public RaceKafkaProducer(KafkaTemplate<String, Race> template) {
		this.template = template;
	}

	public void sendMessage(Race race) {
		Message<Race> message = MessageBuilder.withPayload(race)
				.setHeader(KafkaHeaders.TOPIC, KafkaTopicConfig.RACEMANAGER_TOPICNAME).build();
		template.send(message);
	}
}
