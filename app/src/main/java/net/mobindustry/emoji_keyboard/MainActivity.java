package net.mobindustry.emoji_keyboard;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import net.mobindustry.emojilib.EmojiPanel;
import net.mobindustry.emojilib.EmojiParser;

public class MainActivity extends Activity {

    private TextView textView;
    private ImageView sticker;
    private FrameLayout root_frame_layout;

    private EmojiPanel panel;
    private EmojiParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root_frame_layout = (FrameLayout) findViewById(R.id.root_frame_layout);
        sticker = (ImageView) findViewById(R.id.send_sticker);
        textView = (TextView) findViewById(R.id.send_emoji);

        //Create new panel, set the container in which the panel will be placed and set
        //ClickCallback to receive Spanned string with emoji and path to sticker image.
        panel = new EmojiPanel(this, root_frame_layout, new EmojiPanel.EmojiClickCallback() {
            @Override
            public void sendClicked(Spannable span) {
                textView.setText(span);
            }

            @Override
            public void stickerClicked(String path) {
                sticker.setImageURI(Uri.parse(path));
            }
        });

        //Set default icons for buttons
        //panel.iconsInit();

        //or if you need custom icons for buttons
        panel.iconsInit(R.drawable.ic_send_smile_levels, R.drawable.forward_blue);

        //initialise panel
        panel.init();

        //if you need parse Spannable from String with emoji
        parser = panel.getParser();
        Spannable parsedString = parser.parse(textView.getText().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        panel.dissmissEmojiPopup();
    }

    @Override
    public void onBackPressed() {
        if(panel.isEmojiAttached()) {
            panel.dissmissEmojiPopup();
        } else {
            super.onBackPressed();
        }
    }
}

