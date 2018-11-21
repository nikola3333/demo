package com.isa.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.entity.Guest;
import com.isa.entity.Role;
import com.isa.service.GuestService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestControllerTest {

	@Autowired
	private GuestController guestController;
	@Autowired
	private GuestService guestService;
    private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(guestController).build();
	}
    
	@Test
	public void testFindAll() throws Exception {
		MvcResult mock = this.mockMvc.perform(get("/guests")).andExpect(status().isOk()).andReturn();
		String result = mock.getResponse().getContentAsString();
		assertNotNull(result);
	}
	

	@Test
	public void testFindNoneFriends() throws Exception {
		mockMvc.perform(get("/guests/none").sessionAttr("user", guestService.findOne(1L))).andExpect(status().isOk())
		.andExpect(jsonPath("$",hasSize(4))).andReturn();
		/*String result = mock.getResponse().getContentAsString();
		assertNotNull(result);*/		
	}
	
	@Test
	public void testUpdate() throws Exception {
		Guest guest = guestService.findOne(1L);
		guest.setPassword("456");
		mockMvc.perform(post("/guests")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(asJsonString(guest)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.password", is("456"))).andReturn();
	}

	@Test
	public void testSave() throws Exception {
		Guest g = new Guest();
		g.setConfirmedRegistration(false);
		g.setEmail("gg@g.com");
		g.setFirstName("milos");
		g.setLastName("milosevic");
		g.setPassword("123");
		g.setRole(new Role(1, "GUEST"));
		mockMvc.perform(put("/guests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(g)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email", is("gg@g.com"))).andReturn();
	}


	@Test
	public void testConfirmRegistration() throws Exception {
	      this.mockMvc.perform(get("/guests/confirmation/1234565"))
          .andExpect(status().isOk());
	}

	@Test
	public void testFindByEmail() throws Exception {
		mockMvc.perform(get("/guests/user").sessionAttr("user", guestService.findOne(1L))).andExpect(status().isOk());

	}


	@Test
	public void testSearchNoneFriends() throws Exception {
	      this.mockMvc.perform(get("/guests/none/branko").sessionAttr("user", guestService.findOne(1L)))
          .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}
