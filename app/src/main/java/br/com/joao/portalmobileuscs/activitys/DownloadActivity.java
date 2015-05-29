package br.com.joao.portalmobileuscs.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.joao.portalmobileuscs.Download;
import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.adapter.DownloadArrayAdapter;
import br.com.joao.portalmobileuscs.libs.ORMLiteHelper;

public class DownloadActivity extends SherlockActivity {

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

		Intent i = getIntent();

		Bundle params = i.getExtras();
		String idDisciplinaProfessor = "";
		String nomeDisciplina = "";
		if (params != null) {
			idDisciplinaProfessor = params.getString("idDisciplinaProfessor");
			nomeDisciplina = params.getString("nomeDisciplina");
		}
		this.setTitle(nomeDisciplina);
		RuntimeExceptionDao<Download, Integer> downloadDao = ORMLiteHelper.getInstance(getApplicationContext()).getDownloadRuntimeDao();

		QueryBuilder<Download, Integer> queryBuilder = downloadDao.queryBuilder();

		PreparedQuery<Download> preparedQuery = null;

		try {
			queryBuilder.where().eq("id_disciplina_professor", idDisciplinaProfessor);

			preparedQuery = queryBuilder.prepare();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (Download download : downloadDao.query(preparedQuery)) {

			// Nome do estudante, email do estudante
			values.add(new String[] { download.getDescricaoDownload(), download.getDataInsercao(), download.getUrl() });

		}

		ListView lstw = (ListView) findViewById(R.id.list_layout);

		DownloadArrayAdapter adapter = new DownloadArrayAdapter(this, values);

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
