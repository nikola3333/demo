package com.isa.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Guest;
import com.isa.entity.Role;
import com.isa.repository.GuestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceImplTest {

	@Autowired
	private GuestRepository guestRepository;
	
	@Test
	public void testFindAll() {
		List<Guest> guests = (List<Guest>) guestRepository.findAll();
		assertEquals(6,guests.size());
	}

	@Test
	public void testFindByEmail() {
		Guest g = guestRepository.findByEmail("danilo@a.com");
		assertEquals("danilo@a.com",g.getEmail());
	}

	@Test
	public void testSave() {
		Guest g= new Guest();
		g.setFirstName("Janko");
		g.setLastName("Jankovic");
		g.setConfirmedRegistration(false);
		g.setEmail("jj@j.com");
		g.setPassword("123");
		g.setRole(new Role(1,"GUEST"));
		Guest result = guestRepository.save(g);
		assertEquals("jj@j.com",result.getEmail());
	}

	@Test
	public void testDelete() {
		guestRepository.delete(4L);
		Guest g = guestRepository.findOne(4L);
		assertEquals(null,g);
	}

	@Test
	public void testFindOne() {
		Guest g = guestRepository.findOne(1L);
		assertEquals("danilo@a.com",g.getEmail());
	}

	@Test
	public void testConfirmRegistration() {
		Guest guest = guestRepository.findOne(1L);
		guest.setConfirmedRegistration(true);
		Guest result = guestRepository.save(guest);
		assertEquals(true,result.isConfirmedRegistration());
	}

	@Test
	public void testUpdate() {
		Guest g = guestRepository.findOne(2L);
		g.setPassword("12345");
		Guest result = guestRepository.save(g);
		assertEquals("12345", result.getPassword());
	}

	@Test
	public void testFindNoneFriendsLong() {
		ArrayList<Guest> noneFriends = new ArrayList<Guest>(guestRepository.findNoneFriends(1L));
		Guest loggedUser = null;
		for(Guest g : noneFriends){
			if(g.getId().equals(1L)){
				loggedUser = g;
			}
		}
		noneFriends.remove(loggedUser);
		assertEquals(4,noneFriends.size());
	}

	@Test
	public void testFindNoneFriendsLongString() {
		ArrayList<Guest> noneFriends = new ArrayList<Guest>(guestRepository.findNoneFriends("branko", "dragas", 1L));

		assertEquals("abc@d.com",noneFriends.get(0).getEmail());
	}

}
