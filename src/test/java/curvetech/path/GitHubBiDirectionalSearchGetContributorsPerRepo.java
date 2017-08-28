package curvetech.path;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import curvetech.common.GitHubRepo;
import curvetech.common.GitHubUser;
import curvetech.source.IGitHubSource;

public class GitHubBiDirectionalSearchGetContributorsPerRepo {

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
	public void testGetContributorsForRepoRepoNameNullReturnEmptyArrayList() {
		ArrayList<String> actual = classUnderTest.getContributorsForRepo(null);
		ArrayList<String> expected = new ArrayList<>();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetContributorsForRepoRepoNameEmptyReturnEmptyArrayList() {
		ArrayList<String> actual = classUnderTest.getContributorsForRepo("");
		ArrayList<String> expected = new ArrayList<>();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetContributorsForRepoNotExistingReturnEmptyArrayList() {
		ArrayList<String> actual = classUnderTest.getContributorsForRepo("notExistingRepo");
		ArrayList<String> expected = new ArrayList<>();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetContributorsForRepoCallsGetRepo() {
		classUnderTest.getContributorsForRepo("repo");
		verify(source, times(1)).getRepo("repo");
	}
	
	@Test
	public void testGetContributorsForRepoWithoutContributorsReturnEmptyArrayList() {
		GitHubRepo repoWithoutContributors = new GitHubRepo("repoWithoutContributors");
		when(source.getRepo("repoWithoutContributors")).thenReturn(repoWithoutContributors);
		ArrayList<String> actual = classUnderTest.getContributorsForRepo("repoWithoutContributors");
		ArrayList<String> expected = new ArrayList<>();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testGetContributorsForRepoWithContributorsReturnArrayList() {
		GitHubRepo repo = new GitHubRepo("repo", "user1", "user2");
		when(source.getRepo("repo")).thenReturn(repo);
		ArrayList<String> actual = classUnderTest.getContributorsForRepo("repo");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("user1");
		expected.add("user2");
		Assert.assertEquals(expected, actual);
	}

}
