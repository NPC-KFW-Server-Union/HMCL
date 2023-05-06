package top.eati.npc_kfw_union;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Paths;

public class GitManipulator {

    private Git git = null;
    private static GitManipulator gitManipulator =null;
    private static  GitManipulator getInstance(){
        if(gitManipulator == null){
            gitManipulator = new GitManipulator();
        }
        return gitManipulator;
    }
    public Git getRepo() throws IOException {
        if(git == null){
                Repository repository = new FileRepositoryBuilder()
                        .setGitDir(Paths.get("",".git").toFile())
                        .build();
        }
        return git;
        }
    }
