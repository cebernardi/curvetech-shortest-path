package curvetech.source;

import curvetech.GitHubUser;

public interface IGitHubSource {
	public GitHubUser getUser(String username);
}
