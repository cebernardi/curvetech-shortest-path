package curvetech.source;

import curvetech.common.GitHubRepo;
import curvetech.common.GitHubUser;

public interface IGitHubSource {
	public GitHubUser getUser(String username);
	public GitHubRepo getRepo(String repoName);
}
