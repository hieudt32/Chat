package vn.iotech.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import vn.iotech.Default;
import vn.iotech.R;
import vn.iotech.base.log.Logger;
import vn.iotech.utils.ActivityUtils;

/**
 * BaseFragment
 * Created by akai on 12/21/2017.
 */

public abstract class BaseFragment extends Fragment {

    private static final boolean DEFAULT_START_ON_ANIMATION_ENDED = false;

    protected View mRootView;
    protected int mAnimIn = Default.ANIM_NONE;
    protected int mAnimOut = Default.ANIM_NONE;
    protected boolean mIsStarted = false;
    protected boolean mStartOnAnimationEnded = DEFAULT_START_ON_ANIMATION_ENDED;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mRootView);
        mRootView.setClickable(true);
        return mRootView;
    }

    public void addChildFragment(Fragment fragment, int frameId, boolean addToBackStack,
                                 String tag) {
        ActivityUtils.addChildFragment(getChildFragmentManager(), fragment,
                frameId, addToBackStack, tag);
    }


    /**
     * Set out animation
     */
    public BaseFragment setAnimOut(int animOut) {
        mAnimOut = animOut;
        return this;
    }

    /**
     * Set enter animation
     */
    public BaseFragment setAnimIn(int animIn) {
        mAnimIn = animIn;
        return this;
    }

    protected abstract int getLayoutId();

    protected abstract void startPresent();

    private static long getNextAnimationDuration(Fragment fragment, long defValue) {
        try {
            // Attempt to get the resource ID of the next animation that
            // will be applied to the given fragment.
            Field nextAnimField = Fragment.class.getDeclaredField("mNextAnim");
            nextAnimField.setAccessible(true);
            int nextAnimResource = nextAnimField.getInt(fragment);
            Animation nextAnim = AnimationUtils.loadAnimation(fragment.getActivity(), nextAnimResource);

            // ...and if it can be loaded, return that animation's duration
            return (nextAnim == null) ? defValue : nextAnim.getDuration();
        } catch (NoSuchFieldException | IllegalAccessException | Resources.NotFoundException ex) {
            Logger.e("Error", "Unable to load next animation from parent.", ex);
            return defValue;
        }
    }

    protected abstract boolean needTranslationAnimation();

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (!needTranslationAnimation()) {
            return null;
        }

        Animation anim;
        if (enter) {
            anim = AnimationUtils.loadAnimation(getActivity(), mAnimIn);
        } else {
            anim = AnimationUtils.loadAnimation(getActivity(), mAnimOut);
        }

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (mRootView != null) {
                    mRootView.setLayerType(View.LAYER_TYPE_NONE, null);
                }

                if (mStartOnAnimationEnded && !mIsStarted) {
                    startPresent();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // on Animation repeated
            }

            @Override
            public void onAnimationStart(Animation animation) {
                // onAnimationStart
            }
        });

        // Apply the workaround only if this is a child fragment, and the parent
        // is being removed.
        final Fragment parent = getParentFragment();
        if (!enter && parent != null && parent.isRemoving()) {
            // This is a workaround for the bug where child fragments disappear when
            // the parent is removed (as all children are first removed from the parent)
            // See https://code.google.com/p/android/issues/detail?id=55228
            Animation doNothingAnim = new AlphaAnimation(1, 1);
            doNothingAnim.setDuration(getNextAnimationDuration(parent, getContext().getResources()
                    .getInteger(R.integer.anim_duration_fast)));
            return doNothingAnim;
        }
        return anim;
    }

}
