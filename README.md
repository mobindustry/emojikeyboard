# emoji_keyboard
### Emoji keyboard with input panel
![Screenshot](https://github.com/frontiertsymbal/emoji_keyboard/blob/master/Emoji%20Keyboard.png)
## Usage
### your_activity.xml
Create container for panel
``` xml
<FrameLayout
    android:id="@+id/root_frame_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom"
    >
</FrameLayout>
```
### YourActivity.java
``` java
private FrameLayout root_frame_layout;
private EmojiPanel panel;
private EmojiParser parser;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
    setContentView(R.layout.your_activity);

    root_frame_layout = (FrameLayout) findViewById(R.id.root_frame_layout);

//Create new panel, set the container in which the panel will be placed and set ClickCallback to receive Spanned string with emoji and path to sticker image.
    panel = new EmojiPanel(this, root_frame_layout, new EmojiPanel.EmojiClickCallback() {
        @Override
        public void sendClicked(Spannable span) {
            //do something with received Spannable with emoji
        }
        @Override
        public void stickerClicked(String path) {
           //do something with received path to Sticker image
        }
    });
//Set default icons for buttons
    panel.iconsInit();
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
```
### ic_send_smile_levels.xml
Create level-list xml to customize icons for smile/keyboard buttons
``` xml

		<?xml version="1.0" encoding="utf-8"?>
		<level-list xmlns:android="http://schemas.android.com/apk/res/android">
		    <item android:drawable="@drawable/ic_msg_panel_kb" android:maxLevel="0"/>
		    <item android:drawable="@drawable/doc_blue" android:maxLevel="1"/>
		</level-list>
```
To add your own stickers on the keyboard, you need to put them in a /assets/stickers/
