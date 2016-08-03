package com.example.chenkun.preferencesactivitydemo.com.example.chenkun.preferences;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.chenkun.preferencesactivitydemo.R;
import com.example.chenkun.preferencesactivitydemo.com.example.chenkun.util.ToastUtil;
import com.example.chenkun.preferencesactivitydemo.com.example.chenkun.util.TwoStatePreference;

/**
 * custom preference
 */
public class PreferenceCustom extends TwoStatePreference {
    private TextView mSummary;
    private Button mButton;
    private ToggleButton mToggleButton;


    public PreferenceCustom(Context context) {
        this(context, null);
    }

    public PreferenceCustom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PreferenceCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.layout_custom, parent, false);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        mSummary = (TextView) view.findViewById(R.id.text_view);
        mButton = (Button) view.findViewById(R.id.button);
        mToggleButton = (ToggleButton) view.findViewById(R.id.toggle_button);
        mToggleButton.setChecked(isChecked());
        mSummary.setText(isChecked()
                ? "congratulations you have chosen me."
                : "Ha ha, you haven't picked me");
        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (!callChangeListener(checked)) {
                    compoundButton.setChecked(checked);
                    return;
                }
                setChecked(checked);
                mSummary.setText(isChecked()
                        ? "congratulations you have chosen me."
                        : "Ha ha, you haven't picked me");
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(PreferenceCustom.this.getContext(), "You were fooled, stupid human");
            }
        });
    }

    @Override
    public void setSummary(CharSequence summary) {
        super.setSummary(summary);
        mSummary.setText(!TextUtils.isEmpty(summary) ? summary : "");
    }

    @Override
    protected void onClick() {
        super.onClick();
        ToastUtil.showToast(PreferenceCustom.this.getContext(), "Item Click");
    }
}
