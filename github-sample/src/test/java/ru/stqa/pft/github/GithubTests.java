package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.Coordinates;
import com.jcabi.github.RepoCommit;
import com.jcabi.github.RepoCommits;
import com.jcabi.github.RtGithub;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    RtGithub github = new RtGithub("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");
    RepoCommits commits = github.repos().get(
            new Coordinates.Simple("shinkai-tester", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableBiMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
