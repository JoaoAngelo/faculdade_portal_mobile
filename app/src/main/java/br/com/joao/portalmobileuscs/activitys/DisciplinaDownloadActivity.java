package br.com.joao.portalmobileuscs.activitys;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.joao.portalmobileuscs.Disciplina;
import br.com.joao.portalmobileuscs.DisciplinaProfessor;
import br.com.joao.portalmobileuscs.Download;
import br.com.joao.portalmobileuscs.Pessoa;
import br.com.joao.portalmobileuscs.Professor;
import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.adapter.DisciplinaDownloadArrayAdapter;
import br.com.joao.portalmobileuscs.libs.ORMLiteHelper;

public class DisciplinaDownloadActivity extends SherlockActivity {

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

		List<Download> download_agrupado = null;

		ArrayList<String[]> values = new ArrayList<String[]>();

		RuntimeExceptionDao<DisciplinaProfessor, Integer> disciplinaProfessorDao = ORMLiteHelper.getInstance(getApplicationContext()).getDisciplinaProfessorRuntimeDao();
		RuntimeExceptionDao<Disciplina, Integer> disciplinaDao = ORMLiteHelper.getInstance(getApplicationContext()).getDisciplinaRuntimeDao();
		RuntimeExceptionDao<Professor, Integer> professorDao = ORMLiteHelper.getInstance(getApplicationContext()).getProfessorRuntimeDao();
		RuntimeExceptionDao<Pessoa, Integer> pessoaDao = ORMLiteHelper.getInstance(getApplicationContext()).getPessoaRuntimeDao();
		RuntimeExceptionDao<Download, Integer> downloadDao = ORMLiteHelper.getInstance(getApplicationContext()).getDownloadRuntimeDao();

		try {
			download_agrupado = downloadDao.queryBuilder().groupBy("id_disciplina_professor").query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Download download : download_agrupado) {

			disciplinaProfessorDao.refresh(download.getDisciplinaProfessor());
			disciplinaDao.refresh(download.getDisciplinaProfessor().getDisciplina());
			professorDao.refresh(download.getDisciplinaProfessor().getProfessor());
			pessoaDao.refresh(download.getDisciplinaProfessor().getProfessor().getPessoa());

			long total_arquivos = 0;
			try {
				total_arquivos = downloadDao.countOf(downloadDao.queryBuilder().setCountOf(true).where().eq("id_disciplina_professor", download.getDisciplinaProfessor().getIdDisciplinaProfessor()).prepare());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			values.add(new String[] { Integer.toString(download.getDisciplinaProfessor().getIdDisciplinaProfessor()), download.getDisciplinaProfessor().getDisciplina().getNomeDisciplina(), download.getDisciplinaProfessor().getProfessor().getPessoa().getNomePessoa(), Long.toString(total_arquivos) });

		}

		ListView lstw = (ListView) findViewById(R.id.list_layout);

		DisciplinaDownloadArrayAdapter adapter = new DisciplinaDownloadArrayAdapter(this, values);

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
