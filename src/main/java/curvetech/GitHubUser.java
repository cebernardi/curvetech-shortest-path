package curvetech;

import java.util.ArrayList;

public class GitHubUser {

	private String username;
	private ArrayList<GitHubRepo> repos;
	
	public GitHubUser(String username, ArrayList<GitHubRepo> repos) {
		this.username = username;
		this.repos = new ArrayList<>(repos);
	}

	public String getUsername() {
		return username;
	}

	public ArrayList<GitHubRepo> getRepos() {
		return repos;
	}
	
}
