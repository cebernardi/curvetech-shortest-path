package curvetech.server;

import static spark.Spark.*;

import curvetech.GitHub;

public class Main {

	private static GitHub gitHub;
	
	public static void main(String[] args) {
		
		gitHub = new GitHub();
		
		 get("/hello/:user", (req, res) -> helloWorld(req.params("user")));
		 		 
		 get("/path/:user1/:user2", (req, res) -> gitHub.getShortestPath(req.params("user1"), req.params("user2")));
	}
	
	private static String helloWorld(String user) {
		return "Hello World " + user;
	}
}
