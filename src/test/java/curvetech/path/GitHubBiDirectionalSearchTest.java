package curvetech.path;

import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import curvetech.common.GitHubUser;
import curvetech.source.IGitHubSource;


public class GitHubBiDirectionalSearchTest {

	private IGitHubSource source;
	private GitHubBiDirectionalSearchTestable classUnderTest;
	private GitHubUser user1;
	private GitHubUser user2;
	
	@Before
	public void setUp() throws Exception {
		source = mock(IGitHubSource.class);
		user1 = new GitHubUser("user1");
		user2 = new GitHubUser("user2");
		classUnderTest = new GitHubBiDirectionalSearchTestable(source, user1, user2);
	}

	@After
	public void tearDown() throws Exception {
		source = null;
		classUnderTest = null;
		user1 = null;
		user2 = null;	
	}

	@Test (expected=IllegalArgumentException.class)
	public void testConstructorSourceNullThrows() {
		classUnderTest = new GitHubBiDirectionalSearchTestable(null, user1, user2);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUser1NullThrows() {
		classUnderTest = new GitHubBiDirectionalSearchTestable(source, null, user2);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructorUser2NullThrows() {
		classUnderTest = new GitHubBiDirectionalSearchTestable(source, user1, null);
	}
	
	@Test
	public void testConstructorFieldSourceIsSet() {
		IGitHubSource actual = classUnderTest.getSource();
		Assert.assertEquals(source, actual);
	}
	
	@Test
	public void testConstructorFieldUser1IsSet() {
		GitHubUser actual = classUnderTest.getUser1();
		Assert.assertEquals(user1, actual);
	}
	
	@Test
	public void testConstructorFieldUser2IsSet() {
		GitHubUser actual = classUnderTest.getUser2();
		Assert.assertEquals(user2, actual);
	}
	
	@Test
	public void testConstructorVisitedUsersFromUser1ContainsUser1() {
		HashSet<String> actual = classUnderTest.getVisitedUsersFromUser1();
		HashSet<String> expected = new HashSet<>();
		expected.add("user1");
		
		Assert.assertEquals(expected, actual);		
	}
	
	@Test
	public void testConstructorVisitedUsersFromUser2ContainsUser2() {
		HashSet<String> actual = classUnderTest.getVisitedUsersFromUser2();
		HashSet<String> expected = new HashSet<>();
		expected.add("user2");
		
		Assert.assertEquals(expected, actual);		
	}
	
	@Test
	public void testConstructorUsersToVisitFromUser1ContainsUser1() {
		LinkedList<GitHubUser> actual = classUnderTest.getUsersToVisitFromUser1();
		
		LinkedList<GitHubUser> expected = new LinkedList<>();
		expected.add(user1);
		
		Assert.assertEquals(expected, actual);		
	}
	
	@Test
	public void testConstructorUsersToVisitFromUser2ContainsUser2() {
		LinkedList<GitHubUser> actual = classUnderTest.getUsersToVisitFromUser2();
		
		LinkedList<GitHubUser> expected = new LinkedList<>();
		expected.add(user2);
		
		Assert.assertEquals(expected, actual);		
	}
	
	@Test
	public void testGetShortestPathQueuesEmptyReturn0() {
		classUnderTest.setUsersToVisitFromUser1(new LinkedList<>());
		classUnderTest.setUsersToVisitFromUser2(new LinkedList<>());
		
		int actual = classUnderTest.getShortestPath();
		Assert.assertEquals(0, actual);
	}
}
