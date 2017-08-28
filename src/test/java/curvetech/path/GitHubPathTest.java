package curvetech.path;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import curvetech.common.GitHubUser;
import curvetech.path.GitHubPath;
import curvetech.source.IGitHubSource;

public class GitHubPathTest {
	
	private GitHubPath classUnderTest;
	private IGitHubSource source;
	private GitHubUser user1;
	private GitHubUser user2;

	@Before
	public void setUp() throws Exception {
		source = mock(IGitHubSource.class);
		classUnderTest = new GitHubPath(source);
		user1 = new GitHubUser("user1");
		user2 = new GitHubUser("user2");
	}

	@After
	public void tearDown() throws Exception {
		source = null;
		classUnderTest = null;
		user1 = null;
		user2 = null;
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
	
	@Test
	public void testShortestPathUser1NotExistingReturns0() {
		when(source.getUser("user1")).thenReturn(null);
		int shortest = classUnderTest.getShortestPath("user1", "user2");
		Assert.assertEquals(0, shortest);
	}
	
	@Test
	public void testShortestPathNotExistingEmptyReturns0() {
		when(source.getUser("user2")).thenReturn(null);
		int shortest = classUnderTest.getShortestPath("user1", "user2");
		Assert.assertEquals(0, shortest);
	}

	@Test
	public void testShortestPathUser1AndUser2AreEqualsReturns1() {
		when(source.getUser("user1")).thenReturn(user1);
		when(source.getUser("user2")).thenReturn(user2);
		int shortest = classUnderTest.getShortestPath("user1", "user1");
		Assert.assertEquals(1, shortest);
	}
}
