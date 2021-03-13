package com.oshaev.artclub;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.oshaev.artclub.databinding.ActivityAddSocialEventBindingImpl;
import com.oshaev.artclub.databinding.ActivityCreatePerformanceBindingImpl;
import com.oshaev.artclub.databinding.ActivityUserProfilePublicBindingImpl;
import com.oshaev.artclub.databinding.ActivityUserSettingsBindingImpl;
import com.oshaev.artclub.databinding.ContentUserProfilePublicBindingImpl;
import com.oshaev.artclub.databinding.FragmentFirstBindingImpl;
import com.oshaev.artclub.databinding.FragmentHomeBindingImpl;
import com.oshaev.artclub.databinding.FragmentUserInfoBindingImpl;
import com.oshaev.artclub.databinding.PostItemLayoutBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYADDSOCIALEVENT = 1;

  private static final int LAYOUT_ACTIVITYCREATEPERFORMANCE = 2;

  private static final int LAYOUT_ACTIVITYUSERPROFILEPUBLIC = 3;

  private static final int LAYOUT_ACTIVITYUSERSETTINGS = 4;

  private static final int LAYOUT_CONTENTUSERPROFILEPUBLIC = 5;

  private static final int LAYOUT_FRAGMENTFIRST = 6;

  private static final int LAYOUT_FRAGMENTHOME = 7;

  private static final int LAYOUT_FRAGMENTUSERINFO = 8;

  private static final int LAYOUT_POSTITEMLAYOUT = 9;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(9);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.activity_add_social_event, LAYOUT_ACTIVITYADDSOCIALEVENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.activity_create_performance, LAYOUT_ACTIVITYCREATEPERFORMANCE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.activity_user_profile_public, LAYOUT_ACTIVITYUSERPROFILEPUBLIC);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.activity_user_settings, LAYOUT_ACTIVITYUSERSETTINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.content_user_profile_public, LAYOUT_CONTENTUSERPROFILEPUBLIC);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.fragment_first, LAYOUT_FRAGMENTFIRST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.fragment_user_info, LAYOUT_FRAGMENTUSERINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.oshaev.artclub.R.layout.post_item_layout, LAYOUT_POSTITEMLAYOUT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYADDSOCIALEVENT: {
          if ("layout/activity_add_social_event_0".equals(tag)) {
            return new ActivityAddSocialEventBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_add_social_event is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCREATEPERFORMANCE: {
          if ("layout/activity_create_performance_0".equals(tag)) {
            return new ActivityCreatePerformanceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_create_performance is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYUSERPROFILEPUBLIC: {
          if ("layout/activity_user_profile_public_0".equals(tag)) {
            return new ActivityUserProfilePublicBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_user_profile_public is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYUSERSETTINGS: {
          if ("layout/activity_user_settings_0".equals(tag)) {
            return new ActivityUserSettingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_user_settings is invalid. Received: " + tag);
        }
        case  LAYOUT_CONTENTUSERPROFILEPUBLIC: {
          if ("layout/content_user_profile_public_0".equals(tag)) {
            return new ContentUserProfilePublicBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for content_user_profile_public is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFIRST: {
          if ("layout/fragment_first_0".equals(tag)) {
            return new FragmentFirstBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_first is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTUSERINFO: {
          if ("layout/fragment_user_info_0".equals(tag)) {
            return new FragmentUserInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_user_info is invalid. Received: " + tag);
        }
        case  LAYOUT_POSTITEMLAYOUT: {
          if ("layout/post_item_layout_0".equals(tag)) {
            return new PostItemLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for post_item_layout is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(11);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "buttonHandler");
      sKeys.put(2, "buttonsHandler");
      sKeys.put(3, "exp");
      sKeys.put(4, "impressive");
      sKeys.put(5, "level");
      sKeys.put(6, "maxExp");
      sKeys.put(7, "post");
      sKeys.put(8, "progress");
      sKeys.put(9, "progressMax");
      sKeys.put(10, "user");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(9);

    static {
      sKeys.put("layout/activity_add_social_event_0", com.oshaev.artclub.R.layout.activity_add_social_event);
      sKeys.put("layout/activity_create_performance_0", com.oshaev.artclub.R.layout.activity_create_performance);
      sKeys.put("layout/activity_user_profile_public_0", com.oshaev.artclub.R.layout.activity_user_profile_public);
      sKeys.put("layout/activity_user_settings_0", com.oshaev.artclub.R.layout.activity_user_settings);
      sKeys.put("layout/content_user_profile_public_0", com.oshaev.artclub.R.layout.content_user_profile_public);
      sKeys.put("layout/fragment_first_0", com.oshaev.artclub.R.layout.fragment_first);
      sKeys.put("layout/fragment_home_0", com.oshaev.artclub.R.layout.fragment_home);
      sKeys.put("layout/fragment_user_info_0", com.oshaev.artclub.R.layout.fragment_user_info);
      sKeys.put("layout/post_item_layout_0", com.oshaev.artclub.R.layout.post_item_layout);
    }
  }
}
