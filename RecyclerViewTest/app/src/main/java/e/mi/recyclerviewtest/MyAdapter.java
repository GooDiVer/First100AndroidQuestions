package e.mi.recyclerviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Item> questionList;
    Context m;

    public MyAdapter(Context m, List<Item> questionList) {
        this.m = m;
        this.questionList = questionList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        String link;
        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.textView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(m).inflate(R.layout.item_recycler,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Item item = questionList.get(i);
        String title = item.getTitle();
        String link = item.getLink();
        String text = String.format("%d) <a href=' %s '> %s </a>",i,link,title);
//        myViewHolder.title.setText(text);
        myViewHolder.title.setMovementMethod(LinkMovementMethod.getInstance());
        myViewHolder.title.setText(Html.fromHtml(text));

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }
}
