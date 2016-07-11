package io.astro.lib;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author skeswa
 */
public class ColorBox extends NativeComponent {
    public static Attribute<Integer> color = Attribute.create(Integer.class, Color.BLUE);
    public static Attribute<Integer> width = Attribute.create(Integer.class, 0);
    public static Attribute<Integer> height = Attribute.create(Integer.class, 0);
    public static Attribute<View.OnClickListener> onClick = Attribute.create(View.OnClickListener.class);

    private View view;

    @Override
    protected void onAttributeValueChanged(Attribute attribute, Object value) {
        if (attribute == color) {
            view.setBackgroundColor((Integer) value);
        } else if (attribute == width) {
//            view.getLayoutParams().width = (Integer) value;
        } else if (attribute == height) {
//            view.getLayoutParams().height = (Integer) value;
        }
    }

    @Override
    public void onMount(Context context) {
        view = new View(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(0, 0));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final View.OnClickListener listener = valueOf(onClick);
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });
    }

    @Override
    public void insertChild(View view, int index) {}

    @Override
    public void removeChild(int index) {}

    @Override
    public void moveChild(int oldIndex, int newIndex) {}

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onUnmount() {
        view = null;
    }
}
