package curvetech;

import java.util.ArrayList;

public class GitHubRepo {
	private String repoId;
	private ArrayList<GitHubUser> contributors;
	
	public GitHubRepo(String repoId, ArrayList<GitHubUser> contributors) {
		this.repoId = repoId;
		this.contributors = contributors;
	}

	public String getRepoId() {
		return repoId;
	}

	public ArrayList<GitHubUser> getContributors() {
		return contributors;
	}
	
}
