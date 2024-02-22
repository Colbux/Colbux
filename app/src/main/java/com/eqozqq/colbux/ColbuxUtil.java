package com.eqozqq.colbux;
import android.app.*;
import android.content.*;
import android.graphics.drawable.*;
import android.net.*;
import android.util.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;

import java.io.*;
import java.util.*;

public class ColbuxUtil {
    public static final Random RANDOM = new Random();

    public static void showMessage(Context _context, String _s) {
        Toast.makeText(_context, _s, Toast.LENGTH_SHORT).show();
    }

    public static int getRandom(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
}
