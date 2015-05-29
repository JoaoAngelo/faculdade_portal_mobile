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

public class TurmaArrayAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String[]> values;

    public TurmaArrayAdapter(Context context, ArrayList<String[]> values) {
        super();
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.turma_item_layout, parent, false);

        // Adiciona nome do estudante na lista
        TextView txtDisciplina = (TextView) rowView.findViewById(R.id.turma_txtEstudante);
        txtDisciplina.setText(values.get(position)[0]);

        // Adiciona email do estudante na lista
        TextView txtFaltas = (TextView) rowView.findViewById(R.id.turma_txtEmail);
        txtFaltas.setText(values.get(position)[1]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + values.get(position)[1]));
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
    public Object getItem(int arg0) {
        return 0;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }
}