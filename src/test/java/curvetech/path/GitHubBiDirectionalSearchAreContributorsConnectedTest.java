package curvetech.path;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import curvetech.common.GitHubUser;
import curvetech.source.IGitHubSource;

public class GitHubBiDirectionalSearchAreContributorsConnectedTest {

	private IGitHubSource source;
	private GitHubBiDirectionalSearchTestable classUnderTest;
	private GitHubUser user1;
	private GitHubUser user2;
	private HashSet<String> setToCheck;
	private ArrayList<String> connections;
	private HashSet<String> setVisited;
	private ArrayList<String> userNames;
	
	@Before
	public void setUp() throws Exception {
		source = mock(IGitHubSource.class);
		user1 = new GitHubUser("user1");
		user2 = new GitHubUser("user2");
		classUnderTest = new GitHubBiDirectionalSearchTestable(source, user1, user2);
		setToCheck = new HashSet<>();
		connections = new ArrayList<>();
		setVisited = new HashSet<>();
		userNames = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		source = null;
		classUnderTest = null;
		user1 = null;
		user2 = null;	
		setToCheck = null;
		connections = null;
		setVisited = null;
		userNames = null;
	}

	@Test
	public void testAreContributorsConnectedUserNamesNullReturnFalse() {
		boolean actual = classUnderTest.areContributorsConnected(null, setVisited, setToCheck, connections);
		Assert.assertFalse(actual);
	}
	
	@Test
	public void testAreContributorsConnectedSetVisitedNullReturnFalse() {
		boolean actual = classUnderTest.areContributorsConnected(userNames, null, setToCheck, connections);
		Assert.assertFalse(actual);
	}
	
	@Test
	public void testAreContributorsConnectedSetToCheckNullReturnFalse() {
		boolean actual = classUnderTest.areContributorsConnected(userNames, setVisited, null, connections);
		Assert.assertFalse(actual);
	}
	
	@Test
	public void testAreContributorsConnectedConnectionNullReturnFalse() {
		boolean actual = classUnderTest.areContributorsConnected(userNames, setVisited, setToCheck, null);
		Assert.assertFalse(actual);
	}
	
	@Test
	public void testAreContributorsConnectedCheckIfUserNameIsVisited() {
		userNames.add("username");
		setVisited = mock(HashSet.class);
		classUnderTest.areContributorsConnected(userNames, setVisited, setToCheck, connections);
		verify(setVisited, times(1)).contains("username");
	}

	@Test
	public void testAreContributorsConnectedIfUserNameIsNotVisitedAddToVisited() {
		userNames.add("username");
		setVisited = mock(HashSet.class);
		classUnderTest.areContributorsConnected(userNames, setVisited, setToCheck, connections);
		verify(setVisited, times(1)).add("username");
	}
	
	@Test
	public void testAreContributorsConnectedIfUserNameIsNotVisitedAddToConnections() {
		userNames.add("username");
		setVisited = mock(HashSet.class);
		connections = mock(ArrayList.class);
		classUnderTest.areContributorsConnected(userNames, setVisited, setToCheck, connections);
		verify(connections, times(1)).add("username");
	}
	
	@Test
	public void testAreContributorsConnectedIfUserNameIsVisitedDontAddToVisited() {
		userNames.add("username");
		setVisited = mock(HashSet.class);
		when(setVisited.contains("username")).thenReturn(true);
		classUnderTest.areContributorsConnected(userNames, setVisited, setToCheck, connections);
		verify(setVisited, times(0)).add("username");
	}
	
	@Test
	public void testAreContributorsConnectedIfUserNameIsVisitedDontAddToConnections() {
		userNames.add("username");
		setVisited = mock(HashSet.class);
		when(setVisited.contains("username")).thenReturn(true);
		connections = mock(ArrayList.class);
		classUnderTest.areContributorsConnected(userNames, setVisited, setToCheck, connections);
		verify(connections, times(0)).add("username");
	}
}
