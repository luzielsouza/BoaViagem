package br.com.casadocodigo.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
	}

	/*
	 * com base na view que foi clicada iremos tomar a ação correta
	 */

	public void selecionarOpcao(View view) {

		TextView textView = (TextView) view;
		String opcao = "Opção: " + textView.getText().toString();
		Toast.makeText(this, opcao, Toast.LENGTH_SHORT).show();

		switch (view.getId()) {
		case R.id.nova_viagem:
			startActivity(new Intent(this, ViagemActivity.class));
			break;
		case R.id.novo_gasto:
			startActivity(new Intent(this, GastoActivity.class));
			break;

		case R.id.minhas_viagens:
			startActivity(new Intent(this, ViagemListActivity.class));
			break;

		}
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		finish();
		return true;
	}

}
