package vn.iotech.firebasechat;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ap on 12/19/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {

    List<Message> mListMessage;
    Context mContext;

    public ChatAdapter(List<Message> mListMessage) {
        this.mListMessage = mListMessage;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_chat, parent, false);
        return new ChatHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        Message message = mListMessage.get(position);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.bg_message_one);
        if (message.isMe()) {
            holder.mItemMessage.setText(message.getMessage());
            drawable.setColorFilter(mContext.getResources().getColor(android.R.color.holo_blue_bright), PorterDuff.Mode.MULTIPLY);
            holder.mItemMessage.setBackground(drawable);
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            params.setMarginStart(150);
            params.setMarginEnd(10);
        } else {
            String text = message.getUser() + ": " + message.getMessage();
            holder.mItemMessage.setText(text);
            drawable.setColorFilter(mContext.getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.MULTIPLY);
            holder.mItemMessage.setBackground(drawable);
            params.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
            params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            params.setMarginEnd(150);
            params.setMarginStart(10);
        }
        holder.mItemMessage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return mListMessage != null ? mListMessage.size() : 0;
    }

    public class ChatHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_message)
        TextView mItemMessage;

        public ChatHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
