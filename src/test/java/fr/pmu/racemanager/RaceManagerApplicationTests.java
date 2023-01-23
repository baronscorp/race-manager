package fr.pmu.racemanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class RaceManagerApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mvc;

	@BeforeEach
	public void initMvc() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void createRaceWithCorrectInfo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/race-manager/createRace")
				.content("{\"name\" : \"Course A\", \"number\" : 1, \"date\" : \"15-02-2023\",\n"
						+ "    \"runners\" : [\n" + "        {\"name\" : \"Bertrand\", \"number\" : 1},\n"
						+ "        {\"name\" : \"Cedric\", \"number\" : 2},"
						+ "        {\"name\" : \"Pierre\", \"number\" : 3},"
						+ "        {\"name\" : \"Olivier\", \"number\" : 4}]}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void createRaceWithLessThan3Runners() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/race-manager/createRace")
				.content("{\"name\" : \"Course A\", \"number\" : 1, \"date\" : \"15-02-2023\",\n"
						+ "    \"runners\" : [\n" + "        {\"name\" : \"Bertrand\", \"number\" : 1},\n"
						+ "        {\"name\" : \"Tata\", \"number\" : 4}]}")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
}
