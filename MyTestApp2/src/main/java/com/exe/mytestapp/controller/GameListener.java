package com.exe.mytestapp.controller;

import com.exe.mytestapp.model.Player;

/**
 * Created by Nikita on 22.11.13.
 */
public interface GameListener {
    public void onMoveFinished(int x, int y, Player player);
}
