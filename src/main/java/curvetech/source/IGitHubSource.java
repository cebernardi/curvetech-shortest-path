package curvetech.source;

import curvetech.GitHubRepo;
import curvetech.GitHubUser;

public interface IGitHubSource {
	public GitHubUser getUser(String username);
	public GitHubRepo getRepo(String repoName);
}
