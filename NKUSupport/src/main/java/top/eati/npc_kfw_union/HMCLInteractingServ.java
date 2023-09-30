package top.eati.npc_kfw_union;

import top.eati.npc_kfw_union.callback_interfaces.ShowErr;

/**
 * Service for interacting with HMCL.
 *
 * @author ChihHao Su
 */
public class HMCLInteractingServ {

    /*------------ Singleton ------------*/
    private static HMCLInteractingServ INSTANCE = null;

    public static HMCLInteractingServ getInstance() {
        if(INSTANCE == null)
            throw new RuntimeException("HMCLInteractingServ 未初始化！");

        return INSTANCE;
    }

    /*------------ Static Functions ------------*/
    public static void init(
            ShowErr showErrFunc
    ) {
        INSTANCE = new HMCLInteractingServ(showErrFunc);
    }

    /*------------ Callbacks ------------*/
    private ShowErr showErrFunc;

    public ShowErr getShowErrFunc() {
        return showErrFunc;
    }

    /*------------ Other properties ------------*/

    /*------------ Functions  ------------*/
    public HMCLInteractingServ(ShowErr showErrFunc) {
        this.showErrFunc = showErrFunc;
    }






}
