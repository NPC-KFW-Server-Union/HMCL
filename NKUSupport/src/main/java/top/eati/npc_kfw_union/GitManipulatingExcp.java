package top.eati.npc_kfw_union;

public class GitManipulatingExcp extends RuntimeException {
    public GitManipulatingExcp() {
    }

    public GitManipulatingExcp(String message) {
        super(message);
    }

    public GitManipulatingExcp(String message, Throwable cause) {
        super(message, cause);
    }

    public GitManipulatingExcp(Throwable cause) {
        super(cause);
    }

    public GitManipulatingExcp(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
