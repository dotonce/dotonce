package com.dotonce.image;

public interface OnMediaUploaded {
    void onSuccess(String imageName, String type);

    void onFailed(String error);
}
