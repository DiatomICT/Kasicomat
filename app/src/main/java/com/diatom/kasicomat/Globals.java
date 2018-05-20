package com.diatom.kasicomat;

import android.app.Application;

public class Globals extends Application {
    private boolean dummyInserted = false;

    public boolean isDummyInserted() {
        return dummyInserted;
    }

    public void setDummyInserted(boolean dummyInserted) {
        this.dummyInserted = dummyInserted;
    }
}
