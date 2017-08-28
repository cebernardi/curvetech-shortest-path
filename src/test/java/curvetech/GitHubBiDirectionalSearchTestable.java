package curvetech;

import curvetech.common.GitHubUser;
import curvetech.path.GitHubBiDirectionalSearch;
import curvetech.source.IGitHubSource;

public class GitHubBiDirectionalSearchTestable extends GitHubBiDirectionalSearch {

	public GitHubBiDirectionalSearchTestable(IGitHubSource source, GitHubUser user1, GitHubUser user2) {
		super(source, user1, user2);
	}
	
	public IGitHubSource getSource() {
		return super.source;
	}
	
	public GitHubUser getUser1() {
		return super.user1;
	}

	public GitHubUser getUser2() {
		return super.user2;
	}
}
