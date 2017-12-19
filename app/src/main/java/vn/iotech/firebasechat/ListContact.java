package vn.iotech.firebasechat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListContact extends AppCompatActivity {

    @BindView(R.id.list_contact_lv)
    ListView mListContactLv;
    @BindView(R.id.empty_tv)
    TextView mEmptyTv;
    private ArrayList<String> mListContact;
    private ProgressDialog mProgress;
    private int mTotalUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        ButterKnife.bind(this);
        loadContact();
    }

    private void loadContact() {
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Loading...");
        mProgress.show();
        String url = "https://fir-chat-39f8f.firebaseio.com/users.json";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
                if (mProgress != null) mProgress.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(this);
        rQueue.add(request);
    }

    private void doOnSuccess(String s) {
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while (i.hasNext()) {
                key = i.next().toString();

                if (!key.equals(Users.USER)) {
                    mListContact.add(key);
                }

                mTotalUser++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mTotalUser <= 1) {
            mEmptyTv.setVisibility(View.VISIBLE);
            mListContactLv.setVisibility(View.GONE);
        } else {
            mEmptyTv.setVisibility(View.GONE);
            mListContactLv.setVisibility(View.VISIBLE);
            mListContactLv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListContact));
        }

        mListContactLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Users.CHATWITH = mListContact.get(i);
                startActivity(new Intent(ListContact.this, ChatActivity.class));
            }
        });

        if (mProgress != null) mProgress.dismiss();
    }
}
