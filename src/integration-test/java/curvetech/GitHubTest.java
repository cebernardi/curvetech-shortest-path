package curvetech;

import static org.mockito.Mockito.*;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import curvetech.source.IGitHubSource;

public class GitHubTest {

	private GitHubPath classUnderTest;
	private IGitHubSource source;
	private GitHubUser user1;
	private GitHubUser user2;
	private GitHubUser user3;
	private GitHubUser user4;
	private GitHubUser user5;
	private GitHubRepo repo1;
	private GitHubRepo repo2;
	private GitHubRepo repo3;
	private GitHubRepo repo4;
	
	@Before
	public void setUp() throws Exception {
		
		source = mock(IGitHubSource.class);
		
		repo1 = new GitHubRepo("repo1",  "user1", "user2");
		repo2 = new GitHubRepo("repo2",  "user2", "user3");
		repo3 = new GitHubRepo("repo3",  "user3");
		repo4 = new GitHubRepo("repo4",  "user3", "user4");
		
		user1 = new GitHubUser("user1", "repo1");
		user2 = new GitHubUser("user2", "repo2", "repo1");
		user3 = new GitHubUser("user3", "repo3", "repo4", "repo2");
		user4 = new GitHubUser("user4", "repo4");
		user5 = new GitHubUser("user5");
		
		when(source.getRepo("repo1")).thenReturn(repo1);
		when(source.getRepo("repo2")).thenReturn(repo2);
		when(source.getRepo("repo3")).thenReturn(repo3);
		when(source.getRepo("repo4")).thenReturn(repo4);
		
		when(source.getUser("user1")).thenReturn(user1);
		when(source.getUser("user2")).thenReturn(user2);
		when(source.getUser("user3")).thenReturn(user3);
		when(source.getUser("user4")).thenReturn(user4);
		when(source.getUser("user5")).thenReturn(user5);
		
		classUnderTest = new GitHubPath(source);
	}

	@After
	public void tearDown() throws Exception {
		classUnderTest = null;
		source = null;
		user1 = null;
		user2 = null;
		user3 = null;
		user4 = null;
		repo1 = null;
		repo2 = null;
		repo3 = null;
		repo4 = null;
	}

	@Test
	public void testShortestPathUser1NotInSourceReturns0() {
		int shortest = classUnderTest.getShortestPath("notexisting", "user1");
		Assert.assertEquals(0, shortest);
	}
	
	
	
	@Test
	public void testShortestPathUser2NotInSourceReturns0() {
		int shortest = classUnderTest.getShortestPath("user1", "notexisting");
		Assert.assertEquals(0, shortest);
	}
	
	@Test
	public void testShortestPathUser1AndUser2NotConnectedReturns0() {
		int shortest = classUnderTest.getShortestPath("user1", "user5");
		Assert.assertEquals(0, shortest);
	}
	
	@Test
	public void testShortestPathUser1AndUser2ConnectedBy3StepsReturns3() {
		int shortest = classUnderTest.getShortestPath("user1", "user4");
		Assert.assertEquals(3, shortest);
	}
	
	@Test
	public void testShortestPathUser1AndUser2ConnectedBy1StepsReturns1() {
		int shortest = classUnderTest.getShortestPath("user1", "user2");
		Assert.assertEquals(1, shortest);
	}
	
	@Test
	public void testShortestPathUser1AndUser2ConnectedBy2StepsReturns2() {
		int shortest = classUnderTest.getShortestPath("user1", "user3");
		Assert.assertEquals(2, shortest);
	}
	
	@Test
	public void testShortestPathUser1AndUser2AreEqualsReturns1() {
		int shortest = classUnderTest.getShortestPath("user1", "user1");
		Assert.assertEquals(1, shortest);
	}

}
