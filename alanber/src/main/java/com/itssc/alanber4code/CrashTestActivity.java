package com.itssc.alanber4code;

import android.app.Activity;
import android.os.Bundle;

public class CrashTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s = null;
        s.toCharArray();
    }
}
