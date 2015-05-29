package br.com.joao.portalmobileuscs.activitys;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.joao.portalmobileuscs.Disciplina;
import br.com.joao.portalmobileuscs.DisciplinaProfessor;
import br.com.joao.portalmobileuscs.GradeHoraria;
import br.com.joao.portalmobileuscs.Pessoa;
import br.com.joao.portalmobileuscs.Professor;
import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.adapter.HorariosArrayAdapter;
import br.com.joao.portalmobileuscs.libs.ORMLiteHelper;

public final class HorariosFragment extends SherlockFragment {

	private Context contexto;
	private int dia_da_semana;

	public static HorariosFragment newInstance(Context context, int posicao) {
		HorariosFragment fragment = new HorariosFragment();
		fragment.contexto = context;
		fragment.dia_da_semana = posicao;
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_layout, container, false);

		int contadorDiaLivre = 0;

		// Popular este array list com os dados do banco de dados
		ArrayList<String[]> values = new ArrayList<String[]>();

		RuntimeExceptionDao<GradeHoraria, Integer> gradeHorariaDao = ORMLiteHelper.getInstance(contexto).getGradeHorariaRuntimeDao();
		RuntimeExceptionDao<DisciplinaProfessor, Integer> disciplinaProfessorDao = ORMLiteHelper.getInstance(contexto).getDisciplinaProfessorRuntimeDao();
		RuntimeExceptionDao<Disciplina, Integer> disciplinaDao = ORMLiteHelper.getInstance(contexto).getDisciplinaRuntimeDao();
		RuntimeExceptionDao<Professor, Integer> professorDao = ORMLiteHelper.getInstance(contexto).getProfessorRuntimeDao();
		RuntimeExceptionDao<Pessoa, Integer> pessoaDao = ORMLiteHelper.getInstance(contexto).getPessoaRuntimeDao();

		List<GradeHoraria> gradeHorariaList = null;
		try {
			gradeHorariaList = gradeHorariaDao.queryBuilder().where().eq("dia_semana", dia_da_semana).query();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (GradeHoraria gradeHoraria : gradeHorariaList) {

			disciplinaProfessorDao.refresh(gradeHoraria.getDisciplinaProfessor());
			disciplinaDao.refresh(gradeHoraria.getDisciplinaProfessor().getDisciplina());
			professorDao.refresh(gradeHoraria.getDisciplinaProfessor().getProfessor());
			pessoaDao.refresh(gradeHoraria.getDisciplinaProfessor().getProfessor().getPessoa());

			// Nome da Disciplina, Nome Professor, Hora Inicio, Hora Fim
			values.add(new String[] { gradeHoraria.getDisciplinaProfessor().getDisciplina().getNomeDisciplina(), gradeHoraria.getDisciplinaProfessor().getProfessor().getPessoa().getNomePessoa(), gradeHoraria.getHoraInicio(), gradeHoraria.getHoraFim() });
			contadorDiaLivre++;
		}

		// Se n�o existir grade para o dia, exibir o texto "Livre".
		if (contadorDiaLivre == 0) {

			// Nome da Disciplina, Nome Professor, Hora Inicio, Hora Fim
			values.add(new String[] { "Livre", "--", "--", "--" });
		}

		ListView lstw = (ListView) view.findViewById(R.id.list_layout);

		HorariosArrayAdapter adapter = new HorariosArrayAdapter(contexto, values);

		lstw.setAdapter(adapter);

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
