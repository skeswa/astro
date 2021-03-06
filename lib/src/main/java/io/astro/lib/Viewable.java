package io.astro.lib;

import android.content.Context;
import android.view.View;

/**
 * @author skeswa
 */
public interface Viewable extends Attributable {
    void onCreateView(final Context context);
    void onDestroyView();

    void insertChild(final View view, final int index);
    void removeChild(final int index);
    void moveChild(final int oldIndex, final int newIndex);

    View getView();
}
