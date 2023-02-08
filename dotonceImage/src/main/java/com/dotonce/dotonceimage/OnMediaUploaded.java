package com.dotonce.dotonceimage;

public interface OnMediaUploaded {
    void onSuccess(String imageName, String type);

    void onFailed(String error);
}
