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
public class RentACarControllerTest {

	private static final String PREFIX = "/rentacars";

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
	public void getAllRentACars() throws Exception {
		
		mockMvc.perform(get(PREFIX + "/all")).andExpect(status().isOk());
	}
	
	@Test
	public void getById() throws Exception {
		
		mockMvc.perform(get(PREFIX + "/18")).andExpect(status().isOk());	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void addRentACar() throws Exception {
		
		String json = "{\r\n" + 
				"    \"id\": null,\r\n" + 
				"    \"name\": \"Europcar\",\r\n" + 
				"    \"address\": \"45.24855,19.8371\",\r\n" + 
				"    \"description\": \"Ovo je evropska rentakar kompanija\",\r\n" + 
				"    \"image\": \"europcar\"\r\n" + 
				"}";
		
		
		mockMvc.perform(post(PREFIX + "/new").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				.contentType(contentType).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Europcar")));	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateRentACar() throws Exception {
			
		String json = "{\r\n" + 
				"    \"id\": 18,\r\n" + 
				"    \"name\": \"Sixt\",\r\n" + 
				"    \"address\": \"45.24855,19.8371\",\r\n" + 
				"    \"description\": \"Ovo je evropska rentakar kompanija\",\r\n" + 
				"    \"image\": \"europcar\"\r\n" + 
				"}";
		
		
		mockMvc.perform(put(PREFIX + "/18/update").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				.contentType(contentType).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Sixt")));	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void deleteBranchOffice() throws Exception {
		
		mockMvc.perform(delete(PREFIX + "/18/delete").header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhaXJ0aWNrZXRzIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE1Njc5ODc5MjAsImV4cCI6MTU2Nzk5NjkyMH0.NDa-EWvNX7IEU7Kmq26IetrG6SCpqNXb7xWQToOpK6WHoCSEdezo0WMFMBH0qHVT02LbOroSPI_oXaxY18sg8w")
				).andExpect(status().isOk());
	}
}
