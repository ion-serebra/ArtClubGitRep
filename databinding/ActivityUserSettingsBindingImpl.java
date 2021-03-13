package com.oshaev.artclub.databinding;
import com.oshaev.artclub.R;
import com.oshaev.artclub.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityUserSettingsBindingImpl extends ActivityUserSettingsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.Button mboundView1;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mButtonsHandlerOnLogOutClickedAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public ActivityUserSettingsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ActivityUserSettingsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.appcompat.widget.Toolbar) bindings[2]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.Button) bindings[1];
        this.mboundView1.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.buttonsHandler == variableId) {
            setButtonsHandler((com.oshaev.artclub.ui.notifications.UserSettingsActivity.UserSettingsButtonsHandler) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setButtonsHandler(@Nullable com.oshaev.artclub.ui.notifications.UserSettingsActivity.UserSettingsButtonsHandler ButtonsHandler) {
        this.mButtonsHandler = ButtonsHandler;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.buttonsHandler);
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
        android.view.View.OnClickListener buttonsHandlerOnLogOutClickedAndroidViewViewOnClickListener = null;
        com.oshaev.artclub.ui.notifications.UserSettingsActivity.UserSettingsButtonsHandler buttonsHandler = mButtonsHandler;

        if ((dirtyFlags & 0x3L) != 0) {



                if (buttonsHandler != null) {
                    // read buttonsHandler::onLogOutClicked
                    buttonsHandlerOnLogOutClickedAndroidViewViewOnClickListener = (((mButtonsHandlerOnLogOutClickedAndroidViewViewOnClickListener == null) ? (mButtonsHandlerOnLogOutClickedAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mButtonsHandlerOnLogOutClickedAndroidViewViewOnClickListener).setValue(buttonsHandler));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.mboundView1.setOnClickListener(buttonsHandlerOnLogOutClickedAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.oshaev.artclub.ui.notifications.UserSettingsActivity.UserSettingsButtonsHandler value;
        public OnClickListenerImpl setValue(com.oshaev.artclub.ui.notifications.UserSettingsActivity.UserSettingsButtonsHandler value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onLogOutClicked(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): buttonsHandler
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}