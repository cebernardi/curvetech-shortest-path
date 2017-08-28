package curvetech.path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import curvetech.common.GitHubRepo;
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
		
		HashSet<String> userSet1 = new HashSet<>();
		userSet1.add(username1);
		HashSet<String> userSet2 = new HashSet<>();
		userSet2.add(username2);
		
		LinkedList<GitHubUser> users1 = new LinkedList<>();	
		users1.add(user1);
		LinkedList<GitHubUser> users2 =  new LinkedList<>();
		users2.add(user2);
		
		int shortestPath = getShortestPath(users1, users2, userSet1, userSet2);
		return shortestPath;
	}
	
	
	
	protected int getShortestPath(
			LinkedList<GitHubUser> users1,
			LinkedList<GitHubUser> users2,
			HashSet<String> userSet1, 
			HashSet<String> userSet2) {
		
		int path1 = 0;
		int path2 = 0;
		
		while(!users1.isEmpty() || !users2.isEmpty()) {
			GitHubUser user1 = users1.poll();
			GitHubUser user2 = users2.poll();
			
			
			int dist = getConnectionDistance(user1, user2, userSet1, userSet2);
			if (dist > -1) {
				return path1 + path2 + dist;
			}
			
			if (user1 != null) {
				++path1;
				addConnections(user1, userSet1, users1);
			}
			
			if (user2 != null) {
				++path2;
				addConnections(user2, userSet2, users2);
			}
		}
		
		return 0;
	}

	protected void addConnections(GitHubUser user, HashSet<String> userSet, LinkedList<GitHubUser> users) {
		
		ArrayList<String> connections = user.getConnections();
		for (String conn: connections) {
				GitHubUser u = source.getUser(conn);
				users.push(u);
			
		}
		
	}

	protected int getConnectionDistance(
			GitHubUser user1, 
			GitHubUser user2, 
			HashSet<String> userSet1,
			HashSet<String> userSet2) {
		
		
		ArrayList<String> reposUser1 = null;
		ArrayList<String> reposUser2 = null;
		
		int index = 0;
		int size1 = 0;
		int size2 = 0;
		
		int path = 0;
		
		
		if (user1 != null) {
			reposUser1 = user1.getRepos();
			size1 = reposUser1.size();
		}
		
		if (user2 != null) {
			reposUser2 = user2.getRepos();
			size2 = reposUser2.size();
		}
		
		while (index < size1 || index < size2 ) {
			
			if (index < size1) {
				if (path < 2) ++path;
				String repoNameUser1 = reposUser1.get(index);
				ArrayList<String> contributors = getContributorsForRepo(repoNameUser1);
				ArrayList<String> connections = new ArrayList<>();
				boolean isConnected = areContributorsConnected(contributors, userSet1, userSet2, connections);
				if (isConnected) {
					return path;
				} else {
					user1.addConnections(connections);
				}
			}
			
			if (index < size2) {
				if (path < 2) ++path;
				String repoNameUser2 = reposUser2.get(index);
				ArrayList<String> contributors = getContributorsForRepo(repoNameUser2);
				ArrayList<String> connections = new ArrayList<>();
				boolean isConnected = areContributorsConnected(contributors, userSet2, userSet1, connections);
				if (isConnected) {
					return path;
				} else {
					user2.addConnections(connections);
				}
			}
			
			++index;
		}
		
		
		return -1;
	}
		

	protected boolean areContributorsConnected(
			ArrayList<String> userNames, 
			HashSet<String> setVisited,
			HashSet<String> setToCheck, 
			ArrayList<String> connections) {
		
		boolean isConnected = false;
		
		for (String userName: userNames) {
			if (!setVisited.contains(userName)) {
				if (setToCheck.contains(userName)) {
					isConnected = true;
					break;
				} else {
					setVisited.add(userName);
					connections.add(userName);
				}
			} 
		}
		
		
		return isConnected;
	}

	protected ArrayList<String> getContributorsForRepo(String repoName) {
		
		ArrayList<String> connections = new ArrayList<>();
		
		GitHubRepo repo = source.getRepo(repoName);
		connections.addAll(repo.getContributors());
				
		return connections;
	}

}
