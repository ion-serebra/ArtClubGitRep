package com.oshaev.artclub.databinding;
import com.oshaev.artclub.R;
import com.oshaev.artclub.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.app_bar, 2);
        sViewsWithIds.put(R.id.toolbar_layout, 3);
        sViewsWithIds.put(R.id.toolbar, 4);
        sViewsWithIds.put(R.id.postsNestedScrollView, 5);
        sViewsWithIds.put(R.id.postsRecyclerView, 6);
    }
    // views
    // variables
    // values
    // listeners
    private OnClickListenerImpl mButtonHandlerOnFabClickedAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.appbar.AppBarLayout) bindings[2]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[1]
            , (androidx.core.widget.NestedScrollView) bindings[5]
            , (androidx.recyclerview.widget.RecyclerView) bindings[6]
            , (androidx.appcompat.widget.Toolbar) bindings[4]
            , (com.google.android.material.appbar.CollapsingToolbarLayout) bindings[3]
            );
        this.collapsingToolbar.setTag(null);
        this.fab.setTag(null);
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
            setButtonHandler((com.oshaev.artclub.ui.home.HomeFragment.HomeButtonHandler) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setButtonHandler(@Nullable com.oshaev.artclub.ui.home.HomeFragment.HomeButtonHandler ButtonHandler) {
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
        com.oshaev.artclub.ui.home.HomeFragment.HomeButtonHandler buttonHandler = mButtonHandler;
        android.view.View.OnClickListener buttonHandlerOnFabClickedAndroidViewViewOnClickListener = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (buttonHandler != null) {
                    // read buttonHandler::onFabClicked
                    buttonHandlerOnFabClickedAndroidViewViewOnClickListener = (((mButtonHandlerOnFabClickedAndroidViewViewOnClickListener == null) ? (mButtonHandlerOnFabClickedAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mButtonHandlerOnFabClickedAndroidViewViewOnClickListener).setValue(buttonHandler));
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.fab.setOnClickListener(buttonHandlerOnFabClickedAndroidViewViewOnClickListener);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.oshaev.artclub.ui.home.HomeFragment.HomeButtonHandler value;
        public OnClickListenerImpl setValue(com.oshaev.artclub.ui.home.HomeFragment.HomeButtonHandler value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onFabClicked(arg0); 
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