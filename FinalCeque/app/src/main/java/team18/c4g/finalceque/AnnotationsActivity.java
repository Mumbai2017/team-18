package team18.c4g.finalceque;


        import android.app.Dialog;
        import android.content.ActivityNotFoundException;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.Window;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.android.volley.DefaultRetryPolicy;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.google.android.youtube.player.YouTubeBaseActivity;
        import com.google.android.youtube.player.YouTubeInitializationResult;
        import com.google.android.youtube.player.YouTubePlayer;
        import com.google.android.youtube.player.YouTubePlayer.Provider;
        import com.google.android.youtube.player.YouTubePlayerView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;

public class AnnotationsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    MyPlayerStateChangeListener playerStateChangeListener;
    MyPlaybackEventListener playbackEventListener;
    public Context context;
    private static YouTubePlayer player;
    RecyclerView mRecyclerView;
    RequestQueue mRequestQueue;
    ArrayList<String> seek_values,comments;
    SeekAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        mRequestQueue = Volley.newRequestQueue(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.seek_recyclerview);
        comments = new ArrayList<String>();
        seek_values = new ArrayList<String>();
        adapter = new SeekAdapter(context,seek_values,comments);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        final EditText seekToText = (EditText) findViewById(R.id.seek_to_text);
        Button seekToButton = (Button) findViewById(R.id.seek_to_button);
        seekToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int skipToSecs = Integer.valueOf(seekToText.getText().toString());
                player.seekToMillis(skipToSecs * 1000);
            }
        });

        Button upload  = (Button) findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String url = "http://cfg.hphost.in/apis/DataApi.html";
              try {
                  Intent i = new Intent("android.intent.action.MAIN");
                  i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                  i.addCategory("android.intent.category.LAUNCHER");
                  i.setData(Uri.parse(url));
                  startActivity(i);
              }
              catch(ActivityNotFoundException e) {
                  // Chrome is not installed
                  Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                  startActivity(i);
              }
          }
      });

        //RequestQueue queue = Volley.newRequestQueue(this); // this = context
        String url = "http://cfg.hphost.in/apis/getSeekValues.php";
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show();
                        Log.d("today",response.toString());
                        parseJson(response);
                        adapter.update();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
// error
                        Log.d("Error.Response", "error");
                    }
                }
        )


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "1");

                Log.d("Values", "");
                return params;

            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(postRequest);

    }
    public void seek(String val){
        int skipToSecs = Integer.valueOf(val);
        player.seekToMillis(skipToSecs * 1000);
    }
    public void parseJson(JSONObject response){
        try{
            JSONArray arr = response.getJSONArray("seek_values");
            for(int i=0;i<arr.length();i++){
                JSONObject jsonObject = arr.getJSONObject(i);
                seek_values.add(jsonObject.getString("timepoint"));
                comments.add(jsonObject.getString("msg"));
                Log.d("now",seek_values.get(i).toString());
            }
        }catch(JSONException e){

        }
    }
    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        if (!wasRestored) {
            player.cueVideo("fhWaJi1Hsfo"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {

        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
    public void showMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().
            showMessage("Playing");
        }

        @Override
        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().
            showMessage("Paused");
        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.
            showMessage("Stopped");
        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }

        @Override
        public void onSeekTo(int i) {
            showMessage(i+"");
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
        }

        @Override
        public void onLoaded(String s) {
            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
        }

        @Override
        public void onAdStarted() {
            // Called when playback of an advertisement starts.
        }

        @Override
        public void onVideoStarted() {
            // Called when playback of the video starts.
        }

        @Override
        public void onVideoEnded() {
            // Called when the video reaches its end.
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
        }
    }
}
