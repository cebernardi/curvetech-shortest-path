package curvetech;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import curvetech.path.GitHubPath;
import curvetech.source.IGitHubSource;

public class GitHubPathTest {
	
	private GitHubPath classUnderTest;
	private IGitHubSource source;

	@Before
	public void setUp() throws Exception {
		source = mock(IGitHubSource.class);
		classUnderTest = new GitHubPath(source);
	}

	@After
	public void tearDown() throws Exception {
		source = null;
		classUnderTest = null;
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorNullSourceThrows() {
		classUnderTest = new GitHubPath(null);
	}
	
	@Test
	public void testShortestPathUser1NullReturns0() {
		int shortest = classUnderTest.getShortestPath(null, "user1");
		Assert.assertEquals(0, shortest);
	}
	
	@Test
	public void testShortestPathUser2NullReturns0() {
		int shortest = classUnderTest.getShortestPath("user1", null);
		Assert.assertEquals(0, shortest);
	}
	
	@Test
	public void testShortestPathUser1EmptyReturns0() {
		int shortest = classUnderTest.getShortestPath("", "user1");
		Assert.assertEquals(0, shortest);
	}
	
	@Test
	public void testShortestPathUser2EmptyReturns0() {
		int shortest = classUnderTest.getShortestPath("user1", "");
		Assert.assertEquals(0, shortest);
	}

}
