package vn.iotech.firebasechat;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity {

    @BindView(R.id.user_name_edt)
    EditText mUserName;
    @BindView(R.id.password_edt)
    EditText mPassword;
    String user, pass;

    @OnClick(R.id.register_bt_register)
    void doRegister() {
        register();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Firebase.setAndroidContext(this);
    }

    private void register() {
        user = mUserName.getText().toString();
        pass = mPassword.getText().toString();

        if (user.equals("")) {
            mUserName.setError("can't be blank");
        } else if (pass.equals("")) {
            mPassword.setError("can't be blank");
        } else if (!user.matches("[A-Za-z0-9]+")) {
            mUserName.setError("only alphabet or number allowed");
        } else if (user.length() < 5) {
            mUserName.setError("at least 5 characters long");
        } else if (pass.length() < 5) {
            mPassword.setError("at least 5 characters long");
        } else {
            final ProgressDialog pd = new ProgressDialog(Register.this);
            pd.setMessage("Loading...");
            pd.show();

            String url = "https://fir-chat-39f8f.firebaseio.com/users.json";

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {


                @Override
                public void onResponse(String s) {
                    Firebase reference = new Firebase("https://fir-chat-39f8f.firebaseio.com/users");

                    if (s.equals("null")) {
                        reference.child(user).child("password").setValue(pass);
                        Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            JSONObject obj = new JSONObject(s);

                            if (!obj.has(user)) {
                                reference.child(user).child("password").setValue(pass);
                                Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                onBackPressed();
                            } else {
                                Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    pd.dismiss();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    System.out.println("" + volleyError);
                    pd.dismiss();
                }
            });

            RequestQueue rQueue = Volley.newRequestQueue(Register.this);
            rQueue.add(request);
        }
    }
}
