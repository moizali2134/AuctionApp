package com.example.auctionapp.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.auctionapp.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class InboxActivity extends AppCompatActivity {

    private FirebaseListAdapter<Conversation> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        ListView listView = findViewById(R.id.list_view);

        // Get a reference to the conversations node in Firebase
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String conversationsPath = "conversations/" + currentUser;
        FirebaseListOptions<Conversation> options = new FirebaseListOptions.Builder<Conversation>()
                .setQuery(FirebaseDatabase.getInstance().getReference(conversationsPath), Conversation.class)
                .setLayout(R.layout.item_conversation)
                .build();

        // Create the adapter
        adapter = new FirebaseListAdapter<Conversation>(options) {
            @Override
            protected void populateView(@NonNull View view, @NonNull Conversation conversation, int position) {
                // Populate the conversation data in the item view
                // Here, you can set the conversation participants, last message, etc.
            }
        };

        // Set the adapter on the ListView
        listView.setAdapter(adapter);

        // Set up item click listener to open the chat activity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked conversation
                Conversation conversation = (Conversation) parent.getItemAtPosition(position);

                // Start the chat activity and pass the necessary conversation details
                Intent intent = new Intent(InboxActivity.this, ChatActivity.class);
                intent.putExtra("conversationId", conversation.getId());
                intent.putExtra("recipientId", conversation.getRecipientId());
                startActivity(intent);
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
}

