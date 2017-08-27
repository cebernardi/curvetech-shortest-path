package curvetech;

import java.util.ArrayList;

public class GitHubRepo {
	private String repoId;
	private ArrayList<String> contributors;
	
	public GitHubRepo(String repoId, String... contributors) {
		this.repoId = repoId;
		this.contributors = new ArrayList<>();
		for (String con: contributors) {
			this.contributors.add(con);
			}
	}

	public String getRepoId() {
		return repoId;
	}

	public ArrayList<String> getContributors() {
		return contributors;
	}
	
}
