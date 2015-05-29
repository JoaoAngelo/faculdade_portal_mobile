package br.com.joao.portalmobileuscs.libs;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.joao.portalmobileuscs.Curso;
import br.com.joao.portalmobileuscs.CursoDisciplina;
import br.com.joao.portalmobileuscs.Disciplina;
import br.com.joao.portalmobileuscs.DisciplinaProfessor;
import br.com.joao.portalmobileuscs.Download;
import br.com.joao.portalmobileuscs.Estudante;
import br.com.joao.portalmobileuscs.Falta;
import br.com.joao.portalmobileuscs.GradeHoraria;
import br.com.joao.portalmobileuscs.Link;
import br.com.joao.portalmobileuscs.Nota;
import br.com.joao.portalmobileuscs.Pessoa;
import br.com.joao.portalmobileuscs.Professor;
import br.com.joao.portalmobileuscs.Turma;
import br.com.joao.portalmobileuscs.TurmaEstudante;

public class UserFunctions {

    private static String userError = "";

    private static JSONObject json_curso; // ok
    private static JSONArray json_curso_disciplina; // ok
    private static JSONArray json_disciplina; // ok
    private static JSONArray json_disciplina_professor; // ok
    private static JSONArray json_estudante; // ok
    private static JSONArray json_falta; // ok
    private static JSONArray json_grade_horaria; // ok
    /*
     * N�o � necess�rio importar esta tabela, pois os dados da grade hor�ria do
     * estudante j� vem filtrada. private static JSONArray
     * json_grade_horaria_estudante;
     */
    private static JSONArray json_nota; // ok
    private static JSONArray json_pessoa; // ok
    private static JSONArray json_professor; // ok
    private static JSONObject json_turma; // ok
    private static JSONArray json_turma_estudante; // ok
    private static JSONArray json_link; // ok
    private static JSONArray json_download; // ok

    // id_estudante no banco de dados, para efetuar consultas no banco de dados.
    private static int identificacaoDoEstudante;

    // Get identificacaoDoEstudante
    public static int getIdentificacaoDoEstudante() {
        return identificacaoDoEstudante;
    }

    // Set identificacaoDoEstudante
    public static void setIdentificacaoDoEstudante(int pIdentificacaoDoEstudante) {
        identificacaoDoEstudante = pIdentificacaoDoEstudante;
    }

    // constructor
    public UserFunctions() {

    }

    public static String getUserError() {
        return userError;
    }

    // M�todo para verificar se estudante est� logado na aplica��o. Usado quando
    // estudante reabre a aplica��o.
    public static boolean estaLogado(Context context) {

        // Inst�ncia do DAO para estudante
        RuntimeExceptionDao<Estudante, Integer> estudanteDao = ORMLiteHelper.getInstance(context).getEstudanteRuntimeDao();

        // Consulta se existe dados do estudante no banco de dados
        if (estudanteDao.idExists(UserFunctions.getIdentificacaoDoEstudante())) {
            return true;
        }
        return false;
    }

