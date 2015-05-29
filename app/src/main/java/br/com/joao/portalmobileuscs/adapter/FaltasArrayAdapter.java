package br.com.joao.portalmobileuscs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.joao.portalmobileuscs.R;

public class FaltasArrayAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String[]> values;

    public FaltasArrayAdapter(Context context, ArrayList<String[]> values) {
        super();
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.faltas_item_layout, parent, false);

        // Adiciona nome da disciplina na lista
        TextView txtDisciplina = (TextView) rowView.findViewById(R.id.txtDisciplina);
        txtDisciplina.setText(values.get(position)[0]);

        // Adiciona qtd de faltas na lista
        TextView txtFaltas = (TextView) rowView.findViewById(R.id.txtFaltas);
        txtFaltas.setText(values.get(position)[1]);

        // Adiciona limite de faltas na lista
        TextView txtLimiteFaltas = (TextView) rowView.findViewById(R.id.txtLimiteFaltas);
        txtLimiteFaltas.setText(values.get(position)[2]);

        Double qtdFaltas = Double.valueOf(values.get(position)[1]);
        Double limiteFaltas = Double.valueOf(values.get(position)[2]);

        // Percentual de faltas( limite de faltas VS qtd de faltas )
        double percentualFaltas = (qtdFaltas / limiteFaltas) * 100;

        // Menor que 50 porcento considerar verde
        if (percentualFaltas <= 50) {
            txtFaltas.setTextColor(context.getResources().getColor(R.color.green));
        }
        // Menor que 80 porcento considerar amarelo
        else if (percentualFaltas > 50 & percentualFaltas < 80) {
            txtFaltas.setTextColor(context.getResources().getColor(R.color.laranja_uscs));
        }
        // Maior ou igual a 80 porcento considerar vermelho
        else if (percentualFaltas >= 80) {
            txtFaltas.setTextColor(context.getResources().getColor(R.color.red));
        }

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
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }
}