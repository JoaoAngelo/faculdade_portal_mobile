package br.com.joao.portalmobileuscs.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.activitys.DownloadActivity;

public class DisciplinaDownloadArrayAdapter extends BaseAdapter {
    private final Context context;
    private final List<String[]> values;

    public DisciplinaDownloadArrayAdapter(Context context, ArrayList<String[]> values) {
        super();
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.disciplina_download_item_layout, parent, false);

        // Disciplina da MateriaDownload.
        TextView txtDisciplina = (TextView) rowView.findViewById(R.id.disciplinaDownload_txtDiciplina);
        txtDisciplina.setText(values.get(position)[1]);

        // Professor da MateriaDownload.
        TextView txtProfessor = (TextView) rowView.findViewById(R.id.disciplinaDownload_txtProfessor);
        txtProfessor.setText(values.get(position)[2]);

        // QtdeArquivos da MateriaDownload.
        TextView txtQtdeArquivos = (TextView) rowView.findViewById(R.id.disciplinaDownload_txtQtdeArquivos);
        txtQtdeArquivos.setText(values.get(position)[3] + " arquivo(s)");

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DownloadActivity.class);
                Bundle params = new Bundle();

                String idDisciplinaProfessor = values.get(position)[0];
                String nomeDisciplina = values.get(position)[1];
                params.putString("idDisciplinaProfessor", idDisciplinaProfessor);
                params.putString("nomeDisciplina", nomeDisciplina);
                i.putExtras(params);
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