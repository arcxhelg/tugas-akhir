package com.example.tugasakhir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SentMessageAdapter extends RecyclerView.Adapter<SentMessageAdapter.MessageViewHolder> {

    private Context context;
    private List<GetMessages> sentMessageList;

    public SentMessageAdapter(Context context, List<GetMessages> sentMessageList) {
        this.context = context;
        this.sentMessageList = sentMessageList;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.messages_list_layout, null);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        GetMessages sentMessage = sentMessageList.get(position);

        holder.receiver_email.setText(sentMessage.getReceiverEmail());
        holder.email_subject.setText(sentMessage.getEmailSubject());
        holder.email_datetime.setText(sentMessage.getEmailDatetime());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewSentMessage.class);

                intent.putExtra("receiver_email", sentMessage.getReceiverEmail());
                intent.putExtra("email_subject", sentMessage.getEmailSubject());
                intent.putExtra("email_message", sentMessage.getEmailMessage());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sentMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView receiver_email, email_subject, email_datetime;
        RelativeLayout container;

        public MessageViewHolder(View itemView) {
            super(itemView);

            receiver_email = itemView.findViewById(R.id.sender_receiver_email);
            email_subject = itemView.findViewById(R.id.email_subject);
            email_datetime = itemView.findViewById(R.id.email_datetime);
            container = itemView.findViewById(R.id.message_container);
        }
    }
}
