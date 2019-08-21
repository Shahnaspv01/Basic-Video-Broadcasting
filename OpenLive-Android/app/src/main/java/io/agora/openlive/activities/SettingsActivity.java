package io.agora.openlive.activities;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.agora.openlive.Constants;
import io.agora.openlive.R;
import io.agora.openlive.ui.ResolutionAdapter;

import static io.agora.openlive.Constants.PREF_RESOLUTION_IDX;

public class SettingsActivity extends BaseActivity {
    private static final int DEFAULT_SPAN = 3;

    private TextView mVideoStatCheck;

    private int mItemPadding;
    private ResolutionAdapter mResolutionAdapter;
    private RecyclerView.ItemDecoration mItemDecoration =
            new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                           @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.top = mItemPadding;
                    outRect.bottom = mItemPadding;
                    outRect.left = mItemPadding;
                    outRect.right = mItemPadding;

                    int pos = parent.getChildAdapterPosition(view);
                    if (pos < DEFAULT_SPAN) {
                        outRect.top = 0;
                    }
                    if (pos % DEFAULT_SPAN == 0) outRect.left = 0;
                    else if (pos % DEFAULT_SPAN == (DEFAULT_SPAN - 1)) outRect.right = 0;
                }
            };

    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mPref = PreferenceManager.getDefaultSharedPreferences(this);
        initUI();
    }

    private void initUI() {
        RecyclerView resolutionList = findViewById(R.id.resolution_list);
        resolutionList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, DEFAULT_SPAN);
        resolutionList.setLayoutManager(layoutManager);

        mResolutionAdapter = new ResolutionAdapter(this, config().getVideoDimenIndex());
        resolutionList.setAdapter(mResolutionAdapter);
        resolutionList.addItemDecoration(mItemDecoration);

        mItemPadding = getResources().getDimensionPixelSize(R.dimen.setting_resolution_item_padding);

        mVideoStatCheck = findViewById(R.id.setting_stats_checkbox);
        mVideoStatCheck.setActivated(config().ifShowVideoStats());
    }

    @Override
    protected void onGlobalLayoutCompleted() {
        // Adjust for status bar height
        RelativeLayout titleLayout = findViewById(R.id.setting_title_layout);
        RelativeLayout.LayoutParams params =
                (RelativeLayout.LayoutParams) titleLayout.getLayoutParams();
        params.height += mStatusBarHeight;
        titleLayout.setLayoutParams(params);
    }

    @Override
    public void onBackPressed() {
        onBackArrowPressed(null);
    }

    public void onBackArrowPressed(View view) {
        saveResolution();
        saveShowVideoStats();
        finish();
    }

    private void saveResolution() {
        int profileIndex = mResolutionAdapter.getSelected();
        config().setVideoDimenIndex(profileIndex);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_RESOLUTION_IDX, profileIndex);
        editor.apply();
    }

    private void saveShowVideoStats() {
        config().setIfShowVideoStats(mVideoStatCheck.isActivated());
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(Constants.PREF_SHOW_VIDEO_STATISTICS, mVideoStatCheck.isActivated());
        editor.apply();
    }

    public void onStatsChecked(View view) {
        view.setActivated(!view.isActivated());
    }
}