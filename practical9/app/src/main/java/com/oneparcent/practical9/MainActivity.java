package com.oneparcent.practical9;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int RequestPermissionCode = 1;
    private final static int VIDEO_CAPTURE_CODE = 101;

    Button btnRecordVideo, btnPlayVideo, btnRecordAudio, btnStopRecordingAudio, btnPlayAudio, btnStopPlayingAudio;
    Uri videoUri = null;
    String audioSavePathInDevice = null;
    MediaRecorder audioRecorder;
    MediaPlayer audioPlayer;
    Random random;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRecordVideo = findViewById(R.id.recordVideo);
        btnPlayVideo = findViewById(R.id.playVideo);
        btnRecordAudio = findViewById(R.id.recordAudio);
        btnStopRecordingAudio = findViewById(R.id.stopRecordAudio);
        btnPlayAudio = findViewById(R.id.playAudio);
        btnStopPlayingAudio = findViewById(R.id.stopPlayingAudio);
        btnPlayVideo.setEnabled(false);
        btnStopRecordingAudio.setEnabled(false);
        btnPlayAudio.setEnabled(false);
        random = new Random();
        btnRecordVideo.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, VIDEO_CAPTURE_CODE);
            }
        });

        btnPlayVideo.setOnClickListener(v -> {
            if (videoUri != null) {
                Intent intent = new Intent(this, VideoPlayer.class);
                intent.putExtra("videoUri", videoUri.toString());
                startActivity(intent);
            }
        });

        btnRecordAudio.setOnClickListener(v -> {
            if (checkPermission()) {
                audioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                createRandomAudioFileName(5) + "AudioRecording.mp3";
                makeAudioRecorder();
                try {
                    audioRecorder.prepare();
                    audioRecorder.start();
                    btnRecordAudio.setEnabled(false);
                    btnStopRecordingAudio.setEnabled(true);
                    Toast.makeText(this, "Recording started...",
                            Toast.LENGTH_SHORT).show();
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                requestPermission();
            }
        });

        btnStopRecordingAudio.setOnClickListener(v -> {
            audioRecorder.stop();
            btnRecordAudio.setEnabled(true);
            btnStopRecordingAudio.setEnabled(false);
            btnPlayAudio.setEnabled(true);
            btnStopPlayingAudio.setEnabled(false);
            Toast.makeText(this, "Audio Recorded Successfully!!",
                    Toast.LENGTH_SHORT).show();
        });

        btnPlayAudio.setOnClickListener(v -> {
            btnRecordAudio.setEnabled(false);
            btnStopRecordingAudio.setEnabled(false);
            btnStopPlayingAudio.setEnabled(true);
            audioPlayer = new MediaPlayer();
            try {
                audioPlayer.setDataSource(audioSavePathInDevice);
                audioPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            audioPlayer.start();
            Toast.makeText(this, "Playing audio...", Toast.LENGTH_SHORT).show();
        });

        btnStopPlayingAudio.setOnClickListener(v -> {
            btnStopRecordingAudio.setEnabled(false);
            btnRecordAudio.setEnabled(true);
            btnStopPlayingAudio.setEnabled(false);
            btnPlayAudio.setEnabled(true);
            if (audioPlayer != null) {
                audioPlayer.stop();
                audioPlayer.release();
                makeAudioRecorder();
            }
        });
    }

    public void makeAudioRecorder() {
        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        audioRecorder.setOutputFile(audioSavePathInDevice);
    }
    public String createRandomAudioFileName(int string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        int i = 0;
        while (i < string) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));
            i++;
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_CAPTURE_CODE && resultCode == RESULT_OK) {
            videoUri = data.getData();
            if (videoUri != null) {
                btnPlayVideo.setEnabled(true);
            }
        }
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO},
                RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RequestPermissionCode) {
            if (grantResults.length > 0) {
                boolean storagePermission = grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED;
                boolean recordPermission = grantResults[1] ==
                        PackageManager.PERMISSION_GRANTED;
                if (!storagePermission && !recordPermission) {
                    Toast.makeText(MainActivity.this, "Permission Denied",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }
}