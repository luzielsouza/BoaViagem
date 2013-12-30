package br.com.casadocodigo.boaviagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.Toast;

public class GastoListActivity extends ListActivity implements
		OnItemClickListener {

	private List<Map<String, Object>> gastos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		String[] de = { "data", "descricao", "valor", "categoria" };
		int[] para = { R.id.data, R.id.descricao, R.id.valor, R.id.categoria };

		SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(),
				R.layout.lista_gasto, de, para);

		adapter.setViewBinder(new GastoViewBinder());

		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
		// registro do novo menu de contexto que dará opções ao usuário.
		registerForContextMenu(getListView());
	}

	private List<Map<String, Object>> listarGastos() {
		gastos = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("data", "04/02/2012");
		item.put("descricao", "Diária Hotel");
		item.put("valor", "R$ 260,00");
		item.put("categoria", R.color.categoria_hospedagem);
		gastos.add(item);
		// pode adicionar mais informações de viagens
		return gastos;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Map<String, Object> map = gastos.get(position);
		String descricao = (String) map.get("descricao");
		String mensagem = "Gasto selecionada: " + descricao;
		Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();

	}

	private class GastoViewBinder implements ViewBinder {
		@Override
		public boolean setViewValue(View view, Object data,
				String textRepresentation) {
			if (view.getId() == R.id.barraProgresso) {
				Double valores[] = (Double[]) data;
				ProgressBar progressBar = (ProgressBar) view;
				progressBar.setMax(valores[0].intValue());
				progressBar.setSecondaryProgress(valores[1].intValue());
				progressBar.setProgress(valores[2].intValue());
				return true;
			}
			return false;
		}
	}

	// o método onCreateContextMenu serve para ler as opções do XML e
	// transformá-las em um objeto do tipo ContextMenu:

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.gasto_menu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.remover) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			gastos.remove(info.position);
			getListView().invalidateViews();
			// remover do banco de dados
			return true;
		}
		return super.onContextItemSelected(item);
	}
}
