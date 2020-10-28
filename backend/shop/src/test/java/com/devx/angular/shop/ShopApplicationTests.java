package com.devx.angular.shop;

import com.devx.angular.shop.dto.BeerDto;
import com.devx.angular.shop.dto.UserDto;
import com.devx.angular.shop.services.BeerService;
import com.devx.angular.shop.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShopApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserService userService;
	@Autowired
	private BeerService beerService;

	@BeforeEach
	void beforeEach(){
		userService.clear();
	}

	@Test
	void shouldGetPublicImage() throws Exception {
		//when
		var result = mockMvc.perform(
				get("/images/Idiota_167x735.png")
						.contentType(MediaType.APPLICATION_JSON));
		//then
		result.andExpect(status().isOk());
	}

	@Test
	void shouldCreateNewUser() throws Exception {
		var user = new UserDto();
		user.setUserName("newUser");
		user.setPassword("pass");
		//when
		var result = mockMvc.perform(
				post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));
		//then
		result.andExpect(status().isCreated());

		assertThat(userService.userExist(user)).isTrue();
	}

	@Test
	void shouldNotCreateNewUserWithoutPassword() throws Exception {
		var user = new UserDto();
		user.setUserName("newUser");
		user.setPassword("");
		//when
		var result = mockMvc.perform(
				post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));
		//then
		result.andExpect(status().isBadRequest());

		Assertions.assertThrows(RuntimeException.class, () -> userService.userExist(user));
	}

	@Test
	void shouldNotCreateNewUserWithoutUserName() throws Exception {
		var user = new UserDto();
		user.setUserName("");
		user.setPassword("pass");
		//when
		var result = mockMvc.perform(
				post("/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));
		//then
		result.andExpect(status().isBadRequest());

		Assertions.assertThrows(RuntimeException.class, () -> userService.userExist(user));
	}

	@Test
	void shouldNotCreateNewUserWithoutUserData() throws Exception {
		var user = new UserDto();
		user.setUserName("");
		user.setPassword("pass");
		//when
		var result = mockMvc.perform(
				post("/user")
						.contentType(MediaType.APPLICATION_JSON));
		//then
		result.andExpect(status().isBadRequest());

		Assertions.assertThrows(RuntimeException.class, () -> userService.userExist(user));
	}

	@Test
	void shouldGetTokenForExistingUser() throws Exception {
		var user = new UserDto();
		user.setUserName("newUser");
		user.setPassword("pass");
		userService.createNewUser(user);

		//when
		var result = mockMvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));

		result.andExpect(status().isOk()).andExpect(header().exists("X-Auth-Token"));
	}

	@Test
	void shouldNotGetTokenForNotExistingUser() throws Exception {
		var user = new UserDto();
		user.setUserName("newUser");
		user.setPassword("pass");
//		userService.createNewUser(user);

		//when
		var result = mockMvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));

		result.andExpect(status().isUnauthorized());
	}

	@Test
	void shouldNotGetTokenForExistingUserWithBadPassword() throws Exception {
		var user = new UserDto();
		user.setUserName("newUser");
		user.setPassword("pass");
		userService.createNewUser(user);
		user.setPassword("badPass");

		//when
		var result = mockMvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));

		result.andExpect(status().isUnauthorized());
	}

	@Test
	void shouldNotLoadBeersForNotLoggedUser() throws Exception {
		//when
		var result = mockMvc.perform(
				get("/beer")
						.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isForbidden());
	}

	@Test
	void shouldLoadBeers() throws Exception {
		var user = new UserDto();
		user.setUserName("newUser");
		user.setPassword("pass");
		userService.createNewUser(user);

		//when
		var result = mockMvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));

		var r = result.andExpect(status().isOk()).andExpect(header().exists("X-Auth-Token")).andReturn();
		var header = r.getResponse().getHeader("X-Auth-Token");

		result = mockMvc.perform(
				get("/beer")
						.contentType(MediaType.APPLICATION_JSON)
						.header("X-Auth-Token", header));
		r = result.andExpect(status().isOk()).andReturn();
		assertThat(r.getResponse().getContentAsString()).contains(beerService.getAllBears().stream().map(BeerDto::getName).collect(Collectors.toList()));
	}

	@Test
	void shouldNotLoadDetailBeerIfNotLoggedIn() throws Exception {
		var result = mockMvc.perform(
				get("/beer/Idiota")
						.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isForbidden());
	}

	@Test
	void shouldLoadDetailBeerIfLoggedIn() throws Exception {
		var user = new UserDto();
		user.setUserName("newUser");
		user.setPassword("pass");
		userService.createNewUser(user);

		//when
		var result = mockMvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));

		var r = result.andExpect(status().isOk()).andExpect(header().exists("X-Auth-Token")).andReturn();
		var header = r.getResponse().getHeader("X-Auth-Token");


		result = mockMvc.perform(
				get("/beer/Pan IPani")
						.contentType(MediaType.APPLICATION_JSON)
						.header("X-Auth-Token", header));

		r = result.andExpect(status().isOk()).andReturn();
		assertThat(r.getResponse().getContentAsString()).contains("Wheat IPA");
	}

	@Test
	void shouldNotLoadDetailBeerItNotExist() throws Exception {
		var user = new UserDto();
		user.setUserName("newUser");
		user.setPassword("pass");
		userService.createNewUser(user);

		//when
		var result = mockMvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(user)));

		var r = result.andExpect(status().isOk()).andExpect(header().exists("X-Auth-Token")).andReturn();
		var header = r.getResponse().getHeader("X-Auth-Token");


		result = mockMvc.perform(
				get("/beer/NoSuchBeer")
						.contentType(MediaType.APPLICATION_JSON)
						.header("X-Auth-Token", header));

		result.andExpect(status().isNotFound());
	}


	private static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
