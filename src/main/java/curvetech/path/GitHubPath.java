package curvetech.path;

import curvetech.common.GitHubUser;
import curvetech.source.IGitHubSource;

public class GitHubPath {
	
	private IGitHubSource source;
	
	public GitHubPath(IGitHubSource source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		this.source = source;
	}
	
	public int getShortestPath(String username1, String username2) {

		if (username1 == null || username1.isEmpty() || username2 == null || username2.isEmpty()) {
			return 0;
		}
		
		GitHubUser user1 = source.getUser(username1);
		if (user1 == null) {
			return 0;
		}
		
		GitHubUser user2 = source.getUser(username2);
		if (user2 == null) {
			return 0;
		}
		
		if (username1.equals(username2)) {
			return 1;
		}
		
		GitHubBiDirectionalSearch search = new GitHubBiDirectionalSearch(source, user1, user2);
		int shortestPath = search.getShortestPath();
		
		return shortestPath;
	}
	
}