    // M�todo para efetuar logon o estudante
    public static void fazerLogon(Context context, JSONObject json) {

        String nomePessoaLogada = "";
        String error = "";
        try {
            RuntimeExceptionDao<Estudante, Integer> estudanteRuntimeDao = ORMLiteHelper.getInstance(context).getEstudanteRuntimeDao();
            RuntimeExceptionDao<Pessoa, Integer> pessoaRuntimeDao = ORMLiteHelper.getInstance(context).getPessoaRuntimeDao();
            RuntimeExceptionDao<Professor, Integer> professorRuntimeDao = ORMLiteHelper.getInstance(context).getProfessorRuntimeDao();
            RuntimeExceptionDao<Turma, Integer> turmaRuntimeDao = ORMLiteHelper.getInstance(context).getTurmaRuntimeDao();
            RuntimeExceptionDao<TurmaEstudante, Integer> turmaEstudanteRuntimeDao = ORMLiteHelper.getInstance(context).getTurmaEstudanteRuntimeDao();
            RuntimeExceptionDao<Curso, Integer> cursoRuntimeDao = ORMLiteHelper.getInstance(context).getCursoRuntimeDao();
            RuntimeExceptionDao<Disciplina, Integer> disciplinaRuntimeDao = ORMLiteHelper.getInstance(context).getDisciplinaRuntimeDao();
            RuntimeExceptionDao<CursoDisciplina, Integer> cursoDisciplinaRuntimeDao = ORMLiteHelper.getInstance(context).getCursoDisciplinaRuntimeDao();
            RuntimeExceptionDao<DisciplinaProfessor, Integer> disciplinaProfessorRuntimeDao = ORMLiteHelper.getInstance(context).getDisciplinaProfessorRuntimeDao();
            RuntimeExceptionDao<Falta, Integer> faltaRuntimeDao = ORMLiteHelper.getInstance(context).getFaltaRuntimeDao();
            RuntimeExceptionDao<GradeHoraria, Integer> gradeHorariaRuntimeDao = ORMLiteHelper.getInstance(context).getGradeHorariaRuntimeDao();
            RuntimeExceptionDao<Nota, Integer> notaRuntimeDao = ORMLiteHelper.getInstance(context).getNotaRuntimeDao();
            RuntimeExceptionDao<Link, Integer> linkRuntimeDao = ORMLiteHelper.getInstance(context).getLinkRuntimeDao();
            RuntimeExceptionDao<Download, Integer> downloadRuntimeDao = ORMLiteHelper.getInstance(context).getDownloadRuntimeDao();

            json_curso = json.getJSONObject("curso");
            json_turma = json.getJSONObject("turma");
            json_pessoa = json.getJSONArray("pessoa");
            json_estudante = json.getJSONArray("estudante");
            json_professor = json.getJSONArray("professor");
            json_turma_estudante = json.getJSONArray("turma_estudante");
            json_disciplina = json.getJSONArray("disciplina");
            json_curso_disciplina = json.getJSONArray("curso_disciplina");
            json_disciplina_professor = json.getJSONArray("disciplina_professor");
            json_falta = json.getJSONArray("falta");
            json_grade_horaria = json.getJSONArray("grade_horaria");
            json_nota = json.getJSONArray("nota");
            json_link = json.getJSONArray("link");
            json_download = json.getJSONArray("download");

            Pessoa pessoa;
            Professor professor;
            Estudante estudante;
            Turma turma;
            TurmaEstudante turma_estudante;
            Curso curso;
            Disciplina disciplina;
            CursoDisciplina curso_disciplina;
            DisciplinaProfessor disciplina_professor;
            Falta falta;
            GradeHoraria gradeHoraria;
            Nota nota;
            Link link;
            Download download;

            Log.e("Passei!!!", "Importando curso");
            // Importando curso.......
            // Cria inst�ncia � ser inserida no banco de dados pela DAO
            curso = new Curso(json_curso.getInt("id_curso"), json_curso.getString("nome_curso"));
            // Insere no banco de dados
            cursoRuntimeDao.create(curso);

            Log.e("Passei!!!", "Importando turma");
            // Importando turma.......
            // Cria inst�ncia � ser inserida no banco de dados pela DAO
            turma = new Turma(json_turma.getInt("id_turma"), json_turma.getString("codigo_turma"), json_turma.getInt("numero_sala"));
            // Insere no banco de dados
            turmaRuntimeDao.create(turma);

            Log.e("Passei!!!", "Importando pessoas");
            // Importando pessoas.......
            for (int i = 0; i < json_pessoa.length(); i++) {

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                pessoa = new Pessoa(json_pessoa.getJSONObject(i).getInt("id_pessoa"), json_pessoa.getJSONObject(i).getString("nome_pessoa"), json_pessoa.getJSONObject(i).getString("email"));

                // Insere no banco de dados
                pessoaRuntimeDao.create(pessoa);

            }
            Log.e("Passei!!!", "Importando estudante");

            // Importando estudante.....
            for (int es = 0; es < json_estudante.length(); es++) {

                pessoa = pessoaRuntimeDao.queryForId(json_estudante.getJSONObject(es).getInt("id_pessoa"));

                // Identifica��o do estudante logado
                if (json_estudante.getJSONObject(es).getString("sta_logado").equals("S")) {
                    UserFunctions.identificacaoDoEstudante = json_estudante.getJSONObject(es).getInt("id_estudante");
                    nomePessoaLogada = pessoa.getNomePessoa();
                }

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                estudante = new Estudante(json_estudante.getJSONObject(es).getInt("id_estudante"), pessoa, json_estudante.getJSONObject(es).getString("sta_logado"));

                // Insere no banco de dados
                estudanteRuntimeDao.create(estudante);

            }
            Log.e("Passei!!!", "Importar turma x estudante");

            // Importar turma x estudante
            for (int te = 0; te < json_turma_estudante.length(); te++) {

                estudante = estudanteRuntimeDao.queryForId(json_turma_estudante.getJSONObject(te).getInt("id_estudante"));

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                turma_estudante = new TurmaEstudante(json_turma_estudante.getJSONObject(te).getInt("id_turma_estudante"), turma, estudante);

                // Insere no banco de dados
                turmaEstudanteRuntimeDao.create(turma_estudante);

            }
            Log.e("Passei!!!", "Importando professor");

            // Importando professor.......
            for (int j = 0; j < json_professor.length(); j++) {

                pessoa = pessoaRuntimeDao.queryForId(json_professor.getJSONObject(j).getInt("id_pessoa"));

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                professor = new Professor(json_professor.getJSONObject(j).getInt("id_professor"), pessoa);

                // Insere no banco de dados
                professorRuntimeDao.create(professor);

            }
            Log.e("Passei!!!", "Importando disciplinas");

            // Importando disciplinas.......
            for (int i = 0; i < json_disciplina.length(); i++) {

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                disciplina = new Disciplina(json_disciplina.getJSONObject(i).getInt("id_disciplina"), json_disciplina.getJSONObject(i).getString("nome_disciplina"), json_disciplina.getJSONObject(i).getString("sta_presencial"));
                // Insere no banco de dados
                disciplinaRuntimeDao.create(disciplina);

            }
            Log.e("Passei!!!", "Importar curso x disciplina");

            // Importar curso x disciplina
            for (int d = 0; d < json_curso_disciplina.length(); d++) {

                disciplina = disciplinaRuntimeDao.queryForId(json_disciplina_professor.getJSONObject(d).getInt("id_disciplina"));

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                curso_disciplina = new CursoDisciplina(json_curso_disciplina.getJSONObject(d).getInt("id_curso_disciplina"), curso, disciplina);
                // Insere no banco de dados
                cursoDisciplinaRuntimeDao.create(curso_disciplina);

            }
            Log.e("Passei!!!", "Importar disciplina x professor");

            // Importar disciplina x professor
            for (int d = 0; d < json_disciplina_professor.length(); d++) {

                professor = professorRuntimeDao.queryForId(json_disciplina_professor.getJSONObject(d).getInt("id_professor"));
                disciplina = disciplinaRuntimeDao.queryForId(json_disciplina_professor.getJSONObject(d).getInt("id_disciplina"));

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                disciplina_professor = new DisciplinaProfessor(json_disciplina_professor.getJSONObject(d).getInt("id_disciplina_professor"), disciplina, professor);
                // Insere no banco de dados
                disciplinaProfessorRuntimeDao.create(disciplina_professor);

            }
            Log.e("Passei!!!", "Importar link");

            // Importar link
            for (int d = 0; d < json_link.length(); d++) {

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                link = new Link(json_link.getJSONObject(d).getInt("id_link"), json_link.getJSONObject(d).getString("descricao_link"), json_link.getJSONObject(d).getString("url"));
                // Insere no banco de dados
                linkRuntimeDao.create(link);

            }
            Log.e("Passei!!!", "Importar download");

            // Importar download
            for (int d = 0; d < json_download.length(); d++) {

                disciplina_professor = disciplinaProfessorRuntimeDao.queryForId(json_download.getJSONObject(d).getInt("id_disciplina_professor"));

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                download = new Download(json_download.getJSONObject(d).getInt("id_download"), disciplina_professor, json_download.getJSONObject(d).getString("descricao_download"), json_download.getJSONObject(d).getString("url"), json_download.getJSONObject(d).getString("data_insercao"));
                // Insere no banco de dados
                downloadRuntimeDao.create(download);

            }
            Log.e("Passei!!!", "Importando Faltas");
            // Importar faltas
            for (int d = 0; d < json_falta.length(); d++) {
                disciplina_professor = disciplinaProfessorRuntimeDao.queryForId(json_falta.getJSONObject(d).getInt("id_disciplina_professor"));
                estudante = estudanteRuntimeDao.queryForId(json_falta.getJSONObject(d).getInt("id_estudante"));

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                falta = new Falta(json_falta.getJSONObject(d).getInt("id_falta"), disciplina_professor, estudante, json_falta.getJSONObject(d).getInt("qtd_faltas"), json_falta.getJSONObject(d).getInt("limite_faltas"));
                // Insere no banco de dados
                faltaRuntimeDao.create(falta);

            }
            Log.e("Passei!!!", "Importar grade horaria");
            // Importar grade horaria
            for (int d = 0; d < json_grade_horaria.length(); d++) {

                disciplina_professor = disciplinaProfessorRuntimeDao.queryForId(json_grade_horaria.getJSONObject(d).getInt("id_disciplina_professor"));

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                gradeHoraria = new GradeHoraria(json_grade_horaria.getJSONObject(d).getInt("id_grade_horaria"), disciplina_professor, json_grade_horaria.getJSONObject(d).getInt("dia_semana"), json_grade_horaria.getJSONObject(d).getString("hora_inicio"), json_grade_horaria.getJSONObject(d).getString("hora_fim"));
                // Insere no banco de dados
                gradeHorariaRuntimeDao.create(gradeHoraria);

            }
            Log.e("Passei!!!", "Importar notas");

            // Importar notas
            for (int d = 0; d < json_nota.length(); d++) {

                curso_disciplina = cursoDisciplinaRuntimeDao.queryForId(json_nota.getJSONObject(d).getInt("id_curso_disciplina"));
                estudante = estudanteRuntimeDao.queryForId(json_nota.getJSONObject(d).getInt("id_estudante"));

                // Cria inst�ncia � ser inserida no banco de dados pela DAO
                nota = new Nota(json_nota.getJSONObject(d).getInt("id_nota"), curso_disciplina, estudante, json_nota.getJSONObject(d).getDouble("aproveitamento"), json_nota.getJSONObject(d).getDouble("prova"), json_nota.getJSONObject(d).getDouble("substitutiva"), json_nota.getJSONObject(d).getDouble("total_pontos"), json_nota.getJSONObject(d).getDouble("reavaliacao"), json_nota.getJSONObject(d).getDouble("media_final"), json_nota.getJSONObject(d).getString("resultado_final"));
                // Insere no banco de dados
                notaRuntimeDao.create(nota);

            }

            // Exibe mensagem de Bem vindo ao estudante.
            Mensagem.toastLong(context, "Bem vindo(a), " + nomePessoaLogada + ".");

        } catch (JSONException e) {
            error = "JSONException - Erro 007 " + e.toString();
        }

        userError = error;
    }

    public static void fazerLogon(Context context) {

        RuntimeExceptionDao<Estudante, Integer> estudanteRuntimeDao = ORMLiteHelper.getInstance(context).getEstudanteRuntimeDao();

        Estudante estudante = estudanteRuntimeDao.queryForId(1);


        Mensagem.toastLong(context, "Bem vindo(a), " + estudante.getPessoa().getNomePessoa() + ".");

    }

    public static boolean fazerLogout(Context context) {
        ORMLiteHelper.getInstance(context).resetTables();
        return true;
    }

}
