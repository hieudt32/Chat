package vn.iotech.firebasechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.message_edt)
    EditText mMessageEt;
    @BindView(R.id.send_imb)
    ImageView mSendIb;
    @BindView(R.id.chat_rv)
    RecyclerView mChatRv;
    ChatAdapter mAdapter;
    List<Message> mListChat = new ArrayList<>();
    Firebase reference1;
    Firebase reference2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://fir-chat-39f8f.firebaseio.com/messages/" + Users.USER + "_" + Users.CHATWITH);
        reference2 = new Firebase("https://fir-chat-39f8f.firebaseio.com/messages/" + Users.CHATWITH + "_" + Users.USER);
        mAdapter = new ChatAdapter(mListChat);
        mChatRv.setAdapter(mAdapter);
        mChatRv.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        mSendIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mMessageEt.getText().toString();
                if (!message.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", message);
                    map.put("user", Users.USER);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    mMessageEt.setText("");
                }
            }
        });

        mMessageEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().trim().isEmpty()) {
                    mSendIb.setClickable(true);
                } else {
                    mSendIb.setClickable(false);
                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                Message message = new Message();
                message.setMessage(map.get("message").toString());
                String user = map.get("user").toString();
                message.setUser(user);
                if (user.equals(Users.USER)) {
                    message.setMe(true);
                } else {
                    message.setMe(false);
                }
                mListChat.add(message);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


}
