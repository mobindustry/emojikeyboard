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

public class KeyboardWithPanelActivity extends Activity {

    private TextView mTextView;
    private ImageView mSticker;
    private FrameLayout mFrameLayout;

    private EmojiPanel mPanel;
    private EmojiParser mParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_with_panel);

        mFrameLayout = (FrameLayout) findViewById(R.id.root_frame_layout);
        mSticker = (ImageView) findViewById(R.id.send_sticker);
        mTextView = (TextView) findViewById(R.id.send_emoji);

        //Create new panel, set the container in which the panel will be placed and set
        //ClickCallback to receive Spanned string with emoji and path to sticker image.
        mPanel = new EmojiPanel(this, mFrameLayout, new EmojiPanel.EmojiClickCallback() {
            @Override
            public void sendClicked(Spannable span) {
                mTextView.setText(span);
            }

            @Override
            public void stickerClicked(String path) {
                mSticker.setImageURI(Uri.parse(path));
            }
        });

        //Set default icons for buttons
        //panel.iconsInit();

        //or if you need custom icons for buttons
        mPanel.iconsInit(R.drawable.ic_send_smile_levels, R.drawable.forward_blue);

        //initialise panel
        mPanel.init();

        //if you need parse Spannable from String with emoji
        mParser = mPanel.getParser();
        Spannable parsedString = mParser.parse(mTextView.getText().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPanel.dissmissEmojiPopup();
    }

    @Override
    public void onBackPressed() {
        if (mPanel.isEmojiAttached()) {
            mPanel.dissmissEmojiPopup();
        } else {
            super.onBackPressed();
        }
    }
}
