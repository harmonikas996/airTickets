package airtickets.controllers;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleControllerTest {

	private static final String PREFIX = "/vehicles";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void getAllVehicles() throws Exception {
		
		mockMvc.perform(get(PREFIX + "/all")).andExpect(status().isOk());
	}
	
	@Test
	public void getById() throws Exception {
		
		mockMvc.perform(get(PREFIX + "/21")).andExpect(status().isOk());	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void addVehicle() throws Exception {
		
		String json = "{\r\n" + 
				"    \"id\": null,\r\n" + 
				"    \"name\": \"Series 5\",\r\n" + 
				"    \"brand\": \"BMW\",\r\n" + 
				"    \"model\": \"540D\",\r\n" + 
				"    \"yearOfProduction\": 2017,\r\n" + 
				"    \"numberOfSeats\": 5,\r\n" + 
				"    \"type\": \"Sedan\",\r\n" + 
				"    \"pricePerDay\": 25.0,\r\n" + 
				"    \"rentACarId\": 18,\r\n" + 
				"    \"image\": \"bmw-5er-4d-schwarz-2017.png\"\r\n" + 
				"}";
		
		
		mockMvc.perform(post(PREFIX + "/new").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				.contentType(contentType).content(json)).andExpect(status().isOk());	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void makeReservation() throws Exception {
		
		String json = "{\r\n" + 
				"    \"id\": 21,\r\n" + 
				"    \"name\": \"Series 5\",\r\n" + 
				"    \"brand\": \"BMW\",\r\n" + 
				"    \"model\": \"540D\",\r\n" + 
				"    \"yearOfProduction\": 2017,\r\n" + 
				"    \"numberOfSeats\": 5,\r\n" + 
				"    \"type\": \"Sedan\",\r\n" + 
				"    \"pricePerDay\": 25.0,\r\n" + 
				"    \"rentACarId\": 18,\r\n" + 
				"    \"image\": \"bmw-5er-4d-schwarz-2017.png\"\r\n" + 
				"}";
		
		
		mockMvc.perform(put(PREFIX + "/makeReservation/26/2019-09-18T00:00:00.000/2019-09-25T00:00:00.000").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				.contentType(contentType).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").isNumber());	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateVehicle() throws Exception {
			
		String json = "{\r\n" + 
				"    \"id\": 21,\r\n" + 
				"    \"name\": \"Series 5\",\r\n" + 
				"    \"brand\": \"Jaguar\",\r\n" + 
				"    \"model\": \"540D\",\r\n" + 
				"    \"yearOfProduction\": 2017,\r\n" + 
				"    \"numberOfSeats\": 5,\r\n" + 
				"    \"type\": \"Sedan\",\r\n" + 
				"    \"pricePerDay\": 25.0,\r\n" + 
				"    \"rentACarId\": 18,\r\n" + 
				"    \"image\": \"bmw-5er-4d-schwarz-2017.png\"\r\n" + 
				"}";
		
		
		mockMvc.perform(put(PREFIX + "/18/update").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				.contentType(contentType).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.brand", is("Jaguar")));	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void deleteVehicle() throws Exception {
		
		mockMvc.perform(delete(PREFIX + "/21/delete").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				).andExpect(status().isOk());
	}
}