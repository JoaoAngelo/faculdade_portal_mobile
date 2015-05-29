package br.com.joao.portalmobileuscs.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.joao.portalmobileuscs.Link;
import br.com.joao.portalmobileuscs.R;

public class LinkArrayAdapter extends BaseAdapter {
    Link currentLink;
    private Context context;
    private LayoutInflater inflater;
    private List<Link> links;

    public LinkArrayAdapter(Context context, List<Link> links) {
        this.context = context;
        this.links = links;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.link_item_layout, null);

        TextView txtDescricao = (TextView) convertView.findViewById(R.id.link_txtDescricao);
        TextView txtURL = (TextView) convertView.findViewById(R.id.link_txtURL);

        currentLink = (Link) getItem(position);

        txtDescricao.setText(currentLink.getDescricao_link());
        txtURL.setText(currentLink.getUrl());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(currentLink.getUrl()));
                context.startActivity(i);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return links.size();
    }

    @Override
    public Object getItem(int location) {
        return links.get(location);
    }

    @Override
    public long getItemId(int position) {
        return links.get(position).getId_link();
    }

}