package org.o7planning.demoexamen;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewUtils {
    public static final String RAW_VIDEO_SAMPLE = "clip";
    public static final String LOG_TAG= "PlayVideo";

    public static void playRawVideo(Context context, VideoView videoView, String resName)  {
        try {
            // ID of video file.
            int id = VideoViewUtils.getRawResIdByName( context, resName);

            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + id);
            Log.i(LOG_TAG, "Video URI: "+ uri);

            videoView.setVideoURI(uri);
            videoView.requestFocus();

        } catch (Exception e) {
            Log.e(LOG_TAG, "Error Play Raw Video: "+e.getMessage());
            Toast.makeText(context,"Error Play Raw Video: "+ e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    public static int getRawResIdByName(Context context, String resName) {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "raw", pkgName);

        Log.i(LOG_TAG, "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }
}
