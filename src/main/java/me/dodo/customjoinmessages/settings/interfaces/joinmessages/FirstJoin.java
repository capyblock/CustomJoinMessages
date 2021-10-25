package me.dodo.customjoinmessages.settings.interfaces.joinmessages;

import java.util.List;

public interface FirstJoin {
    boolean isFirstJoinEnabled();

    List<String> getFirstJoinContext();
}
