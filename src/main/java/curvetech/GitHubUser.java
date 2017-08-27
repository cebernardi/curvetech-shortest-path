package curvetech;

import java.util.ArrayList;

public class GitHubUser {

	private String username;
	private ArrayList<String> repos;
	
	public GitHubUser(String username, ArrayList<String> repos) {
		this.username = username;
		this.repos = new ArrayList<>(repos);
	}

	public String getUsername() {
		return username;
	}

	public ArrayList<String> getRepos() {
		return repos;
	}
	
}
