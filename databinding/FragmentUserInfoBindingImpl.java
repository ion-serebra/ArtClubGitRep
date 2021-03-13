package com.oshaev.artclub.databinding;
import com.oshaev.artclub.R;
import com.oshaev.artclub.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentUserInfoBindingImpl extends FragmentUserInfoBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.userAvatar, 6);
        sViewsWithIds.put(R.id.userInfoLayout, 7);
        sViewsWithIds.put(R.id.userName, 8);
        sViewsWithIds.put(R.id.userSurname, 9);
        sViewsWithIds.put(R.id.userGroup, 10);
        sViewsWithIds.put(R.id.buttonsLayout, 11);
        sViewsWithIds.put(R.id.completedTasksButton, 12);
        sViewsWithIds.put(R.id.superUsersList, 13);
        sViewsWithIds.put(R.id.generalMailingButton, 14);
        sViewsWithIds.put(R.id.tasksRecyclerView, 15);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentUserInfoBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private FragmentUserInfoBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[14]
            , (android.widget.ProgressBar) bindings[2]
            , (android.widget.Button) bindings[13]
            , (androidx.recyclerview.widget.RecyclerView) bindings[15]
            , (android.widget.ImageButton) bindings[6]
            , (android.widget.TextView) bindings[10]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.progressBar.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x80L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.progress == variableId) {
            setProgress((java.lang.Integer) variable);
        }
        else if (BR.progressMax == variableId) {
            setProgressMax((java.lang.Integer) variable);
        }
        else if (BR.exp == variableId) {
            setExp((java.lang.String) variable);
        }
        else if (BR.buttonsHandler == variableId) {
            setButtonsHandler((com.oshaev.artclub.ui.notifications.ProfileFragment.ProfileFragmentButtonsHandler) variable);
        }
        else if (BR.user == variableId) {
            setUser((com.oshaev.artclub.User) variable);
        }
        else if (BR.maxExp == variableId) {
            setMaxExp((java.lang.String) variable);
        }
        else if (BR.level == variableId) {
            setLevel((java.lang.String) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setProgress(@Nullable java.lang.Integer Progress) {
        this.mProgress = Progress;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }
    public void setProgressMax(@Nullable java.lang.Integer ProgressMax) {
        this.mProgressMax = ProgressMax;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.progressMax);
        super.requestRebind();
    }
    public void setExp(@Nullable java.lang.String Exp) {
        this.mExp = Exp;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.exp);
        super.requestRebind();
    }
    public void setButtonsHandler(@Nullable com.oshaev.artclub.ui.notifications.ProfileFragment.ProfileFragmentButtonsHandler ButtonsHandler) {
        this.mButtonsHandler = ButtonsHandler;
    }
    public void setUser(@Nullable com.oshaev.artclub.User User) {
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }
    public void setMaxExp(@Nullable java.lang.String MaxExp) {
        this.mMaxExp = MaxExp;
    }
    public void setLevel(@Nullable java.lang.String Level) {
        this.mLevel = Level;
        synchronized(this) {
            mDirtyFlags |= 0x40L;
        }
        notifyPropertyChanged(BR.level);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.Integer progress = mProgress;
        java.lang.String stringValueOfUserGetCoins = null;
        java.lang.Integer progressMax = mProgressMax;
        java.lang.String exp = mExp;
        int androidxDatabindingViewDataBindingSafeUnboxProgressMax = 0;
        java.lang.String progressToString = null;
        com.oshaev.artclub.User user = mUser;
        java.lang.String progressMaxToString = null;
        java.lang.String progressToStringChar = null;
        java.lang.String progressToStringCharProgressMaxToString = null;
        int userGetCoins = 0;
        java.lang.String level = mLevel;
        int androidxDatabindingViewDataBindingSafeUnboxProgress = 0;

        if ((dirtyFlags & 0x83L) != 0) {



                if (progress != null) {
                    // read progress.toString()
                    progressToString = progress.toString();
                }
                if (progressMax != null) {
                    // read progressMax.toString()
                    progressMaxToString = progressMax.toString();
                }


                // read (progress.toString()) + ('/')
                progressToStringChar = (progressToString) + ('/');


                // read ((progress.toString()) + ('/')) + (progressMax.toString())
                progressToStringCharProgressMaxToString = (progressToStringChar) + (progressMaxToString);
            if ((dirtyFlags & 0x81L) != 0) {

                    // read androidx.databinding.ViewDataBinding.safeUnbox(progress)
                    androidxDatabindingViewDataBindingSafeUnboxProgress = androidx.databinding.ViewDataBinding.safeUnbox(progress);
            }
            if ((dirtyFlags & 0x82L) != 0) {

                    // read androidx.databinding.ViewDataBinding.safeUnbox(progressMax)
                    androidxDatabindingViewDataBindingSafeUnboxProgressMax = androidx.databinding.ViewDataBinding.safeUnbox(progressMax);
            }
        }
        if ((dirtyFlags & 0x84L) != 0) {
        }
        if ((dirtyFlags & 0x90L) != 0) {



                if (user != null) {
                    // read user.getCoins
                    userGetCoins = user.getCoins();
                }


                // read String.valueOf(user.getCoins)
                stringValueOfUserGetCoins = java.lang.String.valueOf(userGetCoins);
        }
        if ((dirtyFlags & 0xc0L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x84L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, exp);
        }
        if ((dirtyFlags & 0x83L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, progressToStringCharProgressMaxToString);
        }
        if ((dirtyFlags & 0xc0L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, level);
        }
        if ((dirtyFlags & 0x90L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, stringValueOfUserGetCoins);
        }
        if ((dirtyFlags & 0x82L) != 0) {
            // api target 1

            this.progressBar.setMax(androidxDatabindingViewDataBindingSafeUnboxProgressMax);
        }
        if ((dirtyFlags & 0x81L) != 0) {
            // api target 1

            this.progressBar.setProgress(androidxDatabindingViewDataBindingSafeUnboxProgress);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): progress
        flag 1 (0x2L): progressMax
        flag 2 (0x3L): exp
        flag 3 (0x4L): buttonsHandler
        flag 4 (0x5L): user
        flag 5 (0x6L): maxExp
        flag 6 (0x7L): level
        flag 7 (0x8L): null
    flag mapping end*/
    //end
}