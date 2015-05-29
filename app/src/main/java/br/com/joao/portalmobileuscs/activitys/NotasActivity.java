package br.com.joao.portalmobileuscs.activitys;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;

import br.com.joao.portalmobileuscs.CursoDisciplina;
import br.com.joao.portalmobileuscs.Disciplina;
import br.com.joao.portalmobileuscs.Nota;
import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.adapter.NotasArrayAdapter;
import br.com.joao.portalmobileuscs.libs.ORMLiteHelper;

public class NotasActivity extends SherlockActivity {

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Popular este array list com os dados do banco de dados
        ArrayList<String[]> values = new ArrayList<String[]>();

        RuntimeExceptionDao<Nota, Integer> notasDao = ORMLiteHelper.getInstance(getApplicationContext()).getNotaRuntimeDao();
        RuntimeExceptionDao<Disciplina, Integer> disciplinaDao = ORMLiteHelper.getInstance(getApplicationContext()).getDisciplinaRuntimeDao();
        RuntimeExceptionDao<CursoDisciplina, Integer> cursoDisciplinaDao = ORMLiteHelper.getInstance(getApplicationContext()).getCursoDisciplinaRuntimeDao();

        for (Nota nota : notasDao.queryForAll()) {

            cursoDisciplinaDao.refresh(nota.getCursodisciplina());
            disciplinaDao.refresh(nota.getCursodisciplina().getDisciplina());

            // Nome da Disciplina, Faltas, Limite de Faltas
            values.add(new String[]{nota.getCursodisciplina().getDisciplina().getNomeDisciplina(), Double.toString(nota.getAproveitamento()), Double.toString(nota.getProva()), Double.toString(nota.getSubstitutiva()), Double.toString(nota.getTotalPontos()), Double.toString(nota.getReavaliacao()), Double.toString(nota.getMediaFinal()), nota.getResultadoFinal()});

        }

        ListView lstw = (ListView) findViewById(R.id.list_layout);

        NotasArrayAdapter adapter = new NotasArrayAdapter(this, values);

        lstw.setAdapter(adapter);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        System.out.println(item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
