package br.com.joao.portalmobileuscs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import br.com.joao.portalmobileuscs.R;

public class NotasArrayAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String[]> values;

    public NotasArrayAdapter(Context context, ArrayList<String[]> values) {
        super();
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.notas_item_layout, parent, false);

        // Adiciona nome da disciplina
        TextView txtDisciplina = (TextView) rowView.findViewById(R.id.notas_txtDisciplina);
        txtDisciplina.setText(values.get(position)[0]);

        // Adiciona nota aproveitamento
        TextView txtAproveitamento = (TextView) rowView.findViewById(R.id.notas_aproveitamentoNota);
        txtAproveitamento.setText(converterDoubleDoisDecimais(values.get(position)[1]));

        // Adiciona nota prova
        TextView txtProva = (TextView) rowView.findViewById(R.id.notas_provaNota);
        txtProva.setText(converterDoubleDoisDecimais(values.get(position)[2]));

        // Adiciona nota substutiva
        TextView txtSubstutiva = (TextView) rowView.findViewById(R.id.notas_substutivaNota);
        txtSubstutiva.setText(converterDoubleDoisDecimais(values.get(position)[3]));

        // Adiciona nota totalPontos
        TextView txtTotalPontos = (TextView) rowView.findViewById(R.id.notas_totalPontosNota);
        txtTotalPontos.setText(converterDoubleDoisDecimais(values.get(position)[4]));

        // Adiciona nota reavaliacao
        TextView txtReavaliacao = (TextView) rowView.findViewById(R.id.notas_reavaliacaoNota);
        txtReavaliacao.setText(converterDoubleDoisDecimais(values.get(position)[5]));

        // Adiciona nota mediaFinal
        TextView txtMediaFinal = (TextView) rowView.findViewById(R.id.notas_mediaFinalNota);
        txtMediaFinal.setText(converterDoubleDoisDecimais(values.get(position)[6]));

        // Adiciona nota resultadoFinal
        TextView txtResultadoFinal = (TextView) rowView.findViewById(R.id.notas_resultadoFinalNota);
        txtResultadoFinal.setText(values.get(position)[7]);

        double reavaliacao = Double.valueOf(values.get(position)[5]);
        double mediaFinal = Double.valueOf(values.get(position)[6]);

        if (reavaliacao > 0) {
            if (mediaFinal >= 5) {
                txtResultadoFinal.setTextColor(context.getResources().getColor(R.color.laranja_uscs));
            } else {
                txtResultadoFinal.setTextColor(context.getResources().getColor(R.color.red));
            }
        } else {
            if (mediaFinal >= 7) {
                txtResultadoFinal.setTextColor(context.getResources().getColor(R.color.green));
            } else {
                txtResultadoFinal.setTextColor(context.getResources().getColor(R.color.red));
            }
        }

        return rowView;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object getItem(int arg0) {
        return 0;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    public static String converterDoubleDoisDecimais(String precoString) {
        double precoDouble = 0;
        precoDouble = Double.valueOf(precoString);
        DecimalFormat fmt = new DecimalFormat("0.00");
        String string = fmt.format(precoDouble);
        String[] part = string.split("[,]");
        String preco = part[0] + "." + part[1];
        return preco;
    }
}