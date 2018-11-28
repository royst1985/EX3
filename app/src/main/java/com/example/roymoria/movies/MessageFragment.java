package com.example.roymoria.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MessageFragment extends Fragment {
    private static final String ARGS_MESSAGE = "args_message";
    public static MessageFragment newInstance(String message) {
        MessageFragment messageFragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_MESSAGE, message);
        messageFragment.setArguments(bundle);
        return messageFragment;
    }
}
