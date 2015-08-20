# EmojiKeyboard
Telegram like implementation for emoticons that displays in the app as a pop-up over the soft keyboard

![Screenshot](https://github.com/frontiertsymbal/emoji_keyboard/blob/master/EmojiKeyboard.png)
### Requirements
The library requires Android API Level 14+.
## Integration
* Download and unzip the project you've just downloaded
* Import the emojilib module in your Android Studio project (File > New > Import Module)
* Add module to build.gradle
```groovy
 dependencies {
    compile project (':emojilib')
 }
```
* Enjoy!

## Usage
You may use the code in two ways - with input panel and without it.
## Keyboard with input panel
### your_activity.xml
Create container for input panel in your activity xml file
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
Create level-list xml to customize icons for smile/keyboard button in drawable
### ic_send_smile_levels.xml
``` xml
<?xml version="1.0" encoding="utf-8"?>
<level-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/ic_msg_panel_kb" android:maxLevel="0"/>
    <item android:drawable="@drawable/doc_blue" android:maxLevel="1"/>
</level-list>
```
These actions enough to enable you to use the keyboard.
## Keyboard without input panel
To use keyboard see KeyboardActivity.java code in sample project

## Current stickers
To add your own stickers on the keyboard you need to put them in a /assets/stickers/ directory

###### Note
This code has been carefully excised out of the project at https://github.com/rovkinmax/tchalange
