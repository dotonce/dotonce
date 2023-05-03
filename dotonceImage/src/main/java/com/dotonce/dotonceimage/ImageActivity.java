package com.dotonce.dotonceimage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.dotonce.mainconfig.MainFixed.AppCompatClass;

import java.util.ArrayList;


public class ImageActivity extends AppCompatClass implements OnImagesSelected{
    public ArrayList<MediaModel> arrayListMedia;
    private Uri  mediaUri;
    private String chooser_type = "image";
    private  boolean chooser_multiple =true, autoRename;
    private   int count =0;
    private  String  folder_name="images";
    private  ActivityResultLauncher<Intent>  activityResultLauncherMedia;
    private   String imageName,IP;
    private  boolean watermark;
    int watermarkDrawable,alphaPercent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ini();
        activityMedia();

    }

    public void ini(){
        arrayListMedia = new ArrayList<>();
    }
    @SuppressLint("SetTextI18n")
    public void activityMedia(){
        activityResultLauncherMedia=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if(result.getData()!=null) {
                            if (result.getData().getClipData() != null) {
                                int countClipData = result.getData().getClipData().getItemCount();
                                int currentImage = 0;

                                while (currentImage < countClipData) {
                                    mediaUri = result.getData().getClipData().getItemAt(currentImage).getUri();
                                    ContentResolver cr = this.getContentResolver();
                                    String type = cr.getType(mediaUri).replace("video/","")
                                            .replace("image/","");
                                    arrayListMedia.add(new MediaModel("media_"+System.currentTimeMillis(),mediaUri,type));
                                    currentImage = currentImage + 1;
                                }

                            }
                            else {
                                mediaUri =result.getData().getData();
                                ContentResolver cr = this.getContentResolver();
                                String type = cr.getType(mediaUri).replace("video/",".")
                                        .replace("image/","");
                                arrayListMedia.add(new MediaModel("media_"+System.currentTimeMillis(),mediaUri,type));
                            }
                            onImagesSelectedSuccess(arrayListMedia);
                        }
                    }

                }
        );
    }
    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(ImageActivity.this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                goIntentMedia();
            }else{
                ActivityCompat.requestPermissions(ImageActivity.this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 100);
            }
        }else{

            if (ActivityCompat.checkSelfPermission(ImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                goIntentMedia();
            } else {
                ActivityCompat.requestPermissions(ImageActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            goIntentMedia();
        } else {
            Toast.makeText(ImageActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
    public void goIntentMedia() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(ImageActivity.this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(chooser_type+"/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, chooser_multiple);
                activityResultLauncherMedia.launch(intent);
            } else {
                ActivityCompat.requestPermissions(ImageActivity.this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 100);
            }
        }else{
            if (ActivityCompat.checkSelfPermission(ImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(chooser_type+"/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, chooser_multiple);
                activityResultLauncherMedia.launch(intent);
            } else {
                ActivityCompat.requestPermissions(ImageActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }

    }

    /**
     *
     * IP like <a href="https://d.com/mar/">...</a>
     */
    public void setChooseSetting(String IP,String chooser_type, boolean chooser_multiple, String imageName, boolean autoRename){
        this.chooser_type = chooser_type;
        this.chooser_multiple = chooser_multiple;
        this.imageName = imageName;
        this.autoRename = autoRename;
        this.IP = IP;
    }

    public void setUploadSetting(boolean watermark,int watermarkDrawable,int alphaPercent,String folder_name){
        this.folder_name = folder_name;
        this.watermark = watermark;
        this.watermarkDrawable = watermarkDrawable;
        this.alphaPercent = alphaPercent;
    }
    public String getRealPath(Uri media_uri, String media_type) {
        boolean b = String.valueOf(media_uri).startsWith("content://media") | String.valueOf(media_uri).startsWith("content://com.google");
        if(media_type.equals("image")) {
            if (b) {
                String res = null;
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(media_uri, proj, null, null, null);
                if (cursor.moveToFirst()) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    res = cursor.getString(column_index);
                }
                cursor.close();
                return res;
            } else {
                String wholeID;
                wholeID = DocumentsContract.getDocumentId(media_uri);
                String id = null;
                if (wholeID != null) {
                    id = wholeID.split(":")[1];
                }
                String[] column = {MediaStore.Images.Media.DATA};
                String sel = MediaStore.Images.Media._ID + "=?";
                Cursor cursor = getContentResolver().
                        query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                column, sel, new String[]{id}, null);
                String filePath = "";
                int columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
                return filePath;
            }
        }
        else {

            if (b) {
                String res = null;
                String[] proj = {MediaStore.Video.Media.DATA};
                Cursor cursor = getContentResolver().query(media_uri, proj, null, null, null);
                if (cursor.moveToFirst()) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                    res = cursor.getString(column_index);
                }
                cursor.close();
                return res;
            } else {
                String wholeID;
                wholeID = DocumentsContract.getDocumentId(media_uri);
                String id = null;
                if (wholeID != null) {
                    id = wholeID.split(":")[1];
                }
                String[] column = {MediaStore.Video.Media.DATA};
                String sel = MediaStore.Video.Media._ID + "=?";
                Cursor cursor = getContentResolver().
                        query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                column, sel, new String[]{id}, null);
                String filePath = "";
                int columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
                return filePath;
            }
        }
    }


    public boolean hasImages(){
        return arrayListMedia.size() != 0;
    }


    public void uploadMediaNoCompress(OnMediaUploaded onMediaUploaded) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getQuantityString(R.plurals.adding_image,arrayListMedia.size()));
        progressDialog.setCancelable(false);
        progressDialog.show();

        for (int i = 0; i < arrayListMedia.size(); i++) {
            String type = String.valueOf(arrayListMedia.get(i).getType());
            if(autoRename){
                imageName = arrayListMedia.get(i).getName() + "." + type;
            }else {
                imageName = imageName+ "." + type;
            }
            String filePath;
            if (type.equals("png") | type.equals("jpg") | type.equals("jpeg") | type.equals("gif") | type.equals("webp")) {
                filePath = getRealPath(arrayListMedia.get(i).getPath(), "image");

            } else {
                filePath = getRealPath(arrayListMedia.get(i).getPath(), "video");

            }
           ImageUploaderNoCompressClass.uploadImage(IP,filePath, imageName, folder_name, new ImageUploaderNoCompressClass.onSuccessfulTask() {
                @Override
                public void onSuccess() {
                    progressDialog.setMessage(getResources().getQuantityString(R.plurals.adding_image,arrayListMedia.size())+" " + count + "/" + arrayListMedia.size());

                    count++;
                    if (count >= arrayListMedia.size()) {
                        progressDialog.dismiss();
                        onMediaUploaded.onSuccess(imageName, type);
                        arrayListMedia.clear();

                    }
                }

                @Override
                public void onFailed(String error) {
                    onMediaUploaded.onFailed(error);
                }
            });
        }
    }

    public void uploadMedia(OnMediaUploaded onMediaUploaded) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getResources().getQuantityString(R.plurals.adding_image,arrayListMedia.size()));
            progressDialog.setCancelable(false);
            progressDialog.show();

            for (int i = 0; i < arrayListMedia.size(); i++) {
                String type = String.valueOf(arrayListMedia.get(i).getType());
                if(autoRename){
                    imageName = arrayListMedia.get(i).getName() + "." + type;
                }else {
                    imageName = imageName+ "." + type;
                }
                String filePath;
                if (type.equals("png") | type.equals("jpg") | type.equals("jpeg") | type.equals("gif") | type.equals("webp")) {
                    filePath = getRealPath(arrayListMedia.get(i).getPath(), "image");

                } else {
                    filePath = getRealPath(arrayListMedia.get(i).getPath(), "video");

                }
                ImageUploaderClass.uploadImage(IP,ImageCompress.getInstant().getCompressedBitmap(this, watermark,watermarkDrawable,alphaPercent,filePath,1920f,1080f,80), imageName, folder_name, new ImageUploaderClass.onSuccessfulTask() {
                    @Override
                    public void onSuccess() {
                        progressDialog.setMessage(getResources().getQuantityString(R.plurals.adding_image,arrayListMedia.size())+" " + count + "/" + arrayListMedia.size());

                        count++;
                        if (count >= arrayListMedia.size()) {
                            progressDialog.dismiss();
                            onMediaUploaded.onSuccess(imageName, type);
                            arrayListMedia.clear();

                        }
                    }

                    @Override
                    public void onFailed(String error) {
                        onMediaUploaded.onFailed(error);
                    }
                });
            }
    }

    @Override
    public void onImagesSelectedSuccess(ArrayList<MediaModel> arrayList) {
    }
}
