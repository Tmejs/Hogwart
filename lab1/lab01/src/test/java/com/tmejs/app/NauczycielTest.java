package com.tmejs.app;

import static org.junit.Assert.*;
import org.junit.Test;



/**
 * Unit test for simple App.
 */
public class NauczycielTest {


	@Test
	public void testCreatePerson(){
		Nauczyciel nauczyciel= new Nauczyciel();
		assertNotNull(nauczyciel);
	}

}
