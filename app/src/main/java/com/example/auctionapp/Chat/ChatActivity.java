package com.example.auctionapp.Chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    private EditText etMessage;
    private Button btnSend;
    private RecyclerView recyclerView;

    private DatabaseReference messagesRef;
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        etMessage = findViewById(R.id.et_message);
        btnSend = findViewById(R.id.btn_send);
        recyclerView = findViewById(R.id.recyclerview);

        // Get a reference to the messages node in Firebase
        messagesRef = FirebaseDatabase.getInstance().getReference("messages");

        // Configure the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Create the FirebaseRecyclerOptions for the adapter
        FirebaseRecyclerOptions<Message> options = new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(messagesRef, Message.class)
                .build();

        // Create the adapter
        adapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull Message model) {
                // Bind the message data to the views
                holder.bind(model);
            }

            @NonNull
            @Override
            public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Inflate the item layout and create the ViewHolder
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
                return new MessageViewHolder(view);
            }
        };

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(adapter);

        // Set up the click listener for the send button
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etMessage.getText().toString().trim();
                String senderId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                // Create a new message object
                Message message = new Message(content, senderId, System.currentTimeMillis());

                // Save the message to the Firebase database
                messagesRef.push().setValue(message);

                // Clear the message input field
                etMessage.setText("");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    // ViewHolder for the messages
    private static class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        private TextView tvSender;

        MessageViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvSender = itemView.findViewById(R.id.tv_sender);
        }

        void bind(Message message) {
            tvContent.setText(message.getContent());
            tvSender.setText(message.getSenderId());
        }
    }
}
