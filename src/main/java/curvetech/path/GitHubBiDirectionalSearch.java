package curvetech.path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import curvetech.common.GitHubRepo;
import curvetech.common.GitHubUser;
import curvetech.source.IGitHubSource;

public class GitHubBiDirectionalSearch {
	
	protected IGitHubSource source;
	protected GitHubUser user1;
	protected GitHubUser user2;
	
	protected HashSet<String> visitedUsersFromUser1 = new HashSet<>();
	protected HashSet<String> visitedUsersFromUser2 = new HashSet<>();
	
	protected LinkedList<GitHubUser> usersToVisitFromUser1 = new LinkedList<>();	
	protected LinkedList<GitHubUser> usersToVisitFromUser2 =  new LinkedList<>();
	
	
	public GitHubBiDirectionalSearch(IGitHubSource source, GitHubUser user1, GitHubUser user2) {
		
		if (source == null || user1 == null || user2 == null) {
			throw new IllegalArgumentException();
		}
		
		this.user1 = user1;
		this.user2 = user2;
		this.source = source;
		
		visitedUsersFromUser1.add(user1.getUsername());		
		visitedUsersFromUser2.add(user2.getUsername());
			
		usersToVisitFromUser1.add(user1);		
		usersToVisitFromUser2.add(user2);
	}
	
	public int getShortestPath() {
		
		int path1 = 0;
		int path2 = 0;
		
		while(!usersToVisitFromUser1.isEmpty() || !usersToVisitFromUser2.isEmpty()) {
			GitHubUser user1 = usersToVisitFromUser1.poll();
			GitHubUser user2 = usersToVisitFromUser2.poll();
			
			
			int dist = getUsersImmediateDistance(user1, user2);
			
			if (dist > -1) {
				return path1 + path2 + dist;
			}
			
			if (user1 != null) {
				++path1;
				addConnectionsToQueue(user1, usersToVisitFromUser1);
			}
			
			if (user2 != null) {
				++path2;
				addConnectionsToQueue(user2, usersToVisitFromUser2);
			}
		}
		
		return 0;
	}

	protected void addConnectionsToQueue(GitHubUser user, LinkedList<GitHubUser> users) {
		if (user != null) {
			ArrayList<String> connections = user.getConnections();
			for (String conn: connections) {
					GitHubUser u = source.getUser(conn);
					if (u != null) {
						users.push(u);		
					}
			}
		}
	}

	protected int getUsersImmediateDistance(GitHubUser user1, GitHubUser user2) {		
		
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
				boolean isConnected = areContributorsConnected(contributors, visitedUsersFromUser1, visitedUsersFromUser2, connections);
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
				boolean isConnected = areContributorsConnected(contributors, visitedUsersFromUser2, visitedUsersFromUser1, connections);
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
		

	protected boolean areContributorsConnected (
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
