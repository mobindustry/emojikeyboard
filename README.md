![Screenshot](https://github.com/frontiertsymbal/emoji_keyboard/blob/master/EmojiKeyboard.png)
Telegram like implementation for emjoicons which appear as a popup over the soft keyboard
### Requirements
The library requires Android API Level 14+.
## Usage
You may use the code in two ways - with and without panel.
## Keyboard with panel
### your_activity.xml
Create container for panel in your activity xml file
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
private FrameLayout mFrameLayout;
private EmojiPanel mPanel;
private EmojiParser mParser;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
    setContentView(R.layout.your_activity);

    mFrameLayout = (FrameLayout) findViewById(R.id.root_frame_layout);

    //Create new panel, set the container in which the panel will be placed and set ClickCallback to receive Spanned string with emoji and path to sticker image.
    mPanel = new EmojiPanel(this, mFrameLayout, new EmojiPanel.EmojiClickCallback() {
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
    mPanel.iconsInit();

    //or if you need custom icons for buttons
    mPanel.iconsInit(R.drawable.ic_send_smile_levels, R.drawable.forward_blue);

    //initialise panel
    mPanel.init();

    //if you need parse Spannable from String with emoji
    mParser = mPanel.getParser();
    Spannable parsedString = mParser.parse(textView.getText().toString());
}

@Override
protected void onPause() {
    super.onPause();
    mPanel.dissmissEmojiPopup();
}

@Override
public void onBackPressed() {
    if(mPanel.isEmojiAttached()) {
        mPanel.dissmissEmojiPopup();
    } else {
        super.onBackPressed();
    }
}
```
### ic_send_smile_levels.xml
Create level-list xml to customize icons for smile/keyboard buttons in drawable
``` xml
<?xml version="1.0" encoding="utf-8"?>
<level-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/ic_msg_panel_kb" android:maxLevel="0"/>
    <item android:drawable="@drawable/doc_blue" android:maxLevel="1"/>
</level-list>
```
## Keyboard without panel
To use keyboard see KeyboardActivity.java code in sample project

## Current stickers
To add your own stickers on the keyboard, you need to put them in a /assets/stickers/

###### Note
This code has been carefully excised out of the project at https://github.com/rovkinmax/tchalange
