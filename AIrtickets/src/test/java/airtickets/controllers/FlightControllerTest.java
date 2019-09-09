package airtickets.controllers;

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
public class FlightControllerTest {
	private static final String PREFIX = "/flights";

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
	public void getAllFlights() throws Exception {
		
		mockMvc.perform(get(PREFIX + "/all")).andExpect(status().isOk());
	}
	
	@Test
	public void getById() throws Exception {
		
		mockMvc.perform(get(PREFIX + "/3")).andExpect(status().isOk());	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void addFlight() throws Exception {
		
		String json = "{\r\n" + 
				"    \"id\": null,\r\n" + 
				"    \"timeBegin\": \"2019-09-10T05:14:10\",\r\n" + 
				"    \"timeEnd\": \"2019-09-08T06:51:26\",\r\n" + 
				"    \"distance\": 600.0,\r\n" + 
				"    \"price\": 120.0,\r\n" + 
				"    \"airplaneType\": \"AirbusA320\",\r\n" + 
				"    \"loweredPrice\": 100.0,\r\n" + 
				"    \"placeFromId\": 5005,\r\n" + 
				"    \"placeToId\": 5006,\r\n" + 
				"    \"aircompanyId\": 1\r\n" + 
				"}";
		
		
		mockMvc.perform(post(PREFIX + "/new").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				.contentType(contentType).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.aircompanyId", is(1)));	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateFlight() throws Exception {
			
		String json = "{\r\n" + 
				"    \"id\": 3,\r\n" + 
				"    \"timeBegin\": \"2019-09-10T05:14:10\",\r\n" + 
				"    \"timeEnd\": \"2019-09-08T06:51:26\",\r\n" + 
				"    \"distance\": 1000.0,\r\n" + 
				"    \"price\": 120.0,\r\n" + 
				"    \"airplaneType\": \"AirbusA320\",\r\n" + 
				"    \"loweredPrice\": 100.0,\r\n" + 
				"    \"placeFromId\": 5005,\r\n" + 
				"    \"placeToId\": 5006,\r\n" + 
				"    \"aircompanyId\": 1\r\n" + 
				"}";
		
		
		mockMvc.perform(put(PREFIX + "/3/update").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				.contentType(contentType).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.distance", is(1000.0)));	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void deleteFlight() throws Exception {
		
		mockMvc.perform(delete(PREFIX + "/3/delete").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				).andExpect(status().isOk());
	}

}
