package curvetech.path;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import curvetech.common.GitHubUser;
import curvetech.source.IGitHubSource;


public class GitHubBiDirectionalSearchAddConnectionTest {

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

	@Test
	public void testAddConnectionsToQueueUserNullDoesNothing() {
		LinkedList<GitHubUser> queue = new LinkedList<>();
		classUnderTest.addConnectionsToQueue(null, queue );
		verify(source, times(0)).getUser(anyString());
	}
	
	@Test
	public void testAddConnectionsToQueueUserHasConnectionsCallsSourceWithUserName() {
		LinkedList<GitHubUser> queue = new LinkedList<>();
		ArrayList<String> connections = new ArrayList<>();
		connections.add("conn1");
		user1.addConnections(connections);
		classUnderTest.addConnectionsToQueue(user1, queue );
		verify(source, times(1)).getUser("conn1");
	}
	
	@Test
	public void testAddConnectionsToQueueUserNullBySourceIsNotPutInQueue() {
		LinkedList<GitHubUser> queue = new LinkedList<>();
		ArrayList<String> connections = new ArrayList<>();
		connections.add("conn1");
		user1.addConnections(connections);
		classUnderTest.addConnectionsToQueue(user1, queue);
		
		LinkedList<GitHubUser> expected =  new LinkedList<>();
		Assert.assertEquals(expected, queue);
	}

	@Test
	public void testAddConnectionsToQueueAddsRetrievedUserToQuele() {
		LinkedList<GitHubUser> queue = new LinkedList<>();
		ArrayList<String> connections = new ArrayList<>();
		connections.add("conn1");
		user1.addConnections(connections);
		when(source.getUser("conn1")).thenReturn(user2);
		classUnderTest.addConnectionsToQueue(user1, queue );
		
		LinkedList<GitHubUser> expected =  new LinkedList<>();
		expected.add(user2);
		
		Assert.assertEquals(expected, queue);
	}
}
