package fr.pmu.racemanager.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class for Kafka topics.
 *
 * @author bertrand
 *
 */
@Configuration
public class KafkaTopicConfig {

	public static final String RACEMANAGER_TOPICNAME = "racemanager";

	public NewTopic createRaceManagerTopic() {
		return TopicBuilder.name(RACEMANAGER_TOPICNAME).build();
	}
}
