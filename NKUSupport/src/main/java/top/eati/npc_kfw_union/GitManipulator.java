package top.eati.npc_kfw_union;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.jetbrains.annotations.NotNull;
import top.eati.npc_kfw_union.callback_interfaces.OkQuestion;
import top.eati.npc_kfw_union.callback_interfaces.ShowErr;

import java.io.IOException;
import java.nio.file.Paths;

public class GitManipulator {

    /*------------ Callbacks ------------*/
    private ShowErr showErrFunc;
    private OkQuestion okQuestionFunc;

    /*------------ Other properties ------------*/
    private Git git = null;

    private boolean shortCircuit = false;

    private static GitManipulator instance = null;

    /*------------ Functions  ------------*/
    public static GitManipulator getInstance(){
        if (instance == null)
            throw new IllegalStateException("GitManipulator has not created.");
        return instance;
    }

    public static void createInstance(@NotNull ShowErr showErrFunc) throws IOException {
        instance = new GitManipulator(showErrFunc);
    }

    private GitManipulator(@NotNull ShowErr showErrFunc) throws IOException {
        this.showErrFunc = showErrFunc;
        this.createGitObj();
        try {
            this.git.status().call();
        } catch (NoWorkTreeException e) {
            showErrFunc.run("启动器不位于一个 GIT 仓库下，版本控制功能将停用！");
            this.shortCircuit = true;
        } catch (GitAPIException e) {
            throw new GitManipulatingExcp(e);
        }
    }

    private void createGitObj() throws IOException {
        Repository repository = new FileRepositoryBuilder()
                .setGitDir(Paths.get("",".git").toFile())
                .build();
        this.git = new Git(repository);
    }
    public void commitAllChangeOfTrackedFile() {
        //Controllers.dialog("GIT 操作失败！", null, MessageDialogPane.MessageType.ERROR);
        //showErrFunc.run("GIT 操作失败！");
        if(this.shortCircuit) return;
        try {
            if(this.git.status().call().isClean()) return;
        } catch (GitAPIException e) {
            //throw new RuntimeException(e);
            //showErrFunc.run("获取 GIT 仓库状态失败（GitAPIException）！");
            //e.printStackTrace();
            throw new GitManipulatingExcp("获取 GIT 仓库状态失败！", e);
        }

        // If there's changes, try to commit them.
        try {
            this.git.add().call();
        } catch (GitAPIException e) {
            throw new GitManipulatingExcp("添加文件失败！", e);
        }

        try {
            this.git.commit().call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            this.okQuestionFunc.run("提交文件失败！\n" +
                    "您有一次机会——程序可以试图撤回 add 的操作并将仓库恢复到之前的状态。" +
                    "是否要这样做（但是进行此操作而再度发生的异常，程序将无能为力！）？", () -> {
                try {
                    this.git.reset().call();
                } catch (GitAPIException ex) {
                    showErrFunc.run("不幸地，回滚 add 操作失败！请手动操作 git 使您仓库回到" +
                            "正确的状态，或求助于他人。");
                    ex.printStackTrace();
                }
            });
            throw new GitManipulatingExcp("提交失败！", e);
        }

    }
}

