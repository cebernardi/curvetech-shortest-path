package curvetech;

import java.util.HashSet;
import java.util.LinkedList;

import curvetech.common.GitHubUser;
import curvetech.path.GitHubBiDirectionalSearch;
import curvetech.source.IGitHubSource;

public class GitHubBiDirectionalSearchTestable extends GitHubBiDirectionalSearch {
	

	public GitHubBiDirectionalSearchTestable(IGitHubSource source, GitHubUser user1, GitHubUser user2) {
		super(source, user1, user2);
	}
	
	public IGitHubSource getSource() {
		return source;
	}
	
	public GitHubUser getUser1() {
		return user1;
	}

	public GitHubUser getUser2() {
		return user2;
	}

	public HashSet<String> getVisitedUsersFromUser1() {
		return visitedUsersFromUser1;
	}

	public HashSet<String> getVisitedUsersFromUser2() {
		return visitedUsersFromUser2;
	}

	public LinkedList<GitHubUser> getUsersToVisitFromUser1() {
		return usersToVisitFromUser1;
	}

	public LinkedList<GitHubUser> getUsersToVisitFromUser2() {
		return usersToVisitFromUser2;
	}
	
	public void setUsersToVisitFromUser1(LinkedList<GitHubUser> queue) {
		usersToVisitFromUser1 = queue;
	}
	
	public void setUsersToVisitFromUser2(LinkedList<GitHubUser> queue) {
		usersToVisitFromUser2 = queue;
	}
	
	public void addConnectionsToQueue(GitHubUser user, LinkedList<GitHubUser> users) {
		super.addConnectionsToQueue(user, users);
	}
	
}
