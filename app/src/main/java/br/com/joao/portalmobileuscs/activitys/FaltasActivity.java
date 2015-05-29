package br.com.joao.portalmobileuscs.activitys;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;

import br.com.joao.portalmobileuscs.Disciplina;
import br.com.joao.portalmobileuscs.DisciplinaProfessor;
import br.com.joao.portalmobileuscs.Falta;
import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.adapter.FaltasArrayAdapter;
import br.com.joao.portalmobileuscs.libs.ORMLiteHelper;

public class FaltasActivity extends SherlockActivity {

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// Popular este array list com os dados do banco de dados
		ArrayList<String[]> values = new ArrayList<String[]>();

		RuntimeExceptionDao<Falta, Integer> faltaDao = ORMLiteHelper.getInstance(getApplicationContext()).getFaltaRuntimeDao();
		RuntimeExceptionDao<Disciplina, Integer> disciplinaDao = ORMLiteHelper.getInstance(getApplicationContext()).getDisciplinaRuntimeDao();
		RuntimeExceptionDao<DisciplinaProfessor, Integer> disciplinaProfessorDao = ORMLiteHelper.getInstance(getApplicationContext()).getDisciplinaProfessorRuntimeDao();

		for (Falta falta : faltaDao.queryForAll()) {

			disciplinaProfessorDao.refresh(falta.getDisciplinaProfessor());
			disciplinaDao.refresh(falta.getDisciplinaProfessor().getDisciplina());

			// Nome da Disciplina, Faltas, Limite de Faltas
			values.add(new String[] { 
					falta.getDisciplinaProfessor().getDisciplina().getNomeDisciplina(), 
					Integer.toString(falta.getQtdFaltas()),
					Integer.toString(falta.getLimiteFaltas()) 
					});

		}

		ListView lstw = (ListView) findViewById(R.id.list_layout);

		FaltasArrayAdapter adapter = new FaltasArrayAdapter(this, values);

		lstw.setAdapter(adapter);

	}

	@Override
	public void onBackPressed() {
		this.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
}
