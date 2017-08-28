package curvetech.common;

import java.util.ArrayList;

public class GitHubUser {

	private String username;
	private ArrayList<String> repos;
	private ArrayList<String> connections = new ArrayList<>();
	
	public GitHubUser(String username, String... repos) {
		this.username = username;
		this.repos = new ArrayList<>();
		for (String repo: repos) {
			this.repos.add(repo);
		}
	}

	public String getUsername() {
		return username;
	}

	public ArrayList<String> getRepos() {
		return repos;
	}

	public ArrayList<String> getConnections() {
		return connections;
	}

	public void addConnections(ArrayList<String> connections) {
		this.connections.addAll(connections);
	}
}
