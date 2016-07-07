package io.astro.lib;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author skeswa
 */
public class LinearLayoutComponent extends NativeComponent {
    public static Attribute<Boolean> horizontal = Attribute.create(Boolean.class, Boolean.FALSE);

    private LinearLayout layout;

    @Override
    public void onMount(Context context) {
        layout = new LinearLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onUnmount() {
        layout = null;
    }

    @Override
    protected void onAttributeValueChanged(Attribute attribute, Object value) {
        if (attribute == horizontal) {
            layout.setOrientation(Boolean.TRUE.equals(value) ? LinearLayout.HORIZONTAL :
                LinearLayout.VERTICAL);
        }
    }

    @Override
    public void insertChild(View view, int index) {
        layout.addView(view, index);
    }

    @Override
    public void removeChild(int index) {
        layout.removeViewAt(index);
    }

    @Override
    public void moveChild(int oldIndex, int newIndex) {
        final View child = layout.getChildAt(oldIndex);
        layout.removeViewAt(oldIndex);
        layout.addView(child, newIndex);
    }

    @Override
    public View getView() {
        return layout;
    }
}
