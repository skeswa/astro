package io.astro.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.astro.lib.AstroActivity;
import io.astro.lib.Element;

public class MainActivity extends AstroActivity {
    @Override
    public Element render() {
        return $(FrenchFlag.class).create();
    }
}
