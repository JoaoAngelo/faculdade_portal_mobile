package br.com.joao.portalmobileuscs.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.libs.FuncoesData;

public class DownloadArrayAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String[]> values;

    public DownloadArrayAdapter(Context context, ArrayList<String[]> values) {
        super();
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.download_item_layout, parent, false);

        // Adiciona Descri��o do id.download_
        TextView txtDescricao = (TextView) rowView.findViewById(R.id.download_txtDescricao);
        txtDescricao.setText(values.get(position)[0]);

        // Adiciona data do Download
        TextView txtData = (TextView) rowView.findViewById(R.id.download_txtData);
        txtData.setText(FuncoesData.DateTOString(FuncoesData.StringTODate(values.get(position)[1])));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(values.get(position)[2]));
                context.startActivity(i);

            }
        });

        return rowView;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {

        return values.get(position)[1];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

}