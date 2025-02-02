package com.oshaev.artclub.databinding;
import com.oshaev.artclub.R;
import com.oshaev.artclub.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class PostItemLayoutBindingImpl extends PostItemLayoutBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.postImagePreview, 3);
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public PostItemLayoutBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private PostItemLayoutBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.postTextView.setTag(null);
        this.postTitleTextView.setTag(null);
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
        if (BR.post == variableId) {
            setPost((com.oshaev.artclub.ui.home.NewsItem) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPost(@Nullable com.oshaev.artclub.ui.home.NewsItem Post) {
        this.mPost = Post;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.post);
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
        java.lang.String postPaper = null;
        java.lang.String postTitle = null;
        com.oshaev.artclub.ui.home.NewsItem post = mPost;

        if ((dirtyFlags & 0x3L) != 0) {



                if (post != null) {
                    // read post.paper
                    postPaper = post.getPaper();
                    // read post.title
                    postTitle = post.getTitle();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.postTextView, postPaper);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.postTitleTextView, postTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): post
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}