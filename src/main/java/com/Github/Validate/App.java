package com.Github.Validate;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.egit.github.core.CommitComment;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.CommitStats;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		// GitHubClient client = new
		// GitHubClient("http://github.training.cerner.com/");
		// client.setCredentials("AA027968", "");

		final String user = "acharjeeanirban";
		final String format = "{0}) {1}- created on {2}";
		int count = 1;
		RepositoryService service = new RepositoryService();
		List<CommitComment> lc = null;
		CommitService cs = new CommitService();
		try {
			for (Repository repo : service.getRepositories(user))
				// System.out.println("The commit message is = " + repo.getc)
				System.out.println(MessageFormat.format(format, count++,
						repo.getName(), repo.getCreatedAt()));
			/*
			 * lc = cs.getComments(repo); for(CommitComment c: lc) {
			 * System.out.println(c.getCommitId());
			 * 
			 * }
			 */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// //////////////////////////////////////////////

		// final RepositoryId repo = new RepositoryId("github", "hubot");
		final String message = "   {0} by {1} on {2}";
		final CommitService ser = new CommitService();
		int pages = 1;
		CommitStats c = new CommitStats();
		try {
			for (Repository repo : service.getRepositories(user)) {
				for (Collection<RepositoryCommit> commits : ser
						.pageCommits(repo)) {
					System.out.println("Commit Page " + pages++);
					for (RepositoryCommit commit : commits) {
						String sha = commit.getSha().substring(0, 7);
						String author = commit.getCommit().getAuthor()
								.getName();
						Date date = commit.getCommit().getAuthor().getDate();
						// System.out.println(commit.getCommit().getMessage());
						c = commit.getStats();
						// System.out.println("The number of additiosn are " +
						// c.getTotal());
						// System.out.println(MessageFormat.format(message, sha,
						// author, date,commit.getCommit().getMessage()));
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			for (Repository repo : service.getRepositories(user)) {
				for (RepositoryCommit rc : ser.getCommits(repo)) {
					System.out.println(rc.getCommit().getMessage());
					if (rc.getStats() == null) {
						continue;
					} else {
						System.out.println( " the number of additions " + rc.getStats().getAdditions());
					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
