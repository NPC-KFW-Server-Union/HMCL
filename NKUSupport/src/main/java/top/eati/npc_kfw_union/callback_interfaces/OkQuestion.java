package top.eati.npc_kfw_union.callback_interfaces;

@FunctionalInterface
public interface OkQuestion {
    void run(String text, Runnable ok);
}
