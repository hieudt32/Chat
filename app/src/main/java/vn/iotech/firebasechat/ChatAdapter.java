package vn.iotech.firebasechat;

import android.support.v7.widget.RecyclerView;
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

    public ChatAdapter(List<Message> mListMessage) {
        this.mListMessage = mListMessage;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        Message message = mListMessage.get(position);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.mItemMessage.getLayoutParams();
        if (message.isMe()) {
            holder.mItemMessage.setText(message.getMessage());
            holder.mItemMessage.setBackgroundResource(R.drawable.bg_message_one);
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
        } else {
            String text = message.getUser() + ": " + message.getMessage();
            holder.mItemMessage.setText(text);
            holder.mItemMessage.setBackgroundResource(R.drawable.bg_message_two);
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
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
            ButterKnife.bind(itemView);
        }
    }
}
