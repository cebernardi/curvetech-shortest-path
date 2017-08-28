package curvetech.source;

import java.util.HashMap;

import curvetech.common.GitHubRepo;
import curvetech.common.GitHubUser;

public class GitHubDemoSource implements IGitHubSource {
	
	private HashMap<String, GitHubUser> users = new HashMap<>();
	private HashMap<String, GitHubRepo> repos = new HashMap<>();
	
	private GitHubUser user1;
	private GitHubUser user2;
	private GitHubUser user3;
	private GitHubUser user4;
	private GitHubUser user5;
	private GitHubUser user6;
	private GitHubRepo repo1;
	private GitHubRepo repo2;
	private GitHubRepo repo3;
	private GitHubRepo repo4;
	private GitHubRepo repo5;
	
	public GitHubDemoSource() {
		repo1 = new GitHubRepo("repo1",  "user1", "user2");
		repo2 = new GitHubRepo("repo2",  "user2", "user3");
		repo3 = new GitHubRepo("repo3",  "user3");
		repo4 = new GitHubRepo("repo4",  "user3", "user4");
		repo5 = new GitHubRepo("repo5", "user6");
		
		repos.put(repo1.getRepoId(), repo1);
		repos.put(repo2.getRepoId(), repo2);
		repos.put(repo3.getRepoId(), repo3);
		repos.put(repo4.getRepoId(), repo4);
		repos.put(repo5.getRepoId(), repo5);
		
		user1 = new GitHubUser("user1", "repo1");
		user2 = new GitHubUser("user2", "repo2", "repo1");
		user3 = new GitHubUser("user3", "repo3", "repo4", "repo2");
		user4 = new GitHubUser("user4", "repo4");
		user5 = new GitHubUser("user5");
		user6 = new GitHubUser("user6", "repo5");
		
		users.put(user1.getUsername(), user1);
		users.put(user2.getUsername(), user2);
		users.put(user3.getUsername(), user3);
		users.put(user4.getUsername(), user4);
		users.put(user5.getUsername(), user5);
		users.put(user6.getUsername(), user6);
	}

	@Override
	public GitHubUser getUser(String username) {
		return users.get(username);
	}

	@Override
	public GitHubRepo getRepo(String repoName) {
		return repos.get(repoName);
	}

}
