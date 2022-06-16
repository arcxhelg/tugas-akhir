package com.example.tugasakhir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReceivedMessageAdapter extends RecyclerView.Adapter<ReceivedMessageAdapter.MessageViewHolder> {

    private Context context;
    private List<GetMessages> receivedMessageList;

    public ReceivedMessageAdapter(Context context, List<GetMessages> receivedMessageList) {
        this.context = context;
        this.receivedMessageList = receivedMessageList;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.messages_list_layout, null);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        GetMessages receivedMessage = receivedMessageList.get(position);

        holder.sender_email.setText(receivedMessage.getSenderEmail());
        holder.email_subject.setText(receivedMessage.getEmailSubject());
        holder.email_datetime.setText(receivedMessage.getEmailDatetime());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewReceivedMessage.class);

                intent.putExtra("sender_email", receivedMessage.getSenderEmail());
                intent.putExtra("email_subject", receivedMessage.getEmailSubject());
                intent.putExtra("email_message", receivedMessage.getEmailMessage());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return receivedMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView sender_email, email_subject, email_datetime;
        RelativeLayout container;

        public MessageViewHolder(View itemView) {
            super(itemView);

            sender_email = itemView.findViewById(R.id.sender_receiver_email);
            email_subject = itemView.findViewById(R.id.email_subject);
            email_datetime = itemView.findViewById(R.id.email_datetime);
            container = itemView.findViewById(R.id.message_container);
        }
    }
}
