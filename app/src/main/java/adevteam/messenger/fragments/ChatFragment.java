package adevteam.messenger.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adevteam.messenger.R;
import adevteam.messenger.User;

public class ChatFragment extends Fragment {

    private List<Message> messages = new ArrayList<>();
    private User user;
    private EditText messageToSend;
    private MessageAdapter messageAdapter;


    public static ChatFragment newInstance() {

        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    public ChatFragment() {

    }

   @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);


       user = new User("Vladimir");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        ListView chatWindow = (ListView) view.findViewById(R.id.chatWindow);
        Button button_send = (Button) view.findViewById(R.id.action_send);
        messageToSend = (EditText) view.findViewById(R.id.message);

        ///////////////////////

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnButtonSendClick();
            }
        });

        ///////////////////////

        messageAdapter = new MessageAdapter(getActivity(), messages);
        chatWindow.setAdapter(messageAdapter);

        ///////////////////////

        return view;
    }

    private void OnButtonSendClick() {

        String mess = messageToSend.getText().toString();
        if ("".equals(mess)) {
            return;
        }

        messages.add(0, new Message(user, mess));
        messageAdapter.notifyDataSetChanged();

        messageToSend.setText("");

    }

    private class Message{

        private User user;
        private String message;
        private long datetime;

        public Message(User user, String message) {

            this.user = user;
            this.message = message;
            this.datetime = System.currentTimeMillis();

        }

        public User getUser() {
            return user;
        }

        public String getMessage() {
            return message;
        }

        public String getDateTime() {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm");
            Date resultdate = new Date(datetime);

            return sdf.format(resultdate);
        }
    }

    class MessageAdapter extends ArrayAdapter<Message>{

        private final Context context;
        private List<Message> messages;

        public MessageAdapter(Context context, List<Message> messages) {
            super(context, R.layout.chat_message_layout);

            this.context = context;
            this.messages = messages;

        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.chat_message_layout, parent, false);

            ImageView image = (ImageView) view.findViewById(R.id.chat_row_image);
            TextView name = (TextView) view.findViewById(R.id.chat_row_name);
            TextView time = (TextView) view.findViewById(R.id.chat_row_time);
            TextView chat_row_message = (TextView) view.findViewById(R.id.chat_row_message);

            Message message = messages.get(position);

            Bitmap small_image = message.getUser().getSmall_image(getActivity().getApplicationContext());
            if (small_image == null) {
                image.setImageResource(R.drawable.anonymous_contact);
            }

            name.setText(message.getUser().getName());
            time.setText(message.getDateTime());
            chat_row_message.setText(message.getMessage());

            return view;

        }
    }

}
