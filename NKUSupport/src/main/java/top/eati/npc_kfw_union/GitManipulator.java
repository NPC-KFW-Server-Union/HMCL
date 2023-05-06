package top.eati.npc_kfw_union;

import jdk.jfr.internal.Repository;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import javax.imageio.IIOException;
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
    public Git getRepo(){
        if(git == null){
            try{
                Repository repository = new FileRepositoryBuilder()
                        .setGitDir(Paths.get("",".git").toFile())
                        .build();
            }catch (IIOException e){
                e.printStackTrace();
            }
        }
        return git;
    }
}