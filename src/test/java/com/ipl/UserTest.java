package com.ipl;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipl.model.Film;
import com.ipl.model.User;

public class UserTest {
	
	private JacksonTester<User> json;
	
	@Before
	public void setup() {
		ObjectMapper mapper = new ObjectMapper();
		JacksonTester.initFields(this, mapper);
	}
	
	@Test
	public void serializesToJSON() throws Exception {
		final User user = new User("User McUser");
	
		assertThat(this.json.write(user)).isEqualToJson("/user.json");
		
		assertThat(this.json.write(user)).hasJsonPathStringValue("@.username");
		
		assertThat(this.json.write(user)).extractingJsonPathStringValue("@.username").isEqualTo("User McUser");
	}
	
	@Test
	public void deserializesFromJson() throws IOException{
		String content = "{\"username\":\"User123\"}";
		
		assertThat(this.json.parseObject(content).username).isEqualTo("User123");
	}
	
	/*@Test
	public void serializesToJSONWithFilms() throws Exception {
		final User user = new User("User111", 
				Stream.of(
						new Film("Avengers Assemble", "Joss Whedon"), 
						new Film("Avengers: Age of Ultron", "Joss Whedon")
						).collect(Collectors.toList()));
	
		assertThat(this.json.write(user)).isEqualToJson("/userwithfilm.json");
		
		assertThat(this.json.write(user)).hasJsonPathStringValue("@.username");
		assertThat(this.json.write(user)).hasJsonPathArrayValue("@.films");
		
		assertThat(this.json.write(user)).extractingJsonPathStringValue("@.username").isEqualTo("User111");
		assertThat(this.json.write(user)).extractingJsonPathArrayValue("@.films").isNotEmpty();
		
		// TODO: test content of array
	}*/

}
