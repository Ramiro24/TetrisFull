package com.tetris.view;

import com.tetris.model.events.EventSource;
import com.tetris.model.events.EventType;
import com.tetris.model.events.MoveEvent;

public interface Keys {
    void upKey();

    void downKey();

    void leftKey();

    void rightKey();

}
