package br.com.joao.portalmobileuscs.activitys;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class HorariosFragmentAdapter extends FragmentPagerAdapter {

	// Definindo as Tab( dias da semana )
	protected static final String[] CONTENT = new String[] {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado" };

	// Quantidade de Tabs
	private int tabCount = CONTENT.length;

	private Context contexto;

	public HorariosFragmentAdapter(Context c, FragmentManager fm) {
		super(fm);
		this.contexto = c;
	}

	@Override
	public Fragment getItem(int position) {

		return HorariosFragment.newInstance(contexto, position);
	}

	@Override
	public int getCount() {
		return tabCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return HorariosFragmentAdapter.CONTENT[position];
	}

}