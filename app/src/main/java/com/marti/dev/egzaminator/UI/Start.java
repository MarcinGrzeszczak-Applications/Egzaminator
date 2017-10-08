package com.marti.dev.egzaminator.UI;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.marti.dev.egzaminator.CustomViews.ErrorWindow;
import com.marti.dev.egzaminator.R;
import com.marti.dev.egzaminator.core.JsonLoader;
import com.marti.dev.egzaminator.core.ProgressWindow;
import com.marti.dev.egzaminator.core.Storage;
import com.marti.dev.egzaminator.core.TestModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Start extends AppCompatActivity implements LoaderManager.LoaderCallbacks<TestModel> {

    private final int LOADER_MANAGER = 0;
    private final int READ_STORAGE_PERMISSION = 1;
    private final int CHOOSE_FILE_FROM_STORAGE = 2;


    private TestModel mCurrentTest;
    private boolean mReadStoragePermissionGranted;
    private Uri mFileUri;
    private String mDirPath;
    private LoaderManager mLoaderManager;
    private boolean mWaitForLoad;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == READ_STORAGE_PERMISSION)
            mReadStoragePermissionGranted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CHOOSE_FILE_FROM_STORAGE && resultCode == Activity.RESULT_OK) {
            Storage.write(Start.this, Storage.TEST_FILE_URI, data.getData().toString());
            Uri uri = data.getData();

            initLoader(uri);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        mLoaderManager = getSupportLoaderManager();
        checkPermissions();

        String uriFromStorage = Storage.read(Start.this, Storage.TEST_FILE_URI);
        if (uriFromStorage != null) {
            mFileUri = Uri.parse(uriFromStorage);
            initLoader(mFileUri);
        }
    }

    @OnClick(R.id.Start_button_start)
    public void onClickStart() {

        if (mReadStoragePermissionGranted) {

            if (mLoaderManager.hasRunningLoaders()) {
                mWaitForLoad = true;
                ProgressWindow.show(Start.this);
            } else {

                if (mCurrentTest != null) {
                    Intent startQuestionsActivity = new Intent(Start.this, Questions.class);
                    startQuestionsActivity.putExtra(TestModel.NAME, mCurrentTest);

                    if(mDirPath != null)
                        startQuestionsActivity.putExtra(Questions.DIR_PATH_NAME,mDirPath);
                    startActivity(startQuestionsActivity);

                } else
                    ErrorWindow.show(Start.this, getString(R.string.window_title_attention), getString(R.string.window_content_not_choosed_file));
            }
        } else
            ErrorWindow.show(Start.this, getString(R.string.window_title_attention), getString(R.string.window_content_read_permission_not_granted));
    }

    @OnClick(R.id.Start_button_load)
    public void onClickLoad() {
        if (mReadStoragePermissionGranted) {
            Intent chooseFile = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            chooseFile.addCategory(Intent.CATEGORY_OPENABLE);

            chooseFile.setType("*/*");
            startActivityForResult(chooseFile, CHOOSE_FILE_FROM_STORAGE);
        } else
            ErrorWindow.show(Start.this, getString(R.string.window_title_attention), getString(R.string.window_content_read_permission_not_granted));


    }

    private String rootPath(){
        String docUri = DocumentsContract.getDocumentId(mFileUri);
        docUri =  docUri.split(":")[1];
        docUri = docUri.substring(0,docUri.lastIndexOf("/"));
        return docUri;
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mReadStoragePermissionGranted = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

            if (!mReadStoragePermissionGranted)
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.MANAGE_DOCUMENTS,Manifest.permission.INTERNET}, READ_STORAGE_PERMISSION);
        }
    }

    private void initLoader(Uri fileUri) {
        mFileUri = fileUri;
        mDirPath = rootPath();
        if (mFileUri != null && mReadStoragePermissionGranted) {
            if (!mLoaderManager.hasRunningLoaders())
                mLoaderManager.initLoader(LOADER_MANAGER, null, this);
            else
                mLoaderManager.restartLoader(LOADER_MANAGER, null, this);
        }
    }


    @Override
    public android.support.v4.content.Loader<TestModel> onCreateLoader(int id, Bundle args) {
        return new JsonLoader(Start.this,mFileUri);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<TestModel> loader, TestModel data) {
        mCurrentTest = data;

        if(mWaitForLoad){
            ProgressWindow.dismiss();
            mWaitForLoad = false;
            onClickStart();
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<TestModel> loader) {
        mCurrentTest = null;
    }
}


