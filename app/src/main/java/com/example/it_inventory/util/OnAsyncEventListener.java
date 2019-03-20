package com.example.it_inventory.util;

/**
 * This generic interface is used as custom callback for async tasks.
 * For an example usage see {@link ch.hevs.aislab.intro.ui.ClientDetails:197}. <-- A MODIFIER!!!!!
 */
public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
