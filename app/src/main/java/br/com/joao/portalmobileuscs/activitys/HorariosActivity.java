package br.com.joao.portalmobileuscs.activitys;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.libs.FuncoesData;

public class HorariosActivity extends SherlockFragmentActivity {

    HorariosFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.horarios_layout);

        mAdapter = new HorariosFragmentAdapter(this, getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

		/*
         * Definindo o dia da semana:
		 * 
		 * Para FuncoesData.diaSemana() domingo � 1, segunda � 2 e assim por
		 * diante.
		 * 
		 * Para o cabe�alho usamos um array que come�a do 0, logo domingo � 0,
		 * segunda � 1 e assim por diante.
		 * 
		 * Por este motivo subtraimos 1 quando passamos dia da semana atual.
		 */
        mIndicator.setCurrentItem(FuncoesData.diaSemana() - 1);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
