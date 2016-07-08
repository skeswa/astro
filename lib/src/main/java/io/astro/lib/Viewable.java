package io.astro.lib;

import android.content.Context;
import android.view.View;

/**
 * @author skeswa
 */
public interface Viewable extends ComponentType {
    void onMount(final Context context);
    void onUnmount();

    void insertChild(final View view, final int index);
    void removeChild(final int index);
    void moveChild(final int oldIndex, final int newIndex);

    View getView();
}
