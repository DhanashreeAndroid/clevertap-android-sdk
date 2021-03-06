package com.clevertap.android.sdk;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Custom ViewHolder for Simple message layout
 */
class CTSimpleMessageViewHolder extends CTInboxBaseMessageViewHolder {

    private TextView title;
    private TextView message;
    private TextView timestamp;
    private ImageView readDot, mediaImage, squareImage;
    private Button cta1,cta2,cta3;


    CTSimpleMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setTag(this);
        title = itemView.findViewById(R.id.messageTitle);
        message = itemView.findViewById(R.id.messageText);
        timestamp = itemView.findViewById(R.id.timestamp);
        readDot = itemView.findViewById(R.id.read_circle);
        cta1 = itemView.findViewById(R.id.cta_button_1);
        cta2 = itemView.findViewById(R.id.cta_button_2);
        cta3 = itemView.findViewById(R.id.cta_button_3);
        mediaImage = itemView.findViewById(R.id.media_image);
        relativeLayout = itemView.findViewById(R.id.simple_message_relative_layout);
        frameLayout = itemView.findViewById(R.id.simple_message_frame_layout);
        squareImage = itemView.findViewById(R.id.square_media_image);
        clickLayout = itemView.findViewById(R.id.click_relative_layout);
        ctaLinearLayout = itemView.findViewById(R.id.cta_linear_layout);
        bodyRelativeLayout = itemView.findViewById(R.id.body_relative_layout);
    }

    @Override
    void configureWithMessage(final CTInboxMessage inboxMessage, final CTInboxListViewFragment parent, final int position) {
        super.configureWithMessage(inboxMessage, parent, position);
        final CTInboxListViewFragment parentWeak = getParent();
        CTInboxMessageContent content = inboxMessage.getInboxMessageContents().get(0);

        this.title.setText(content.getTitle());
        this.title.setTextColor(Color.parseColor(content.getTitleColor()));
        this.message.setText(content.getMessage());
        this.message.setTextColor(Color.parseColor(content.getMessageColor()));
        this.bodyRelativeLayout.setBackgroundColor(Color.parseColor(inboxMessage.getBgColor()));
        String displayTimestamp  = calculateDisplayTimestamp(inboxMessage.getDate());
        this.timestamp.setText(displayTimestamp);
        this.timestamp.setTextColor(Color.parseColor(content.getTitleColor()));
        if(inboxMessage.isRead()){
            this.readDot.setVisibility(View.GONE);
        }else{
            this.readDot.setVisibility(View.VISIBLE);
        }
        //Shows the CTA layout only if links are present, also handles the display of the CTAs depending on the number
        JSONArray linksArray = content.getLinks();
        if(linksArray != null){
            this.ctaLinearLayout.setVisibility(View.VISIBLE);
            int size = linksArray.length();
            JSONObject cta1Object,cta2Object,cta3Object;
            try {
                switch (size){
                    case 1:
                        cta1Object = linksArray.getJSONObject(0);
                        this.cta1.setVisibility(View.VISIBLE);
                        this.cta1.setText(content.getLinkText(cta1Object));
                        this.cta1.setTextColor(Color.parseColor(content.getLinkColor(cta1Object)));
                        this.cta1.setBackgroundColor(Color.parseColor(content.getLinkBGColor(cta1Object)));
                        hideTwoButtons(this.cta1, this.cta2, this.cta3);
                        if(parentWeak != null) {
                            this.cta1.setOnClickListener(new CTInboxButtonClickListener(position, inboxMessage, this.cta1.getText().toString(),cta1Object, parentWeak));
                        }
                        break;
                    case 2:
                        cta1Object = linksArray.getJSONObject(0);
                        this.cta1.setVisibility(View.VISIBLE);
                        this.cta1.setText(content.getLinkText(cta1Object));
                        this.cta1.setTextColor(Color.parseColor(content.getLinkColor(cta1Object)));
                        this.cta1.setBackgroundColor(Color.parseColor(content.getLinkBGColor(cta1Object)));
                        cta2Object = linksArray.getJSONObject(1);
                        this.cta2.setVisibility(View.VISIBLE);
                        this.cta2.setText(content.getLinkText(cta2Object));
                        this.cta2.setTextColor(Color.parseColor(content.getLinkColor(cta2Object)));
                        this.cta2.setBackgroundColor(Color.parseColor(content.getLinkBGColor(cta2Object)));
                        hideOneButton(this.cta1,this.cta2,this.cta3);
                        if(parentWeak != null) {
                            this.cta1.setOnClickListener(new CTInboxButtonClickListener(position, inboxMessage, this.cta1.getText().toString(),cta1Object, parentWeak));
                            this.cta2.setOnClickListener(new CTInboxButtonClickListener(position, inboxMessage, this.cta2.getText().toString(),cta2Object, parentWeak));
                        }
                        break;
                    case 3:
                        cta1Object = linksArray.getJSONObject(0);
                        this.cta1.setVisibility(View.VISIBLE);
                        this.cta1.setText(content.getLinkText(cta1Object));
                        this.cta1.setTextColor(Color.parseColor(content.getLinkColor(cta1Object)));
                        this.cta1.setBackgroundColor(Color.parseColor(content.getLinkBGColor(cta1Object)));
                        cta2Object = linksArray.getJSONObject(1);
                        this.cta2.setVisibility(View.VISIBLE);
                        this.cta2.setText(content.getLinkText(cta2Object));
                        this.cta2.setTextColor(Color.parseColor(content.getLinkColor(cta2Object)));
                        this.cta2.setBackgroundColor(Color.parseColor(content.getLinkBGColor(cta2Object)));
                        cta3Object = linksArray.getJSONObject(2);
                        this.cta3.setVisibility(View.VISIBLE);
                        this.cta3.setText(content.getLinkText(cta3Object));
                        this.cta3.setTextColor(Color.parseColor(content.getLinkColor(cta3Object)));
                        this.cta3.setBackgroundColor(Color.parseColor(content.getLinkBGColor(cta3Object)));
                        if(parentWeak != null) {
                            this.cta1.setOnClickListener(new CTInboxButtonClickListener(position, inboxMessage, this.cta1.getText().toString(),cta1Object, parentWeak));
                            this.cta2.setOnClickListener(new CTInboxButtonClickListener(position, inboxMessage, this.cta2.getText().toString(),cta2Object, parentWeak));
                            this.cta3.setOnClickListener(new CTInboxButtonClickListener(position, inboxMessage, this.cta3.getText().toString(),cta3Object, parentWeak));
                        }
                        break;
                }
            }catch (JSONException e){
                Logger.d("Error parsing CTA JSON - "+e.getLocalizedMessage());
            }
        }else{
            this.ctaLinearLayout.setVisibility(View.GONE);
        }
        //Loads the media based on orientation and media type
        removeVideoView();
        this.mediaImage.setVisibility(View.GONE);
        this.squareImage.setVisibility(View.GONE);
        try {
            switch (inboxMessage.getOrientation()) {
                case "l":
                    if (content.mediaIsImage()) {
                        this.mediaImage.setVisibility(View.VISIBLE);
                        this.mediaImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(this.mediaImage.getContext())
                                .load(content.getMedia())
                                .into(this.mediaImage);
                    } else if (content.mediaIsGIF()) {
                        this.mediaImage.setVisibility(View.VISIBLE);
                        this.mediaImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(this.mediaImage.getContext())
                                .asGif()
                                .load(content.getMedia())
                                .into(this.mediaImage);
                    } else if (content.mediaIsVideo() || content.mediaIsAudio()) {
                        addMediaPlayerView(inboxMessage);
                    }
                    break;
                case "p":
                    if (content.mediaIsImage()) {
                        this.squareImage.setVisibility(View.VISIBLE);
                        this.squareImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(this.squareImage.getContext())
                                .load(content.getMedia())
                                .into(this.squareImage);
                    } else if (content.mediaIsGIF()) {
                        this.squareImage.setVisibility(View.VISIBLE);
                        this.squareImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        Glide.with(this.squareImage.getContext())
                                .asGif()
                                .load(content.getMedia())
                                .into(this.squareImage);
                    } else if (content.mediaIsVideo() || content.mediaIsAudio()) {
                        addMediaPlayerView(inboxMessage);
                    }
                    break;
            }
        }catch (NoClassDefFoundError error) {
            Logger.d("CleverTap SDK requires Glide dependency. Please refer CleverTap Documentation for more info");
        }
        //New thread to remove the Read dot, mark message as read and raise Notification Viewed
        Runnable simpleRunnable = new Runnable() {
            @Override
            public void run() {
                final CTInboxListViewFragment parent = getParent();
                if(parent != null){
                    Activity activity = parent.getActivity();
                    if (activity == null) return;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(readDot.getVisibility() == View.VISIBLE) {
                                parent.didShow(null, position);
                            }
                            readDot.setVisibility(View.GONE);
                        }
                    });
                }

            }
        };
        Handler simpleHandler = new Handler();
        simpleHandler.postDelayed(simpleRunnable,2000);
        if(parentWeak != null) {
            this.clickLayout.setOnClickListener(new CTInboxButtonClickListener(position, inboxMessage, null,null, parentWeak));
        }

    }
}
