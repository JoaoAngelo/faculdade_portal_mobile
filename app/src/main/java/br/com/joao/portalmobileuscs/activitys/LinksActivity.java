package br.com.joao.portalmobileuscs.activitys;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import br.com.joao.portalmobileuscs.Link;
import br.com.joao.portalmobileuscs.R;
import br.com.joao.portalmobileuscs.adapter.LinkArrayAdapter;
import br.com.joao.portalmobileuscs.libs.ORMLiteHelper;

public class LinksActivity extends SherlockActivity {

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

        RuntimeExceptionDao<Link, Integer> linkDao = ORMLiteHelper.getInstance(getApplicationContext()).getLinkRuntimeDao();

        ListView lstw = (ListView) findViewById(R.id.list_layout);
        LinkArrayAdapter adapter = new LinkArrayAdapter(this, linkDao.queryForAll());

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
