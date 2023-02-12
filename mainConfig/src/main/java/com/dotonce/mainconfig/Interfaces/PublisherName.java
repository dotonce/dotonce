package com.dotonce.mainconfig.Interfaces;

import androidx.annotation.NonNull;

public enum PublisherName {
    ALAYAN{
        @NonNull
        @Override
        public String toString() {
            return "alayan";
        }
    },
    DOTONCE{
        @NonNull
        @Override
        public String toString() {
            return "dotonce";
        }
    }
}
