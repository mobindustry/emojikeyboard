package net.mobindustry.emoji_keyboard;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.mobindustry.emojilib.EmojiPanel;
import net.mobindustry.emojilib.EmojiParser;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button panel = (Button) findViewById(R.id.keyboard_with_panel_button);
        Button activity = (Button) findViewById(R.id.keyboard_on_activity_button);

        panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KeyboardWithPanelActivity.class);
                startActivity(intent);
            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KeyboardActivity.class);
                startActivity(intent);
            }
        });
    }
}

