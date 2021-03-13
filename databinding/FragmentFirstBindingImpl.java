package com.oshaev.artclub.databinding;
import com.oshaev.artclub.R;
import com.oshaev.artclub.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentFirstBindingImpl extends FragmentFirstBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 2);
        sViewsWithIds.put(R.id.tabs, 3);
        sViewsWithIds.put(R.id.viewpager_content, 4);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.ImageButton mboundView1;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mButtonHandlerOnSettingsClickedAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentFirstBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private FragmentFirstBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.tabs.TabLayout) bindings[3]
            , (androidx.appcompat.widget.Toolbar) bindings[2]
            , (androidx.viewpager.widget.ViewPager) bindings[4]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.ImageButton) bindings[1];
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
        if (BR.buttonHandler == variableId) {
            setButtonHandler((com.oshaev.artclub.ui.notifications.FirstFragment.FirstFragmentButtonsHandler) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setButtonHandler(@Nullable com.oshaev.artclub.ui.notifications.FirstFragment.FirstFragmentButtonsHandler ButtonHandler) {
        this.mButtonHandler = ButtonHandler;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.buttonHandler);
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
        android.view.View.OnClickListener buttonHandlerOnSettingsClickedAndroidViewViewOnClickListener = null;
        com.oshaev.artclub.ui.notifications.FirstFragment.FirstFragmentButtonsHandler buttonHandler = mButtonHandler;

        if ((dirtyFlags & 0x3L) != 0) {



                if (buttonHandler != null) {
                    // read buttonHandler::onSettingsClicked
                    buttonHandlerOnSettingsClickedAndroidViewViewOnClickListener = (((mButtonHandlerOnSettingsClickedAndroidViewViewOnClickListener == null) ? (mButtonHandlerOnSettingsClickedAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mButtonHandlerOnSettingsClickedAndroidViewViewOnClickListener).setValue(buttonHandler));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.mboundView1.setOnClickListener(buttonHandlerOnSettingsClickedAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.oshaev.artclub.ui.notifications.FirstFragment.FirstFragmentButtonsHandler value;
        public OnClickListenerImpl setValue(com.oshaev.artclub.ui.notifications.FirstFragment.FirstFragmentButtonsHandler value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onSettingsClicked(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): buttonHandler
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}