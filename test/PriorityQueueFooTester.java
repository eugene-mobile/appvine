import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

//Uses JUnit 4
public class PriorityQueueFooTester {

	private static final String INCORRECT_SIZE = "Incorrect queue size returned as result";

	@Test
	public void testNull() {
		try {
			Foo<Integer> foo = new PriorityQeueFoo<Integer>(3);
			foo.offer(null);											//Question 1: Should exception be thrown in case of null value?
			fail("Exception should've been thrown");
		} catch (IllegalArgumentException e) {
			assertNotNull("Exception should be non-null", e);
		}
	}
	
	@Test
	public void testAdd() {
		Foo<Integer> foo = new PriorityQeueFoo<Integer>(3);
		assertNotNull("Collection cannot be null", foo.getTopK());		//Question 2: should the implementation return empty array or null of no values offered yet?
		assertEquals(INCORRECT_SIZE, foo.getTopK().size(), 0);
		foo.offer(0);
		assertEquals(foo.getTopK().size(), 1);							//Question 3: should foo.getTopK() clear the queue?
		foo.offer(1);
		assertEquals(foo.getTopK().size(), 2);
		foo.offer(2);
		assertEquals(foo.getTopK().size(), 3);
		foo.offer(3);
		assertEquals(foo.getTopK().size(), 3);
		foo.offer(4);
		assertEquals(foo.getTopK().size(), 3);
	}
	
	@Test
	public void testImmutability() {
		Foo<String> foo = new PriorityQeueFoo<String>(3);
		foo.offer("0");
		foo.offer("1");
		foo.offer("2");
		assertTrue(foo.getTopK().contains("0"));
		assertTrue(foo.getTopK().contains("1"));
		assertTrue(foo.getTopK().contains("2"));

		foo.getTopK().remove("0");										//Question 4: Should altering return collection affect internal state of the queue? 
		assertTrue(foo.getTopK().contains("0"));
		assertTrue(foo.getTopK().contains("1"));
		assertTrue(foo.getTopK().contains("2"));

	}
	
	@Test
	public void testCorrectness() {
		Foo<String> foo = new PriorityQeueFoo<String>(3);
		foo.offer("0");
		assertTrue(foo.getTopK().contains("0"));
		assertFalse(foo.getTopK().contains("1"));
		assertFalse(foo.getTopK().contains("2"));
		
		foo.offer("1");
		assertTrue(foo.getTopK().contains("0"));
		assertTrue(foo.getTopK().contains("1"));
		assertFalse(foo.getTopK().contains("2"));

		foo.offer("2");
		assertTrue(foo.getTopK().contains("0"));
		assertTrue(foo.getTopK().contains("1"));
		assertTrue(foo.getTopK().contains("2"));
		
		foo.offer("0");
		assertTrue(foo.getTopK().contains("0"));
		assertTrue(foo.getTopK().contains("1"));
		assertTrue(foo.getTopK().contains("2"));
		
		foo.offer("5");
		assertFalse(foo.getTopK().contains("0"));
		assertTrue(foo.getTopK().contains("1"));
		assertTrue(foo.getTopK().contains("2"));
		assertTrue(foo.getTopK().contains("5"));

		String longString = "VERY LONG STRING VERY LONG STRING VERY LONG STRING VERY LONG STRING VERY LONG STRING VERY LONG STRING VERY LONG STRING VERY LONG STRING VERY LONG STRING VERY LONG STRING VERY LONG STRING";
		foo.offer(longString);
		assertFalse(foo.getTopK().contains("0"));
		assertFalse(foo.getTopK().contains("1"));
		assertTrue(foo.getTopK().contains("2"));
		assertTrue(foo.getTopK().contains("5"));
		assertTrue(foo.getTopK().contains(longString));
		
	}

}
