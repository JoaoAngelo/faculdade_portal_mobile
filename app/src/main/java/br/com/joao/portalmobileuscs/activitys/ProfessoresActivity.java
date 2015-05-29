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
import br.com.joao.portalmobileuscs.Pessoa;
import br.com.joao.portalmobileuscs.Professor;
import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.adapter.ProfessorArrayAdapter;
import br.com.joao.portalmobileuscs.libs.ORMLiteHelper;


public class ProfessoresActivity extends SherlockActivity {

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

		RuntimeExceptionDao<DisciplinaProfessor, Integer> disciplinaProfessorDao = ORMLiteHelper.getInstance(getApplicationContext()).getDisciplinaProfessorRuntimeDao();
		RuntimeExceptionDao<Disciplina, Integer> disciplinaDao = ORMLiteHelper.getInstance(getApplicationContext()).getDisciplinaRuntimeDao();
		RuntimeExceptionDao<Professor, Integer> professorDao = ORMLiteHelper.getInstance(getApplicationContext()).getProfessorRuntimeDao();
		RuntimeExceptionDao<Pessoa, Integer> pessoaDao = ORMLiteHelper.getInstance(getApplicationContext()).getPessoaRuntimeDao();

		for (DisciplinaProfessor disciplinaProfessor : disciplinaProfessorDao.queryForAll()) {

			professorDao.refresh(disciplinaProfessor.getProfessor());
			disciplinaDao.refresh(disciplinaProfessor.getDisciplina());
			pessoaDao.refresh(disciplinaProfessor.getProfessor().getPessoa());

			// Nome do estudante, email do estudante
			values.add(new String[] { disciplinaProfessor.getProfessor().getPessoa().getNomePessoa(), disciplinaProfessor.getProfessor().getPessoa().getEmail() });

		}

		ListView lstw = (ListView) findViewById(R.id.list_layout);

		ProfessorArrayAdapter adapter = new ProfessorArrayAdapter(this, values);

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
